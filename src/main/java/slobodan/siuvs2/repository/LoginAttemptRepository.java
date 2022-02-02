package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Integer> {

    long countByIpAndCreatedOnGreaterThanEqual(String ip, LocalDateTime createdOn);

}
