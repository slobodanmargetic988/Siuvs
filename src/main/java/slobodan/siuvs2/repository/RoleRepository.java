package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Role;
import slobodan.siuvs2.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(Roles role);

}
