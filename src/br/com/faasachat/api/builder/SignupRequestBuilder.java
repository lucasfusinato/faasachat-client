package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Signup request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class SignupRequestBuilder implements RequestBuilder {
    
    /**
     * Request instance.
     */
    private Request request;
    
    /**
     * User nickname to signup.
     */
    private String nickname;
    
    /**
     * User email to signup.
     */
    private String email;
    
    /**
     * User password to signup.
     */
    private String password;
    
    /**
     * User year of birth to signup.
     */
    private Integer yearOfBirth;
    
    /**
     * Instantiates signup request builder.
     * @param nickname
     * @param email
     * @param password
     * @param yearOfBirth
     */
    public SignupRequestBuilder(String nickname, String email, String password, Integer yearOfBirth) {
        this.nickname    = nickname;
        this.email       = email;
        this.password    = password;
        this.yearOfBirth = yearOfBirth;
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
        request.setResource("/users");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildMethod() {
        request.setMethod("signup");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildParameters() {
        request.setParameter("nickname",    nickname);
        request.setParameter("email",       email);
        request.setParameter("password",    password);
        request.setParameter("yearOfBirth", yearOfBirth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Request getRequest() {
        return request;
    }

}
