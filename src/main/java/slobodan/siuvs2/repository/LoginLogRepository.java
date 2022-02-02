package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.LoginLog;
import slobodan.siuvs2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {

    List<LoginLog> findTop10ByUserOrderByCreatedOnDesc(User user);

}
