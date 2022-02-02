package slobodan.siuvs2.listeners;

import slobodan.siuvs2.service.LoginMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginMonitorService loginMonitorService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String username = e.getAuthentication().getName();
        loginMonitorService.loginFailed(username);
    }
}
