package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Login request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class LoginRequestBuilder implements RequestBuilder {
    
    /**
     * Request instance.
     */
    private Request request;
    
    /**
     * User nickname to login.
     */
    private String nickname;
    
    /**
     * User password to login.
     */
    private String password;
    
    /**
     * Instantiates login request builder.
     * @param nickname
     * @param password
     */
    public LoginRequestBuilder(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
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
        request.setResource("/sessions");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildMethod() {
        request.setMethod("login");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildParameters() {
        request.setParameter("nickname", nickname);
        request.setParameter("password", password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Request getRequest() {
        return request;
    }

}
