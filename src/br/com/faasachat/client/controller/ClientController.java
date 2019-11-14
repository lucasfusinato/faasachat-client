package br.com.faasachat.client.controller;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
import java.util.List;

import br.com.faasachat.api.builder.AddContactRequestBuilder;
import br.com.faasachat.api.builder.GetContactsRequestBuilder;
import br.com.faasachat.api.builder.LoginRequestBuilder;
import br.com.faasachat.api.builder.RemoveContactRequestBuilder;
import br.com.faasachat.api.builder.SignupRequestBuilder;
import br.com.faasachat.api.builder.UpdateProfileRequestBuilder;
import br.com.faasachat.api.executor.RequestExecutor;
import br.com.faasachat.api.executor.RequestExecutorCallback;
<<<<<<< HEAD
import br.com.faasachat.api.executor.SessionRequestExecutor;
import br.com.faasachat.domain.adapter.GsonAdapter;
=======
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;
import br.com.faasachat.domain.model.Session;
import br.com.faasachat.domain.model.User;

<<<<<<< HEAD
=======
/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
public class ClientController implements ClientControllerInterface {

    private RequestExecutor requestExecutor;
    private ClientControllerObserver observer;
    private Session session;
    private List<User> contacts;

    public ClientController(RequestExecutor requestExecutor) {
<<<<<<< HEAD
        this(requestExecutor, null);
=======
        this.requestExecutor = requestExecutor;
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
    }
    
    public ClientController(RequestExecutor requestExecutor, ClientControllerObserver observer) {
        this.requestExecutor = requestExecutor;
        this.observer = observer;
<<<<<<< HEAD
        this.contacts = new ArrayList<>();
=======
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
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
<<<<<<< HEAD
                            new ClientControllerUpdateContactsThread(ClientController.this).start();;
=======
                            getContacts();
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
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
<<<<<<< HEAD
        new SessionRequestExecutor(requestExecutor, session).execute(new Request(new UpdateProfileRequestBuilder(session.getUser().getId(), nickname, email, password, yearOfBirth)),
=======
        requestExecutor.execute(new Request(
                new UpdateProfileRequestBuilder(session.getUser().getId(), nickname, email, password, yearOfBirth)),
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
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
<<<<<<< HEAD
        new SessionRequestExecutor(requestExecutor, session).execute(new Request(new GetContactsRequestBuilder(session.getUser().getId())),
                new RequestExecutorCallback() {
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            @SuppressWarnings("unchecked")
                            List<Object> data = response.getData(List.class);
                            contacts = new ArrayList<>();
                            for(Object object : data) {
                                contacts.add(GsonAdapter.getInstance().fromJson(GsonAdapter.getInstance().toJson(object), User.class));
                            }
=======
        requestExecutor.execute(new Request(new GetContactsRequestBuilder(session.getUser().getId())),
                new RequestExecutorCallback() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void call(Response response) {
                        if (response.isSuccess()) {
                            contacts = (List<User>) response.getData(List.class);
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
                            observer.updateContacts();
                        } else {
                            observer.catchException(response.getData(Exception.class));
                        }
                    }
                });
    }

    @Override
    public void addContact(String nickname) {
<<<<<<< HEAD
        new SessionRequestExecutor(requestExecutor, session).execute(new Request(new AddContactRequestBuilder(session.getUser().getId(), nickname)),
=======
        requestExecutor.execute(new Request(new AddContactRequestBuilder(session.getUser().getId(), nickname)),
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
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
<<<<<<< HEAD
        new SessionRequestExecutor(requestExecutor, session).execute(new Request(new RemoveContactRequestBuilder(session.getUser().getId(), contacts.get(contactIndex).getNickname())),
=======
        requestExecutor.execute(new Request(new RemoveContactRequestBuilder(session.getUser().getId(), contacts.get(contactIndex).getNickname())),
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
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
