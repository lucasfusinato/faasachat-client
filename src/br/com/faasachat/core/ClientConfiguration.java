package br.com.faasachat.core;

import br.com.faasachat.domain.utils.Configuration;

/**
 * Proxy to access client configurations from a common configuration object.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class ClientConfiguration {
    
    /**
     * Configuration original instance.
     */
    private final Configuration configuration;
    
    /**
     * Instantiates client configuration.
     * @param configuration
     */
    public ClientConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Returns server host value.
     * @return the serverHost
     */
    public String getServerHost() {
        return configuration.getParameter("serverHost", String.class);
    }

    /**
     * Defines server host value.
     * @param serverHost the serverHost to set
     */
    public void setServerHost(String serverHost) {
        configuration.setParameter("serverHost", serverHost);
    }

    /**
     * Returns server port value.
     * @return the serverPort
     */
    public Integer getServerPort() {
        return configuration.getParameter("serverPort", Integer.class);
    }

    /**
     * Defines server port value.
     * @param serverPort the serverPort to set
     */
    public void setServerPort(Integer serverPort) {
        configuration.setParameter("serverPort", serverPort);
    }

    /**
     * Defines if application must be display errors in console.
     * @return
     */
    public Boolean getDisplayErrors() {
        return configuration.getParameter("displayErrors", Boolean.class);
    }

    /**
     * Defines if application must be display errors in console.
     * @param displayErrors
     */
    public void setDisplayErrors(Boolean displayErrors) {
        configuration.setParameter("displayErrors", displayErrors);
    }

}
