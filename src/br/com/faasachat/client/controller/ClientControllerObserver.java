package br.com.faasachat.client.controller;

public interface ClientControllerObserver {

    void openLogin(String message);

    void openSignup();

    void openHome(String message);

    void openProfile();

    void updateContacts();

    void catchException(Exception data);

}
