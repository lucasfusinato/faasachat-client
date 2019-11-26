const { ipcRenderer } = require('electron');

const blockContainer = document.getElementById('block-container');

const userInfo = document.getElementById('user-info');
const fileMessageInput = document.getElementById('chat-file-field');
const fileMessageIcon = document.getElementById('chat-file-field-icon');
const contactsList = document.getElementById('contacts-list');
const chatArea = document.getElementById('chat-area');
const chatMessages = document.getElementById('chat-messages');
const chatMessagesList = document.getElementById('chat-messages-list');
const activeContactName = document.getElementById('active-contact-name');
const sendMessageInput = document.getElementById('send-message-input');

let openedConversation = null;

fileMessageIcon.addEventListener('click', function(event) {
    if(openedConversation !== null && openedConversation.online) {
        fileMessageInput.click();
    }
});

fileMessageInput.addEventListener('change', function(event) {
    const file = fileMessageInput.files[0];
    fileMessageInput.value = null;

    const fileData = {};
    for(const property in file) {
        fileData[property] = file[property];
    }
    ipcRenderer.send('sendFile', { contact: openedConversation.id, content: fileData });
});

sendMessageInput.addEventListener('keypress', function(event) {
    if(openedConversation !== null && openedConversation.online) {
        if(event.keyCode === 13) {
            const message = sendMessageInput.value;
            if(message.trim().length > 0) {
                sendMessageInput.value = '';
                ipcRenderer.send('sendMessage', { contact: openedConversation.id, content: message });
            }
        }
    }
});

function createContactElement(contact) {
    const div = document.createElement('div');
    div.innerHTML = `
        <div class="contact-item">
            <div class="contact-name">
                ${contact.unknow ? 'Anonymous' : contact.nickname}
            </div>
            <span class="contact-status" ${contact.unknow ? '' : `${contact.online ? 'online' : 'offline'}="true`}"></span>
        </div>
    `;
    const element = div.querySelector('.contact-item');
    element.contact = contact;
    element.addEventListener('click', function() {
        if(contact === openedConversation) {
            openedConversation = null;
        } else {
            openedConversation = contact;
        }
        sendMessageInput.value = '';
        updateConversationData();
    });
    return element;
};

function createMessageElement(message) {
    const div = document.createElement('div');
    div.innerHTML = `
        <div class="message-item" ${message.self ? 'self' : 'another'}="true">
            <div class="message-content">${message.file ? (message.content.buffer ? `<div class="lds-ripple" title="Receiving file..."><div></div><div></div></div>` : `<i class="fas fa-file-alt message-content-file" title="Click to download file: ${message.content.name}"></i>`) : message.content}</div>
        </div>
    `;
    if(message.file) {
        const imageElement = div.querySelector('.message-content-file');
        if(imageElement) {
            imageElement.addEventListener('click', function() {
                ipcRenderer.send('openFileMessage', message);
            });
        }
    }
    return div.querySelector('.message-item');
};

function updateContactsList(contacts) {
    contactsList.innerHTML = '';
    contacts.forEach(contact => {
        contactsList.appendChild(createContactElement(contact));
        if(openedConversation && contact.id === openedConversation.id) {
            openedConversation = contact;
            updateConversationData();
        }
    });
}

function updateConversationData() {
    contactsList.querySelectorAll('.contact-item').forEach(item => {
        if(openedConversation && item.contact.id === openedConversation.id) {
            item.setAttribute('active', true);
        } else {
            item.removeAttribute('active');
        }
    });
    chatArea.removeAttribute('user');
    chatMessagesList.innerHTML = '';
    if(openedConversation !== null) {
        chatArea.setAttribute('user', true);
        activeContactName.innerHTML = openedConversation.unknow ? 'Anonymous' : openedConversation.nickname;
        if(openedConversation.online) {
            fileMessageIcon.classList.remove('blocked-field');
            fileMessageIcon.setAttribute('title', 'Click here to send a file');
            sendMessageInput.removeAttribute('disabled');
            sendMessageInput.setAttribute('placeholder', 'Type a message here');
            sendMessageInput.classList.remove('blocked-field');
        } else {
            fileMessageIcon.classList.add('blocked-field');
            fileMessageIcon.setAttribute('title', `Can not send files to ${openedConversation.unknow ? 'anonymous' : 'offline'} user`);
            sendMessageInput.setAttribute('disabled', true);
            sendMessageInput.setAttribute('placeholder', `Can not send messages to ${openedConversation.unknow ? 'anonymous' : 'offline'} user`);
            sendMessageInput.classList.add('blocked-field');
        }
        sendMessageInput.focus();
        openedConversation.messages.forEach(appendMessage);
    }
}

function appendMessage(message) {
    chatMessagesList.appendChild(createMessageElement(message));
    chatMessages.scrollTo(0, chatMessagesList.offsetHeight);
}

let firstGetContactsRequest = true;
let firstUpdateNicknameRequest = true;

blockContainer.classList.remove('hide');

ipcRenderer.on('updateMessages', function() {
    ipcRenderer.send('getMessages');
})

ipcRenderer.on('contactsList', function(event, contactsList) {
    updateContactsList(contactsList);
    if(firstGetContactsRequest) {
        firstGetContactsRequest = false;
        if(!firstUpdateNicknameRequest) {
            blockContainer.classList.add('hide');
        }
    }
});

ipcRenderer.on('updateNickname', function(event, nickname) {
    userInfo.innerHTML = nickname;
    if(firstUpdateNicknameRequest) {
        firstUpdateNicknameRequest = false;
        if(!firstGetContactsRequest) {
            blockContainer.classList.add('hide');
        }
    }
});

ipcRenderer.send('getContacts');
ipcRenderer.send('getNickname');