module.exports = new class {

    constructor() {
        this._session = null;
        this._serverIp = "localhost";
        this._serverPort = 56000;
    }

    login(event, data) {
        const { email, password } = data;
        
        this._findAvailablePort()
            .then(data => {
                const requestData = { email, password, url: `http://${data.ip}:${data.port}` };
                this._sendRequest('POST', '/sessions', requestData).then(result => {
                    if(result.error) {
                        event.sender.send('homeError', result.content);
                    } else {
                        this._session = result.content.session;
                        this._openChatPage();
                        
                        const C2C = require('./ClientToClientController');
                        C2C.initClient(data.port, result.content.user);
                    }
                });
            });
    }
    
    signup(event, data) {
        const { nickname, email, yearOfBirth, password, port } = data;
        const requestData = { nickname, yearOfBirth, email, password };
        
        this._sendRequest('POST', '/users', requestData).then(result => {
            if(result.error) {
                event.sender.send('homeError', result.content);
            } else {
                this._notifySuccessfulSignup(result.content);
            }
        });
    }

    signout(event, data) {
        this._sendRequest('DELETE', '/sessions/:sessionId');
        this._openLoginPage();
        this._session = null;
    }

    getContacts(event, data) { 
        this._sendRequest('GET', '/users/:userId/contacts').then(result => {
            if(!result.error) {
                const C2C = require('./ClientToClientController');
                C2C.contactsList = result.content;
                event.sender.send('contactsList', C2C.contactsList);
            }
            setTimeout(() => {
                if(this._session) { 
                    this.getContacts(event, data);
                }
            }, 5000);
        });
    }

    updateProfile(event, data) {
        const { nickname, email, yearOfBirth, password } = data;
        const requestData = { nickname, yearOfBirth, email, password };

        this._sendRequest('PUT', '/users/:userId', requestData).then(result => {
            event.sender.send('updateProfileResult', result);
        });
    }

    addContact(event, data) {
        const { nickname } = data;
        const requestData = { nickname };

        this._sendRequest('POST', '/users/:userId/contacts', requestData).then(result => {
            if(result.error) {
                event.sender.send('chatError', result.content);
            } else {
                const C2C = require('./ClientToClientController');
                C2C.addContact(result.content);
                event.sender.send('newContact', C2C.contactsList);
            }
        });
    }

    removeContact(event, data) {
        setTimeout(function () {
            let errorMessage = '';
            if (contactNickname === 'contact99') {
                errorMessage = 'User is not a contact.';
            }
    
            event.sender.send('removeContactResult', errorMessage);
        }, 2000);
    }

    _openLoginPage() {
        const { ipcMain } = require('electron');
        const path = require('path');
        ipcMain.emit('redirect', path.join('file://', __dirname, '../view/home.html'));
    }

    _openChatPage() {
        const { ipcMain } = require('electron');
        const path = require('path');
        ipcMain.emit('redirect', path.join('file://', __dirname, '../view/chat.html'));
    }

    _notifySuccessfulSignup(user) {
        const { ipcMain } = require('electron');
        ipcMain.emit('successfulSignup', user.email);
    }

    _findAvailablePort() {
        const portscanner = require('portscanner');
        const ip = require('ip').address();

        return new Promise(resolve => {
            portscanner.findAPortNotInUse(56000, 56010, ip, function(error, port) {
                resolve({ ip, port });
            });
        });
    }

    _sendRequest(method, path, data = null) {
        return new Promise(resolve => {
            const axios = require('axios');
            if(path.includes(':userId')) {
                path = path.replace(':userId', this._session.userId);
            }
            if(path.includes(':sessionId')) {
                path = path.replace(':sessionId', this._session.id);
            }

            axios({
                method: method,
                url: `http://${this._serverIp}:${this._serverPort}${path}`,
                data: data
            }).then(function(response) {
                resolve(response.data);
            });
        });
    }

};