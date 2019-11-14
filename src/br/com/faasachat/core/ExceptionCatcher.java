package br.com.faasachat.core;

import javax.swing.JOptionPane;

/**
 * Application default exception catcher.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 13/11/2019
 * @version 1.0
 */
public class ExceptionCatcher {

    /**
     * Catches a exception.
     * @param e
     */
    public void catchException(Exception e) {
        String message = e.getMessage();
        if(message.isEmpty()) {
            message = e.getClass().getName();
        }
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
