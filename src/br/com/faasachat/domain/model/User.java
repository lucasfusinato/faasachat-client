package br.com.faasachat.domain.model;

/**
 * Model that represents user data.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class User {
    
    /**
     * User id.
     */
    private long id;
    
    /**
     * User nickname.
     */
    private String nickname;
    
    /**
     * User email.
     */
    private String email;
    
    /**
     * User password.
     */
    private String password;
    
    /**
     * User year of birth.
     */
    private int yearOfBirth;

    /**
     * Returns user id.
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Defines user id.
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns user nickname.
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Defines user nickname.
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Returns user email.
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Defines user email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns user password.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Defines user password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user year of birth.
     * @return
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Defines user year of birth.
     * @param yearOfBirth
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
