package br.com.faasachat.domain.model;

/**
 * Model that represents a contact between two users.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class Contact {
    
    /**
     * User.
     */
    private User user;

    /**
     * User contact.
     */
    private User contact;

    /**
     * Returns user.
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
     * Returns user contact.
     * @return
     */
    public User getContact() {
        return contact;
    }

    /**
     * Defines user contact.
     * @param contact
     */
    public void setContact(User contact) {
        this.contact = contact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Contact other = (Contact) obj;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}
