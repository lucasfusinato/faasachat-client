package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Get contacts request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class GetContactsRequestBuilder implements RequestBuilder {
    
    /**
     * Request instance.
     */
    private Request request;
    
    /**
     * User that wants to get contacts.
     */
    private Long userId;
    
    /**
     * Instantiates get contacts request builder.
     * @param userId
     */
    public GetContactsRequestBuilder(Long userId) {
        this.userId = userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        request = new Request();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildResource() {
        request.setResource("/contacts");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildMethod() {
        request.setMethod("getContacts");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildParameters() {
        request.setParameter("userId", userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Request getRequest() {
        return request;
    }

}
