package br.com.faasachat.client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.faasachat.api.builder.AddContactRequestBuilder;
import br.com.faasachat.api.builder.GetContactsRequestBuilder;
import br.com.faasachat.api.builder.LoginRequestBuilder;
import br.com.faasachat.api.builder.RemoveContactRequestBuilder;
import br.com.faasachat.api.builder.SignupRequestBuilder;
import br.com.faasachat.api.builder.UpdateProfileRequestBuilder;
import br.com.faasachat.api.executor.StubServerExecutor;
import br.com.faasachat.api.executor.RequestExecutorCallback;
import br.com.faasachat.api.executor.SessionRequestExecutor;
import br.com.faasachat.api.executor.SocketRequestExecutor;
import br.com.faasachat.domain.adapter.GsonAdapter;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.Session;
import br.com.faasachat.domain.model.User;

/**
 * Test class to client controller class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 14/11/2019
 * @version 1.0
 */
public class ClientControllerTest {
    
    /**
     * Mock server executor.
     */
    private StubServerExecutor executor;
    
    /**
     * Client application main class.
     */
    private ClientController controller;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.executor = new StubServerExecutor();
        this.controller = new ClientController(this.executor);
    }

    @Test
    public void testLogin(String nickname, String password) {
        this.controller.login(nickname, password);
    }

    @Test
    public void testSignup(String nickname, String email, String password, int yearOfBirth) {
        this.controller.signup(nickname, email, password, yearOfBirth);
    }

    @Test
    public void testUpdateProfile(String nickname, String email, String password, int yearOfBirth) {
        this.controller.updateProfile(nickname, email, password, yearOfBirth);
    }

    @Test
    public void testGetContacts() {
        this.controller.getContacts();
    }

    @Test
    public void testAddContact(String nickname) {
        this.controller.addContact(nickname);
    }

    @Test
    public void testRemoveContact(int contactIndex) {
        this.controller.removeContact(contactIndex);
    }

    @Test
    public void testGetContactsQuantity() {
        this.controller.getContactsQuantity();
    }

    @Test
    public void testGetContactNickname(int index) {
        this.controller.getContactNickname(index);
    }

    @Test
    public void testGetContactActivity(int index) {
        this.controller.getContactActivity(index);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.controller = null;
    }

}
