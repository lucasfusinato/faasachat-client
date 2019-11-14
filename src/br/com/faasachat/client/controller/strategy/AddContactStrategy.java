package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

<<<<<<< HEAD
public class AddContactStrategy {
    
    private ClientControllerInterface controller;
    
    public AddContactStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(String nickname) {
        controller.addContact(nickname);
=======
/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public class AddContactStrategy {

    public AddContactStrategy(ClientControllerInterface controller) {
        // TODO Auto-generated constructor stub
    }

    public void execute(String nickname) {
        // TODO Auto-generated method stub
        
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
    }

}
