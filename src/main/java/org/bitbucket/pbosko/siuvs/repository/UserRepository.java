package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findById(Integer userId);

    Page<User> findAllByClientIsNullOrderByNameAscLastNameAsc(Pageable pageable);

    Page<User> findAllByClientOrderByNameAscLastNameAsc(Client client, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `user` SET `password` = :pass, modified_by = :userid, modified_on = NOW() WHERE user_id = :userid", nativeQuery = true)
    void forceChangePassword(@Param("userid") String userId, @Param("pass") String password);

}
