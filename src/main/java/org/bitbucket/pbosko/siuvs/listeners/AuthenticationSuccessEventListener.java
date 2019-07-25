package org.bitbucket.pbosko.siuvs.listeners;

import org.bitbucket.pbosko.siuvs.model.SiuvsUserPrincipal;
import org.bitbucket.pbosko.siuvs.model.User;
import org.bitbucket.pbosko.siuvs.service.LoginMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginMonitorService loginMonitorService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        User user = ((SiuvsUserPrincipal)authentication.getPrincipal()).getUser();
        loginMonitorService.logSuccessfulLogin(user);
    }
}
