package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Remove contact request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class RemoveContactRequestBuilder implements RequestBuilder {
    
    /**
     * Request instance.
     */
    private Request request;
    
    /**
     * User that wants to remove a contact.
     */
    private Long userId;
    
    /**
     * Contact nickname to remove.
     */
    private String contactNickname;
    
    /**
     * Instantiates remove contact request builder.
     * @param userId
     * @param contactNickname
     */
    public RemoveContactRequestBuilder(Long userId, String contactNickname) {
        this.userId          = userId;
        this.contactNickname = contactNickname;
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
        request.setMethod("removeContact");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildParameters() {
        request.setParameter("userId",          userId);
        request.setParameter("contactNickname", contactNickname);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Request getRequest() {
        return request;
    }

}
