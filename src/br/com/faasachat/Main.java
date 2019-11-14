package br.com.faasachat;

import br.com.faasachat.client.controller.ClientController;
import br.com.faasachat.client.controller.strategy.StrategyFactory;
import br.com.faasachat.client.view.ClientView;
import br.com.faasachat.core.Application;

public class Main {

    public static void main(String[] args) {
        ClientController controller = new ClientController(Application.getInstance().getRequestExecutor());
        ClientView view = new ClientView(new StrategyFactory(controller));
        controller.setObserver(view);
        view.setVisible(true);
    }

}
