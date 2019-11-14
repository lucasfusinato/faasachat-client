package br.com.faasachat.client.view.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.faasachat.client.controller.strategy.LoginStrategy;
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
@SuppressWarnings("serial")
public class LoginFrame extends JInternalFrame {

    private LoginStrategy confirmStrategy;

    private JLabel lbNickname;
    private JTextField tfNickname;
    private JLabel lbPassword;
    private JTextField tfPassword;
    private JButton btLogin;

    public LoginFrame(LoginStrategy confirmStrategy) {
        this.confirmStrategy = confirmStrategy;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        tfNickname = new JTextField();
        lbNickname = new JLabel();
        tfPassword = new JTextField();
        lbPassword = new JLabel();
        btLogin = new JButton();

        tfNickname.setPreferredSize(new Dimension(100, 25));
        tfPassword.setPreferredSize(new Dimension(100, 25));

        lbNickname.setText("Nickname:");
        lbPassword.setText("Password:");
        btLogin.setText("Login");
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nickname = getNicknameValue();
                    String password = getPasswordValue();
                    getConfirmStrategy().execute(nickname, password);
                } catch(Exception ex) {
                    Application.getInstance().getExceptionCatcher().catchException(ex);
                }
            }
        });
    }
    
    private void addComponents() {
        setLayout(new FlowLayout());
        add(lbNickname);
        add(tfNickname);
        add(lbPassword);
        add(tfPassword);
        add(btLogin);
    }

    public String getNicknameValue() {
        return tfNickname.getText().trim();
    }

    public String getPasswordValue() {
        return tfPassword.getText().trim();
    }

    private LoginStrategy getConfirmStrategy() {
        return confirmStrategy;
    }
    
}
