package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Add contact request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class AddContactRequestBuilder implements RequestBuilder {
    
    /**
     * Request instance.
     */
    private Request request;
    
    /**
     * User that wants to add a contact.
     */
    private Long userId;
    
    /**
     * Contact nickname to add.
     */
    private String contactNickname;
    
    /**
     * Instantiates add contact request builder.
     * @param userId
     * @param contactNickname
     */
    public AddContactRequestBuilder(Long userId, String contactNickname) {
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
        request.setMethod("addContacts");
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
