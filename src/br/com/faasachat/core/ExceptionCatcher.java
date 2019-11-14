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
     * Defines if its display errors in console.
     */
    private Boolean displayErrors;

    /**
     * Instantiates exception catcher.
     * @param displayErrors
     */
    public ExceptionCatcher(Boolean displayErrors) {
        this.displayErrors = displayErrors;
    }

    /**
     * Catches a exception.
     * @param e
     */
    public void catchException(Exception e) {
        if(displayErrors) {
            e.printStackTrace();
        }
        String message = e.getMessage();
        if(message == null || message.isEmpty()) {
            message = e.getClass().getName();
        }
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
