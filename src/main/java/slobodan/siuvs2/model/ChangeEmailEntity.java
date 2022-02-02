package slobodan.siuvs2.model;

import org.hibernate.validator.constraints.Email;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public class ChangeEmailEntity {

    private User user;

    private String currentPassword;

    @Email(message = "* Молимо унесите валидну имејл адресу")
    private String newEmail;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public boolean validate() {
        return getNewEmail().length() > 0;
    }
}
