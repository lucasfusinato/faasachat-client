package br.com.faasachat.api.executor;

import br.com.faasachat.domain.model.Response;

/**
 * Request executor callback.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public interface RequestExecutorCallback {
    
    /**
     * Callback event of request execution.
     * @param response
     */
    void call(Response response);

}
