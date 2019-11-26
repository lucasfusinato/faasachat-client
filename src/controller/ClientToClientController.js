module.exports = new class {

    constructor() {
        this._contactsList = [];
        this._user = {};
    }

    getNickname(event) {
        event.sender.send('updateNickname', this._user.nickname);
    }

    initClient(port, user) {
        this._user = user;

        const express = require('express')();
        const http = require('http').createServer(express);
        const io = require('socket.io')(http);

        io.on('connection', client => {
            client.on('newMessage', message => {
                const contact = this._contactsList.find(contact => contact.id === parseInt(message.author));
                if(contact) {
                    if(message.file) {
                        const { key, name, buffer, end } = message.content;
                        const oldMessage = contact.messages.find(current => current.content.key === key);
                        if(oldMessage) {
                            oldMessage.content.buffer.push(buffer);
                            if(end) {
                                oldMessage.content.path = this._createReceivedFile(contact, Buffer.concat(oldMessage.content.buffer), oldMessage.content.name);
                                delete oldMessage.content.key;
                                delete oldMessage.content.buffer;
                            } else {
                                io.to(client.id).emit('receivedFileMessage');
                            }
                        } else {
                            const newMessage = { id: this._getNextIdMessage(contact), content: { name }, self: false, contact: contact.id, file: true };
                            if(end) {
                                let bufferData = [ buffer ];
                                newMessage.content.path = this._createReceivedFile(contact, Buffer.concat(bufferData), name);
                                contact.messages.push(newMessage);
                            } else {
                                newMessage.content.key = key;
                                newMessage.content.buffer = [ buffer ];
                                contact.messages.push(newMessage);
                                io.to(client.id).emit('receivedFileMessage');
                            }
                            require('electron').ipcMain.emit('updateMessages');
                        }
                    } else {
                        contact.messages.push({ id: this._getNextIdMessage(contact), content: message.content, contact: contact.id, self: false });
                        require('electron').ipcMain.emit('updateMessages');
                    }
                } else {
                    const newContact = { id: parseInt(message.author), unknow: true, messages: [] };
                    newContact.messages.push({ id: this._getNextIdMessage(newContact), content: message.content, contact: newContact.id, self: false });
                    this._contactsList.push(newContact);
                    require('electron').ipcMain.emit('updateMessages');
                }
                client.disconnect();
            });
        });
        http.listen(port);
    }

    getMessages(event) {
        return event.sender.send('contactsList', this._contactsList);
    }

    sendMessage(event, data) {
        const contact = this._contactsList.find(contact => contact.id === data.contact);
        if (contact) {
            contact.messages.push({ id: this._getNextIdMessage(contact), content: data.content, contact: contact.id, self: true });
            event.sender.send('contactsList', this._contactsList);

            const ioc = require('socket.io-client');
            const connection = ioc(contact.online);
            connection.emit('newMessage', { author: this._user.id, content: data.content });
        }
    }

    sendFile(event, data) {
        const fs = require('fs');
        const contact = this._contactsList.find(contact => contact.id === parseInt(data.contact));
        if (contact) {
            const newFileName = this._createFile(data.contact, data.content.name.split('.').pop());
            
            const source = fs.createReadStream(data.content.path);
            const destination = fs.createWriteStream(newFileName);

            source.pipe(destination);
    
            contact.messages.push({ id: this._getNextIdMessage(contact), content: { name: data.content.name, path: newFileName }, self: true, contact: contact.id, file: true });
            event.sender.send('contactsList', this._contactsList);
            
            //key, end
            let sourceData = [];
            source.on('data', (chunk) => {
                sourceData.push(chunk);
            });
            source.on('end', () => {
                const ioc = require('socket.io-client');
                const newMessage = { author: this._user.id, file: true, content: { name: data.content.name, key: this._generateFileKey() } };
                const buffer = Buffer.concat(sourceData);
                const sliceInterval = 1000000; 
                
                var sendSlice = function(ini, end) {
                    const connection = ioc(contact.online);
                    const sendBuffer = buffer.slice(ini, end);
                    newMessage.content.buffer = sendBuffer;
                    newMessage.content.end = buffer.length < end;
                    connection.emit('newMessage', newMessage);

                    if(newMessage.content.end) {
                        return;
                    }
                    connection.on('receivedFileMessage', function() {
                        sendSlice(ini + sliceInterval, end + sliceInterval);
                    });
                };
                sendSlice(0, sliceInterval);
            });
        }
    }

    _createFile(contactId, type) {
        const fs = require('fs');
        const path = require('path');

        const newFilePath = path.resolve('file://', __dirname, 'assets/files/', contactId + '');
        if (!fs.existsSync(newFilePath)) {
            fs.mkdirSync(newFilePath, { recursive: true });
        }
        return path.join(newFilePath, '/', this._getRandomFileName(type));
    }

    _createReceivedFile(contact, buffer, name) {
        const fs = require('fs');
        const newFileName = this._createFile(contact.id, name.split('.').pop());

        const file = fs.openSync(newFileName, 'w');
        fs.writeSync(file, buffer, 0, buffer.length, 0);
        fs.closeSync(file);

        return newFileName;
    }

    openFileMessage(event, data) {
        const { shell } = require('electron');
        if(data.self) {
            shell.openItem(data.content.path);
        } else {
            const contact = this._contactsList.find(contact => contact.id === parseInt(data.contact));
            if(contact) {
                const message = contact.messages.find(message => message.id === parseInt(data.id));
                shell.openItem(message.content.path);
            }
        }
    }

    set contactsList(contactsList) {
        const anonymous = this._contactsList.filter(contact => !contactsList.some(listContact => contact.id === listContact.id));
        anonymous.forEach(contact => {
            contact.unknow = true;
            delete contact.online;
            delete contact.nickname;
        });
        this._contactsList = contactsList.concat(anonymous).map(contact => {
            const current = this._contactsList.find(current => current.id === contact.id);
            if(current) {
                contact.messages = current.messages;
            }
            if(!contact.messages) {
                contact.messages = [];
            }
            return contact;
        });
    }

    get contactsList() {
        return this._contactsList;
    }

    _getRandomFileName(type) {
        let randomName = new Date().toLocaleString().replace(/[^0-9]/gi, '');
        randomName += require('randomstring').generate(20);
        randomName += '.';
        randomName += type;
        return randomName;
    }

    _generateFileKey() {
        return require('randomstring').generate(20);
    }

    _getNextIdMessage(contact) {
        return contact.messages.length;
    }

};