package br.com.faasachat;

import br.com.faasachat.client.controller.ClientController;
import br.com.faasachat.client.controller.strategy.StrategyFactory;
import br.com.faasachat.client.view.ClientView;
import br.com.faasachat.core.Application;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        ClientController controller = new ClientController(Application.getInstance().getRequestExecutor());
        ClientView view = new ClientView(new StrategyFactory(controller));
        controller.setObserver(view);
        view.setVisible(true);
    }

}
