const { app, BrowserWindow, Menu, ipcMain } = require('electron');
const path = require('path');

Menu.setApplicationMenu(false);

let window;

ipcMain.on('login', function(event, credentials) {
    const { email, password } = credentials;
    
    setTimeout(function() {
        let errorMessage = '';
        if(email !== 'admin@admin.com') {
            errorMessage = 'Not registered email.';
        } else if(password !== 'admin') {
            errorMessage = 'Invalid password.';
        }
        
        if(errorMessage.length > 0) {
            event.sender.send('homeError', errorMessage);
        } else {
            window.loadURL(path.join('file://', __dirname, 'src/chat.html'));
        }
    }, Math.random() * 5000);
});

ipcMain.on('signup', function(event, data) {
    const { nickname, email, yearOfBirth, password } = data;
    
    setTimeout(function() {
        let errorMessage = '';
        if(nickname === 'admin') {
            errorMessage = 'Already registered nickname';
        } else if(email === 'admin@admin.com') {
            errorMessage = 'Already registered email.';
        }
        
        if(errorMessage.length > 0) {
            event.sender.send('homeError', errorMessage);
        } else {
            window.loadURL(path.join('file://', __dirname, 'src/chat.html'));
        }
    }, Math.random() * 5000);
});

ipcMain.on('getContacts', function(event) {
    setTimeout(function() {
        event.send.send('contactsList', [
            { nickname: 'contact1', online: true },
            { nickname: 'contact2', online: true },
            { nickname: 'contact3', online: false },
            { nickname: 'contact4', online: true },
            { nickname: 'contact5', online: false },
            { nickname: 'contact6', online: false }
        ]);
    }, Math.random() * 5000);
});

ipcMain.on('addContact', function(event, contactNickname) {
    setTimeout(function() {
        let errorMessage = '';
        switch(contactNickname) {
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
});

ipcMain.on('removeContact', function(event, contactNickname) {
    setTimeout(function() {
        let errorMessage = '';
        if(contactNickname === 'contact99') {
            errorMessage = 'User is not a contact.';
        }

        event.sender.send('removeContactResult', errorMessage);
    }, 2000);
});

ipcMain.on('updateProfile', function(event, data) {
    const { nickname, email, yearOfBirth, password } = data;

    setTimeout(function() {
        let errorMessage = '';
        if(nickname === 'contact1') {
            errorMessage = 'Nickname already exists.';
        }
        if(email === 'contact1@email.com') {
            errorMessage = 'Email already exists.';
        }

        event.sender.send('updateProfileResult', errorMessage);
    }, 2000);
});

app.on('ready', function() {
    window = new BrowserWindow({ width: 600, height: 500, webPreferences: { nodeIntegration: true }});
    window.loadURL(path.join('file://', __dirname, 'src/home.html'));
    window.webContents.openDevTools();
    window.on('closed', function() {
        window = null;
    });
});

app.on('activate', function() {
    if(window === null) {
        createWindow();
    }
});