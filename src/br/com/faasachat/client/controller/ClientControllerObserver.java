package br.com.faasachat.client.controller;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public interface ClientControllerObserver {

    void openLogin(String message);

    void openSignup();

    void openHome(String message);

    void openProfile();

    void updateContacts();

    void catchException(Exception data);

}
