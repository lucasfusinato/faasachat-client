package br.com.faasachat.client.controller;

public class ClientControllerUpdateContactsThread extends Thread {

    private static final long SEARCH_INTERVAL = 5000;
    private ClientControllerInterface controller;

    public ClientControllerUpdateContactsThread(ClientControllerInterface controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                controller.getContacts();
                Thread.sleep(SEARCH_INTERVAL);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}
