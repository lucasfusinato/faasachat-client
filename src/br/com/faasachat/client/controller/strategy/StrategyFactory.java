package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

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
