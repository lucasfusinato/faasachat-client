package br.com.faasachat.domain.model;

import java.time.LocalDateTime;

/**
 * Model that represents user session.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class Session {
    
    /**
     * Session id.
     */
    private long id;
    
    /**
     * User.
     */
    private User user;
    
    /**
     * Last time that session was updated.
     */
    private LocalDateTime lastUpdate;
    
    /**
     * Session active flag.
     */
    private boolean active;
    
    /**
     * Session token.
     */
    private String token;
    
    /**
     * Returns session id.
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Defines session id.
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns user.
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Defines user.
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns last time updated.
     * @return
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Defines last time updated.
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns session active flag.
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Defines session active flag.
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns session token.
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Defines session token.
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
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
        Session other = (Session) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
