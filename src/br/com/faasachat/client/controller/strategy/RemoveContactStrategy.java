package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

public class RemoveContactStrategy {

    private ClientControllerInterface controller;

    public RemoveContactStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(int contactIndex) {
        controller.removeContact(contactIndex);
    }

}
