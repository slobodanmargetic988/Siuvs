package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.LoginLog;
import org.bitbucket.pbosko.siuvs.model.User;
import java.util.List;

public interface LoginMonitorService {

    void loginFailed(String email);

    String getRemoteIp();

    long getFailedLoginCountInPast24h();

    void logSuccessfulLogin(User user);

    List<LoginLog> getLastTenLogins(User user);

}
