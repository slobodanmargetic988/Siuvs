package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.PasswordResetToken;
import slobodan.siuvs2.model.User;

public interface PasswordResetTokenService {

    PasswordResetToken findOneByToken(String token);

    PasswordResetToken findValidToken(String token);

    void invalidateToken(PasswordResetToken passwordResetToken);

    void save(PasswordResetToken passwordResetToken);

    void delete(PasswordResetToken passwordResetToken);

    PasswordResetToken generateNew(User user);

}
