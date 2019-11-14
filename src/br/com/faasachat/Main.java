package br.com.faasachat;

import br.com.faasachat.client.controller.ClientController;
import br.com.faasachat.client.controller.strategy.StrategyFactory;
import br.com.faasachat.client.view.ClientView;
import br.com.faasachat.core.Application;

<<<<<<< HEAD
=======
/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
>>>>>>> f69c15242aa715388a80b5103c0f0a35e428ac43
public class Main {

    public static void main(String[] args) {
        ClientController controller = new ClientController(Application.getInstance().getRequestExecutor());
        ClientView view = new ClientView(new StrategyFactory(controller));
        controller.setObserver(view);
        view.setVisible(true);
    }

}
