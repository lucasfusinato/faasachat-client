const { app, BrowserWindow, ipcMain, Menu } = require('electron');
const path = require('path');

const C2S = require('./controller/ClientToServerController');
const C2C = require('./controller/ClientToClientController');


let window = null;

Menu.setApplicationMenu(false);

app.on('ready', function () {
    window = new BrowserWindow({ width: 700, height: 500, webPreferences: { nodeIntegration: true } });
    //window.webContents.openDevTools();
    window.on('closed', function () {
        window = null;
    });

    //Internal requests
    ipcMain.on('successfulSignup', function(email) {
        window.webContents.send('successfulSignup', email);
    });
    ipcMain.on('updateMessages', function(contactsList) {
        window.webContents.send('updateMessages');
    });
    ipcMain.on('redirect', function(url) {
        window.loadURL(url);
    });
    ipcMain.emit('redirect', path.join('file://', __dirname, 'view/home.html'));
    
    //Client-server requests
    ipcMain.on('login',         C2S.login.bind(C2S));
    ipcMain.on('signup',        C2S.signup.bind(C2S))
    ipcMain.on('signout',       C2S.signout.bind(C2S));
    ipcMain.on('getContacts',   C2S.getContacts.bind(C2S));
    ipcMain.on('updateProfile', C2S.updateProfile.bind(C2S));
    ipcMain.on('addContact',    C2S.addContact.bind(C2S));
    ipcMain.on('removeContact', C2S.removeContact.bind(C2S));

    //Client-client requests
    ipcMain.on('sendMessage',     C2C.sendMessage.bind(C2C));
    ipcMain.on('sendFile',        C2C.sendFile.bind(C2C));
    ipcMain.on('openFileMessage', C2C.openFileMessage.bind(C2C));
    ipcMain.on('getNickname',     C2C.getNickname.bind(C2C));
    ipcMain.on('getMessages',     C2C.getMessages.bind(C2C));
});

app.on('activate', function () {
    if (window === null) {
        createWindow();
    }
});