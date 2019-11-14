package br.com.faasachat.client.view.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.faasachat.client.controller.strategy.SignupStrategy;
import br.com.faasachat.core.Application;

/**
 * 
 * @author João Victor Arruda
 * @since 13/11/2019
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UserFrame extends JInternalFrame {

    private SignupStrategy confirmStrategy;
    
    private JLabel lbNickname;
    private JTextField tfNickname;
    private JLabel lbEmail;
    private JTextField tfEmail;
    private JLabel lbPassword;
    private JTextField tfPassword;
    private JLabel lbYearOfBirth;
    private JTextField tfYearOfBirth;
    private JButton btConfirm;

    public UserFrame(SignupStrategy confirmStrategy) {
        this.confirmStrategy = confirmStrategy;
        initComponentes();
        addComponents();
    }

    private void initComponentes() {
        tfNickname = new JTextField();
        lbNickname = new JLabel();
        tfEmail = new JTextField();
        lbEmail = new JLabel();
        btConfirm = new JButton();
        tfPassword = new JTextField();
        lbPassword = new JLabel();
        tfYearOfBirth = new JTextField();
        lbYearOfBirth = new JLabel();
        
        tfNickname.setPreferredSize(new Dimension(100, 25));
        tfEmail.setPreferredSize(new Dimension(100, 25));
        tfPassword.setPreferredSize(new Dimension(100, 25));
        tfYearOfBirth.setPreferredSize(new Dimension(100, 25));

        lbNickname.setText("Nickname:");
        lbEmail.setText("Email:");
        lbPassword.setText("Password:");
        lbYearOfBirth.setText("Year of Birth:");

        btConfirm.setText("Confirm");
        btConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nickname = getNicknameValue();
                    String email = getEmailValue();
                    String password = getPasswordValue();
                    Integer yearOfBirth = getYearOfBirthValue();
                    getConfirmStrategy().execute(nickname, email, password, yearOfBirth);
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
        add(lbEmail);
        add(tfEmail);
        add(lbPassword);
        add(tfPassword);
        add(lbYearOfBirth);
        add(tfYearOfBirth);
        add(btConfirm);
    }

    public String getNicknameValue() {
        return tfNickname.getText().trim();
    }

    public String getEmailValue() {
        return tfEmail.getText().trim();
    }

    public String getPasswordValue() {
        return tfPassword.getText().trim();
    }

    public Integer getYearOfBirthValue() {
        Integer yearOfBirth = null;
        String value = tfYearOfBirth.getText().trim();
        if(!value.isEmpty()) {
            yearOfBirth = Integer.parseInt(value);
        }
        return yearOfBirth;
    }

    public SignupStrategy getConfirmStrategy() {
        return confirmStrategy;
    }
    
}
