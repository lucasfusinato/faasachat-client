package br.com.faasachat.api.executor;

import br.com.faasachat.domain.model.Request;

/**
 * Request executor interface.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public interface RequestExecutor {
    
    /**
     * Executes a request.
     * @param request
     * @param callback
     */
    public void execute(Request request, RequestExecutorCallback callback);

}
