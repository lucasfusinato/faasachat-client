package br.com.faasachat.api.executor;

import br.com.faasachat.domain.model.Request;

/**
 * Runnable to executes a request.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class RequestExecutorRunnable implements Runnable {

    /**
     * Request executor.
     */
    private final RequestExecutor executor;
    
    /**
     * Request to execute.
     */
    private final Request request;
    
    /**
     * Callback to calls on request complete.
     */
    private final RequestExecutorCallback callback;
    
    /**
     * Instantiates request executor runnable.
     * @param executor
     * @param request
     * @param callback
     */
    public RequestExecutorRunnable(RequestExecutor executor, Request request, RequestExecutorCallback callback) {
        this.executor = executor;
        this.request = request;
        this.callback = callback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        executor.execute(request, callback);
    }

}
