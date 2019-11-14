package br.com.faasachat.client.controller.strategy;

import br.com.faasachat.client.controller.ClientControllerInterface;

public class SignupStrategy {

    private ClientControllerInterface controller;

    public SignupStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(String nickname, String email, String password, Integer yearOfBirth) {
        controller.signup(nickname, email, password, yearOfBirth);
    }

}
