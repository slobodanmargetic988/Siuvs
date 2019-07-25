package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.shared.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidationService {

    @Autowired
    private KnownPasswordsValidationService knownPasswordsValidationService;

    public void validate(String password, String passwordRepeat) throws PasswordException {
        if (!password.equals(passwordRepeat)) {
            throw new PasswordException("Лозинка и поновљена лозинка се не поклапају");
        }
        validate(password);
    }

    public void validate(String password) throws PasswordException {
        if (password.length() < 8) {
            throw new PasswordException("Лозинка мора да садржи минимум осам знакова");
        }
        if (knownPasswordsValidationService.contains(password)) {
            throw new PasswordException("Лозинка је превише једноставна");
        }
    }

}
