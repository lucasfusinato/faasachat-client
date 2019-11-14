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
public class SignupStrategy {

    private ClientControllerInterface controller;

    public SignupStrategy(ClientControllerInterface controller) {
        this.controller = controller;
    }

    public void execute(String nickname, String email, String password, Integer yearOfBirth) {
        controller.signup(nickname, email, password, yearOfBirth);
    }

}
