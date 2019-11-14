package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public class RemoveContactStrategy {

    private ClientControllerInterface controller;

    public RemoveContactStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(int contactIndex) {
        controller.removeContact(contactIndex);
    }

}
