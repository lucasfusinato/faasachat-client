package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public class LoginStrategy {

    private ClientControllerInterface controller;

    public LoginStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(String nickname, String password) {
        controller.login(nickname, password);
    }

}
