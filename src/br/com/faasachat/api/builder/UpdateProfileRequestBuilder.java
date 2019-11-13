package br.com.faasachat.api.builder;

import br.com.faasachat.domain.model.Request;

/**
 * Update profile request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class UpdateProfileRequestBuilder implements RequestBuilder {
    
    /**
     * Request instance.
     */
    private Request request;
    
    /**
     * User id to update.
     */
    private Long id;
    
    /**
     * User nickname to update.
     */
    private String nickname;
    
    /**
     * User email to update.
     */
    private String email;
    
    /**
     * User password to update.
     */
    private String password;
    
    /**
     * User year of birth to update.
     */
    private Integer yearOfBirth;
    
    /**
     * Instantiates update profile request builder.
     * @param nickname
     * @param email
     * @param password
     * @param yearOfBirth
     */
    public UpdateProfileRequestBuilder(Long id, String nickname, String email, String password, Integer yearOfBirth) {
        this.id          = id;
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
        request.setMethod("updateProfile");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildParameters() {
        request.setParameter("id",          id);
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
