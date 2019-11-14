package br.com.faasachat.client.controller;

<<<<<<< HEAD
=======
/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
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
