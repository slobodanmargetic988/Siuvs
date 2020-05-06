package slobodan.siuvs2.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;

public class SiuvsUserPrincipal implements UserDetails {

    private User user;

    private Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();

    private boolean locked;

    public SiuvsUserPrincipal(User user, boolean isLocked) {
        this.user = user;
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        locked = isLocked;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
