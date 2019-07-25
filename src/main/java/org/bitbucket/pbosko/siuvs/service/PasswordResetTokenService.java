package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.PasswordResetToken;
import org.bitbucket.pbosko.siuvs.model.User;

public interface PasswordResetTokenService {

    PasswordResetToken findOneByToken(String token);

    PasswordResetToken findValidToken(String token);

    void invalidateToken(PasswordResetToken passwordResetToken);

    void save(PasswordResetToken passwordResetToken);

    void delete(PasswordResetToken passwordResetToken);

    PasswordResetToken generateNew(User user);

}
