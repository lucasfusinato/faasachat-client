module.exports = new class {

    constructor() {
        this._session = null;
    }

    login(event, data) {
        const { email, password } = data;
        
        this._findAvailablePort()
            .then(port => {
                const requestData = { email, password, url: `http://localhost:${port}` };
                this._sendRequest('POST', '/sessions', requestData).then(result => {
                    if(result.error) {
                        event.sender.send('homeError', result.content);
                    } else {
                        this._session = result.content.session;
                        this._openChatPage();
                        
                        const C2C = require('./ClientToClientController');
                        C2C.initClient(port, result.content.user);
                    }
                });
            });
    }
    
    signup(event, data) {
        const { nickname, email, yearOfBirth, password, port } = data;
        const requestData = { nickname, yearOfBirth, email, password, url: `http://localhost:${port}` };
        
        this._sendRequest('POST', '/users', requestData).then(result => {
            if(result.error) {
                event.sender.send('homeError', result.content);
            } else {
                this._openChatPage(port);
            }
        });
    }

    getContacts(event, data) { 
        this._sendRequest('GET', '/users/:userId/contacts').then(result => {
            if(!result.error) {
                const C2C = require('./ClientToClientController');
                C2C.contactsList = result.content;
                event.sender.send('contactsList', C2C.contactsList);
            }
            setTimeout(() => {
                this.getContacts(event, data);
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
        setTimeout(function () {
            let errorMessage = '';
            switch (contactNickname) {
                case 'admin':
                    errorMessage = 'Can not add yourself as a contact.';
                    break;
                case 'fusinato':
                    errorMessage = 'User does not exists.';
                    break;
                case 'contact1':
                    errorMessage = 'User already added as a contact.';
                    break;
            }
    
            event.sender.send('addContactResult', errorMessage);
        }, 1500);
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

    _openChatPage() {
        const { ipcMain } = require('electron');
        const path = require('path');
        ipcMain.emit('redirect', path.join('file://', __dirname, '../view/chat.html'));
    }

    _findAvailablePort() {
        const portscanner = require('portscanner');
        const ip = require('ip');

        return new Promise(resolve => {
            portscanner.findAPortNotInUse(3000, 3010, ip.address(), function(error, port) {
                resolve(port);
            });
        });
    }

    _sendRequest(method, path, data = null) {
        return new Promise(resolve => {
            const axios = require('axios');
            if(path.includes(':userId')) {
                path = path.replace(':userId', this._session.userId);
            }

            axios({
                method: method,
                url: 'http://localhost:3333' + path,
                data: data
            }).then(function(response) {
                resolve(response.data);
            });
        });
    }

};