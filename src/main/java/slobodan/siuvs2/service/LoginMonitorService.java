package slobodan.siuvs2.service;

import slobodan.siuvs2.model.LoginLog;
import slobodan.siuvs2.model.User;
import java.util.List;

public interface LoginMonitorService {

    void loginFailed(String email);

    String getRemoteIp();

    long getFailedLoginCountInPast24h();

    void logSuccessfulLogin(User user);

    List<LoginLog> getLastTenLogins(User user);

}
