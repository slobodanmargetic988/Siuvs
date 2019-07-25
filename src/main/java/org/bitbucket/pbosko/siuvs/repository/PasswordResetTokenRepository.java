package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    public PasswordResetToken findOneByToken(String token);

}
