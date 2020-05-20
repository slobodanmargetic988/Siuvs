package slobodan.siuvs2.service;

import slobodan.siuvs2.model.ChangeEmailEntity;
import slobodan.siuvs2.model.Role;
import slobodan.siuvs2.model.ChangePasswordEntity;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.repository.RoleRepository;
import slobodan.siuvs2.repository.UserRepository;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import slobodan.siuvs2.model.Supervising;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void setSupervising(User user, Supervising supervising) {
        user.setSupervising(supervising);
    }

    @Override
    public void updateUser(int userId, User user) {
        User existing = findUserById(userId);
        existing.setName(user.getName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        existing.setActive(user.isActive());
        clearRoles(existing);
        existing.setRoles(user.getRoles());
        userRepository.save(existing);
    }

    @Override
    public void addRole(User user, Roles newRole) {
        Set<Role> roles = user.getRoles();
        if (roles == null) {
            roles = new HashSet<>();
        }
        Role role = roleRepository.findByRole(newRole);
        roles.add(role);
        user.setRoles(roles);
    }

    public void clearRoles(User user) {
        Set<Role> roles = new HashSet<>();
        user.setRoles(roles);
    }

    @Override
    public boolean hasRole(User user, Roles role) {
        Set<Role> roles = user.getRoles();
        if (roles == null) {
            return false;
        }
        for (Role current : roles) {
            if (current.getRole().equals(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<User> findByRolesId(List<Integer> id, Pageable pageable) {

        return userRepository.findByRolesIdIn(id, pageable);
    }

    @Override
    public Page<User> findAllClientIsNullOrderByNameAscLastNameAsc(Pageable pageable) {
        return userRepository.findAllByClientIsNullOrderByIdAsc(pageable);
    }

    @Override
    public Page<User> findAllByClientOrderByNameAscLastNameAsc(Client client, Pageable pageable) {
        return userRepository.findAllByClientOrderByIdAsc(client, pageable);
    }

    public boolean performChangePassword(ChangePasswordEntity changePasswordEntity) throws SiuvsException {
        boolean result = false;
        boolean validChangeRequest = changePasswordEntity.validate();
        if (!validChangeRequest) {
            throw new SiuvsException("Невалидан захтев за промену лозинке");
        }
        User user = changePasswordEntity.getUser();
        String currentPasswordEncoded = user.getPassword();
        String oldPassword = changePasswordEntity.getOldPassword();
        String newPassword = changePasswordEntity.getNewPassword();
        boolean oldMatch = bCryptPasswordEncoder.matches(oldPassword, currentPasswordEncoded);
        if (oldMatch) {
            result = forceChangePassword(user, newPassword);
        } else {
            throw new SiuvsException("Невалидан захтев за промену лозинке");
        }
        return result;
    }

    public boolean forceChangePassword(User user, String newPassword) {
        String userId = Integer.toString(user.getId());
        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        userRepository.forceChangePassword(userId, encodedPassword);
        return true;
    }

    public boolean performChangeEmail(ChangeEmailEntity changeEmailEntity) throws SiuvsException {
        User user = changeEmailEntity.getUser();
        String currentPasswordEncoded = user.getPassword();
        String currentPasswordEntered = changeEmailEntity.getCurrentPassword();
        boolean passwordMatch = bCryptPasswordEncoder.matches(currentPasswordEntered, currentPasswordEncoded);
        if (passwordMatch) {
            user.setEmail(changeEmailEntity.getNewEmail());
            userRepository.save(user);
            return true;
        } else {
            throw new SiuvsException("Невалидан захтев за промену имејл адресе");
        }
    }

}
