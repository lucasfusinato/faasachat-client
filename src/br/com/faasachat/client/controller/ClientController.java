package br.com.faasachat.client.controller;

import java.util.List;

import br.com.faasachat.api.builder.AddContactRequestBuilder;
import br.com.faasachat.api.builder.GetContactsRequestBuilder;
import br.com.faasachat.api.builder.LoginRequestBuilder;
import br.com.faasachat.api.builder.RemoveContactRequestBuilder;
import br.com.faasachat.api.builder.SignupRequestBuilder;
import br.com.faasachat.api.builder.UpdateProfileRequestBuilder;
import br.com.faasachat.api.executor.RequestExecutor;
import br.com.faasachat.api.executor.RequestExecutorCallback;
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.Session;
import br.com.faasachat.domain.model.User;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public class ClientController implements ClientControllerInterface {

    private RequestExecutor requestExecutor;
    private ClientControllerObserver observer;
    private Session session;
    private List<User> contacts;

    public ClientController(RequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
    }
    
    public ClientController(RequestExecutor requestExecutor, ClientControllerObserver observer) {
        this.requestExecutor = requestExecutor;
        this.observer = observer;
    }

    @Override
    public void login(String nickname, String password) {
        requestExecutor.execute(new Request(new LoginRequestBuilder(nickname, password)),
                new RequestExecutorCallback() {
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            session = response.getData(Session.class);
                            observer.openHome("Wellcome " + session.getUser().getNickname());
                            getContacts();
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public void signup(String nickname, String email, String password, int yearOfBirth) {
        requestExecutor.execute(new Request(new SignupRequestBuilder(nickname, email, password, yearOfBirth)),
                new RequestExecutorCallback() {
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            observer.openLogin("Successful signup");
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public void updateProfile(String nickname, String email, String password, int yearOfBirth) {
        requestExecutor.execute(new Request(
                new UpdateProfileRequestBuilder(session.getUser().getId(), nickname, email, password, yearOfBirth)),
                new RequestExecutorCallback() {
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            observer.openHome("Successful update");
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public void getContacts() {
        requestExecutor.execute(new Request(new GetContactsRequestBuilder(session.getUser().getId())),
                new RequestExecutorCallback() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            contacts = (List<User>) response.getData(List.class);
                            observer.updateContacts();
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public void addContact(String nickname) {
        requestExecutor.execute(new Request(new AddContactRequestBuilder(session.getUser().getId(), nickname)),
                new RequestExecutorCallback() {
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            observer.openHome("Successful added contact.");
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public void removeContact(int contactIndex) {
        requestExecutor.execute(new Request(new RemoveContactRequestBuilder(session.getUser().getId(), contacts.get(contactIndex).getNickname())),
                new RequestExecutorCallback() {
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            observer.openHome("Successful removed contact.");
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public int getContactsQuantity() {
        return contacts.size();
    }

    @Override
    public String getContactNickname(int index) {
        return contacts.get(index).getNickname();
    }

    @Override
    public String getContactActivity(int index) {
        String activity = "Offline";
        if (contacts.get(index).isOnline()) {
            activity = "Online";
        }
        return activity;
    }

    public ClientControllerObserver getObserver() {
        return observer;
    }

    public void setObserver(ClientControllerObserver observer) {
        this.observer = observer;
    }

}
