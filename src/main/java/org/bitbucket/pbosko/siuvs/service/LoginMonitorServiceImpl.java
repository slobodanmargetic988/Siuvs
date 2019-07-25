package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.LoginAttempt;
import org.bitbucket.pbosko.siuvs.model.LoginLog;
import org.bitbucket.pbosko.siuvs.model.User;
import org.bitbucket.pbosko.siuvs.repository.LoginAttemptRepository;
import org.bitbucket.pbosko.siuvs.repository.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginMonitorServiceImpl implements LoginMonitorService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    @Autowired
    private LoginLogRepository loginLogRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void loginFailed(String email) {
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setIp(getRemoteIp());
        loginAttempt.setEmail(email);
        loginAttemptRepository.save(loginAttempt);
    }

    @Override
    public String getRemoteIp() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public long getFailedLoginCountInPast24h() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return loginAttemptRepository.countByIpAndCreatedOnGreaterThanEqual(getRemoteIp(), yesterday);
    }

    @Override
    public void logSuccessfulLogin(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(getRemoteIp());
        loginLog.setUser(user);
        loginLogRepository.save(loginLog);
    }

    public List<LoginLog> getLastTenLogins(User user) {
        return loginLogRepository.findTop10ByUserOrderByCreatedOnDesc(user);
    }

}
