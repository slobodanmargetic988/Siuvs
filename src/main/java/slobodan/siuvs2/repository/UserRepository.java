package slobodan.siuvs2.repository;

import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Role;
import slobodan.siuvs2.model.User;
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
    
   List<User> findAllByMobileonlyservis( String servis);



    Page<User> findAllByClientIsNullOrderByIdAsc(Pageable pageable);

    @Transactional(readOnly = true)
    Page<User> findAllByRoles(Role role, Pageable pageable);

    @Transactional(readOnly = true)
    Page<User> findByRolesIdIn(List<Integer> id, Pageable pageable);

    Page<User> findAllByClientOrderByIdAsc(Client client, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `user` SET `password` = :pass, modified_by = :userid, modified_on = NOW() WHERE user_id = :userid", nativeQuery = true)
    void forceChangePassword(@Param("userid") String userId, @Param("pass") String password);

}
