package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

public class AddContactStrategy {
    
    private ClientControllerInterface controller;
    
    public AddContactStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(String nickname) {
        controller.addContact(nickname);
    }

}
