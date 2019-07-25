package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.LoginLog;
import org.bitbucket.pbosko.siuvs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {

    List<LoginLog> findTop10ByUserOrderByCreatedOnDesc(User user);

}
