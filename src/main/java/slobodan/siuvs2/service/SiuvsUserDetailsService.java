package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Role;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SiuvsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginMonitorService loginMonitorService;

    @Value("${siuvs.maxFailedLoginAttemptsPerIp}")
    private int maxFailedLoginAttemptsPerIp;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean isLocked = isClientIpBlocked();
        if (isLocked) {
            request.getSession().setAttribute("errorMessage", "Ваша ИП адреса је блокирана на 24 сата.");
        }
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (!isUserClientActive(user)) {
            isLocked = true;
            request.getSession().setAttribute("errorMessage", "Клијент је неактиван. Контактирајте администратора");
        }
        return new SiuvsUserPrincipal(user, isLocked);
    }

    private boolean isClientIpBlocked() {
        return loginMonitorService.getFailedLoginCountInPast24h() > maxFailedLoginAttemptsPerIp;
    }

    private boolean isUserClientActive(User user) {
        boolean result = true;
        Role userRole = user.getFirstRole();
        List<Roles> checkActiveClientRoles = new ArrayList<>();
        checkActiveClientRoles.add(Roles.CLIENT);
        checkActiveClientRoles.add(Roles.CLIENT_READ_ONLY);
        if (checkActiveClientRoles.contains(userRole.getRole())) {
            result = user.getClient().isActive();
        }
        return result;
    }

}
