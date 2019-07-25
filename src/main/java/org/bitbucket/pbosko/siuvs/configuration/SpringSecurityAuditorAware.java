package org.bitbucket.pbosko.siuvs.configuration;

import org.bitbucket.pbosko.siuvs.model.SiuvsUserPrincipal;
import org.bitbucket.pbosko.siuvs.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return ((SiuvsUserPrincipal)authentication.getPrincipal()).getUser();
    }

}
