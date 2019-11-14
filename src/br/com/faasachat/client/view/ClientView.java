package br.com.faasachat.client.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import br.com.faasachat.client.controller.ClientControllerObserver;
import br.com.faasachat.client.controller.strategy.StrategyFactory;
import br.com.faasachat.client.view.frames.ContactsFrame;
import br.com.faasachat.client.view.frames.LoginFrame;
import br.com.faasachat.client.view.frames.UserFrame;
import br.com.faasachat.core.Application;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ClientView extends JFrame implements ClientControllerObserver {
    
    private StrategyFactory strategyFactory;
    private ContactsTableModel contactsTableModel;

    public ClientView(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
        this.contactsTableModel = new ContactsTableModel(strategyFactory.createGetContactsStrategy());
        init();
    }
    
    private void init() {
        setTitle("Faasachat");
        setSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        openLogin(null);
    }

    @Override
    public void openLogin(String message) {
        setInternalFrame(new LoginFrame(strategyFactory.createLoginStrategy()));
        if(message != null) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public void openSignup() {
        setInternalFrame(new UserFrame(strategyFactory.createSignupStrategy()));
    }

    @Override
    public void openHome(String message) {
        setInternalFrame(new ContactsFrame(contactsTableModel, strategyFactory.createAddContactStrategy(), strategyFactory.createRemoveContactStrategy()));
        if(message != null) {
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public void openProfile() {
        setInternalFrame(new UserFrame(strategyFactory.createUpdateProfileStrategy()));
    }

    @Override
    public void updateContacts() {
        contactsTableModel.update();
    }

    @Override
    public void catchException(Exception e) {
        Application.getInstance().getExceptionCatcher().catchException(e);
    }
    
    private void setInternalFrame(JInternalFrame frame) {
        JPanel panel = new JPanel();
        frame.setBorder(null);
        ((BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
        frame.setVisible(true);
        panel.add(frame);
        setContentPane(panel);
    }
    
}
