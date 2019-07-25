package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.*;
import org.bitbucket.pbosko.siuvs.shared.SiuvsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findUserByEmail(String email);

    User findUserById(Integer userId);

    void saveNewUser(User user);

    void updateUser(int userId, User user);

    void addRole(User user, Roles newRole);

    void clearRoles(User user);

    boolean hasRole(User user, Roles role);

    Page<User> findAllClientIsNullOrderByNameAscLastNameAsc(Pageable pageable);

    Page<User> findAllByClientOrderByNameAscLastNameAsc(Client client, Pageable pageable);

    boolean performChangePassword(ChangePasswordEntity changePasswordEntity) throws SiuvsException;

    boolean forceChangePassword(User user, String newPassword);

    boolean performChangeEmail(ChangeEmailEntity changeEmailEntity) throws SiuvsException;


}
