package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.PasswordResetToken;
import org.bitbucket.pbosko.siuvs.model.User;
import org.bitbucket.pbosko.siuvs.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken findOneByToken(String token) {
        return this.passwordResetTokenRepository.findOneByToken(token);
    }

    @Override
    public PasswordResetToken findValidToken(String token) {
        PasswordResetToken tokenObject = this.passwordResetTokenRepository.findOneByToken(token);
        if (tokenObject != null && tokenObject.getExpiresOn().isBefore(LocalDateTime.now())) {
            this.passwordResetTokenRepository.delete(tokenObject);
            tokenObject = null;
        }
        return tokenObject;
    }


    @Override
    public void invalidateToken(PasswordResetToken passwordResetToken) {
        this.passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        this.passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public void delete(PasswordResetToken passwordResetToken) {
        this.passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public PasswordResetToken generateNew(User user) {
        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString().replace("-", ""));
        token.setExpiresOn(LocalDateTime.now().plusDays(1));
        save(token);
        return token;
    }

}
