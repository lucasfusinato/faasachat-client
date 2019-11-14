package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

<<<<<<< HEAD
=======
/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
public class StrategyFactory {

    private ClientControllerInterface controller;
    
    public StrategyFactory(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public LoginStrategy createLoginStrategy() {
        return new LoginStrategy(controller);
    }

    public SignupStrategy createSignupStrategy() {
        return new SignupStrategy(controller);
    }

    public AddContactStrategy createAddContactStrategy() {
        return new AddContactStrategy(controller);
    }

    public RemoveContactStrategy createRemoveContactStrategy() {
        return new RemoveContactStrategy(controller);
    }

    public SignupStrategy createUpdateProfileStrategy() {
        return new SignupStrategy(controller);
    }

    public ClientControllerInterface createGetContactsStrategy() {
        return controller;
    }

}
