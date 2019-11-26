const { ipcRenderer, remote } = require('electron');

const errorContainer   = document.getElementById('error-container');
const successContainer = document.getElementById('success-container');
const blockContainer   = document.getElementById('block-container');

const form          = document.getElementById('form');
const nickname      = document.getElementById('nickname');
const email         = document.getElementById('email');
const yearOfBirth   = document.getElementById('yearOfBirth');
const password      = document.getElementById('password');
const button        = document.getElementById('button');
const notRegistered = document.getElementById('not-registered');

password.addEventListener('keypress', function(event) {
    if(event.keyCode === 13) {
        password.blur();
        button.click();
    }
});

ipcRenderer.on('homeError', function(event, errorMessage) {
    errorContainer.innerHTML = errorMessage;
    blockContainer.classList.add('hide');
    button.removeAttribute('disabled');
    
    errorContainer.classList.remove('hide');
    setTimeout(function() {
        errorContainer.classList.add('hide');
    }, 2000);
});

ipcRenderer.on('successfulSignup', function(event, emailValue) {
    notRegistered.click();
    email.value = emailValue;
    password.focus();
    button.removeAttribute('disabled');
    
    successContainer.innerHTML = 'Successful signup.';
    successContainer.classList.remove('hide');
    blockContainer.classList.add('hide');
    
    successContainer.classList.remove('hide');
    setTimeout(function() {
        successContainer.classList.add('hide');
    }, 2000);
});

errorContainer.addEventListener('click', function(event) {
    if(!errorContainer.classList.contains('hide')) {
        errorContainer.classList.add('hide');
    }
});

button.addEventListener('click', function(event) {
    if(!form.reportValidity()) {
        return false;
    }
    errorContainer.classList.add('hide');
    blockContainer.classList.remove('hide');

    button.setAttribute('disabled', true);

    const operation = form.getAttribute('type');
    const data = {};
    
    form.querySelectorAll('.field').forEach(field => {
        if(field.offsetParent !== null) {
            data[field.name] = field.value;
        }
    });

    ipcRenderer.send(operation, data);
});

notRegistered.addEventListener('click', function(event) {
    form.querySelectorAll('.field').forEach(field => field.value = '');

    const buttonTitle = button.dataset.lastTitle || 'Click to sign up';
    const buttonHtml = button.dataset.lastHtml || 'Sign up';
    const notRegisteredHtml = notRegistered.dataset.lastHtml || 'Are you registered? <b>Back to login!</b>';

    button.dataset.lastTitle = button.getAttribute('title');
    button.dataset.lastHtml = button.innerHTML;
    notRegistered.dataset.lastHtml = notRegistered.innerHTML;

    button.setAttribute('title', buttonTitle);
    button.innerHTML = buttonHtml;
    notRegistered.innerHTML = notRegisteredHtml;

    if(form.getAttribute('type') === 'login') {
        form.setAttribute('type', 'signup');
        nickname.focus();
    } else {
        form.setAttribute('type', 'login');
        email.focus();
    }
});

email.focus();