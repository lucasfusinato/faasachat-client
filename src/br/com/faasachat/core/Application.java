package br.com.faasachat.core;

import br.com.faasachat.api.executor.RequestExecutor;
import br.com.faasachat.api.executor.SocketRequestExecutor;
import br.com.faasachat.api.executor.ThreadRequestExecutor;
import br.com.faasachat.domain.utils.ConfigurationReader;

/**
 * Main application class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class Application {

    /**
     * Self unique instance.
     */
    public static Application instance;

    /**
     * Client configurations.
     */
    private ClientConfiguration configuration;
    
    /**
     * Default request executor.
     */
    private RequestExecutor requestExecutor;

    /**
     * Default exception catcher.
     */
    private ExceptionCatcher exceptionCatcher;
    
    /**
     * Returns self unique instance.
     * @return
     */
    public static synchronized Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }
        return instance;
    }
    
    /**
     * Instantiates application main class.
     */
    private Application() {
        configuration = new ClientConfiguration(new ConfigurationReader("configuration.json").read());
    }

    /**
     * Returns default request executor.
     * @return
     */
    public RequestExecutor getRequestExecutor() {
        if(requestExecutor == null) {
            requestExecutor = new ThreadRequestExecutor(new SocketRequestExecutor(configuration.getServerHost(), configuration.getServerPort()));
        }
        return requestExecutor;
    }

    /**
     * Returns default exception catcher.
     * @return
     */
    public ExceptionCatcher getExceptionCatcher() {
        if(exceptionCatcher == null) {
            exceptionCatcher = new ExceptionCatcher(configuration.getDisplayErrors()); 
        }
        return exceptionCatcher;
    }
    
}
