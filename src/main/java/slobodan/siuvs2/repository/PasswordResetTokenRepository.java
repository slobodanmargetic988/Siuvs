package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    public PasswordResetToken findOneByToken(String token);

}
