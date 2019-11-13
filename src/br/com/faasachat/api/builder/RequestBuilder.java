package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Request builder interface.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public interface RequestBuilder {
    
    /**
     * Resets request object instance.
     */
    void reset();
    
    /**
     * Build request resource.
     */
    void buildResource();

    /**
     * Build request method.
     */
    void buildMethod();

    /**
     * Build request parameters.
     */
    void buildParameters();

    /**
     * Returns request instance.
     * @return
     */
    Request getRequest();
    
}
