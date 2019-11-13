package br.com.faasachat.api.executor;

import br.com.faasachat.domain.model.Request;

/**
 * Thread decorator to request executor.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class ThreadRequestExecutor implements RequestExecutor {
    
    /**
     * Origin request executor.
     */
    private final RequestExecutor executor;
    
    /**
     * Instantiates thread request executor.
     * @param executor
     */
    public ThreadRequestExecutor(RequestExecutor executor) {
        this.executor = executor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Request request, RequestExecutorCallback callback) {
        Thread thread = new Thread(new RequestExecutorRunnable(executor, request, callback));
        thread.start();
    }

}
