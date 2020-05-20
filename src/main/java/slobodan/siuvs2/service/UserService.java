package slobodan.siuvs2.service;

import slobodan.siuvs2.model.ChangeEmailEntity;
import slobodan.siuvs2.model.ChangePasswordEntity;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.User;
import java.util.List;
import slobodan.siuvs2.shared.SiuvsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import slobodan.siuvs2.model.Supervising;

public interface UserService {

    User findUserByEmail(String email);

    User findUserById(Integer userId);

    void saveNewUser(User user);

    void updateUser(int userId, User user);

    void addRole(User user, Roles newRole);

    void setSupervising(User user, Supervising supervising);

    void clearRoles(User user);

    boolean hasRole(User user, Roles role);

    Page<User> findByRolesId(List<Integer> id, Pageable pageable);

    Page<User> findAllClientIsNullOrderByNameAscLastNameAsc(Pageable pageable);

    Page<User> findAllByClientOrderByNameAscLastNameAsc(Client client, Pageable pageable);

    boolean performChangePassword(ChangePasswordEntity changePasswordEntity) throws SiuvsException;

    boolean forceChangePassword(User user, String newPassword);

    boolean performChangeEmail(ChangeEmailEntity changeEmailEntity) throws SiuvsException;

}
