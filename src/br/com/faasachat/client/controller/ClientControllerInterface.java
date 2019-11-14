package br.com.faasachat.client.controller;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public interface ClientControllerInterface {

    void login(String nickname, String password);

    void signup(String nickname, String email, String password, int yearOfBirth);
    
    void updateProfile(String nickname, String email, String password, int yearOfBirth);

    void getContacts();
    
    void addContact(String nickname);
    
    void removeContact(int contactIndex);

    int getContactsQuantity();

    String getContactNickname(int index);

    String getContactActivity(int index);
    
}
