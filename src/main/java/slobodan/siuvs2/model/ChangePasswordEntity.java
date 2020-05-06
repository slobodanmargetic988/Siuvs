package slobodan.siuvs2.model;

import org.hibernate.validator.constraints.Length;

public class ChangePasswordEntity {

    private User user;

    private String oldPassword;

    private String newPassword;

    private String newPasswordRepeat;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public boolean validate() {
        return getNewPassword().equals(getNewPasswordRepeat());
    }
}
