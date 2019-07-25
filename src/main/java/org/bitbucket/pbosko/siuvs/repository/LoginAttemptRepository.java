package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Integer> {

    long countByIpAndCreatedOnGreaterThanEqual(String ip, LocalDateTime createdOn);

}
