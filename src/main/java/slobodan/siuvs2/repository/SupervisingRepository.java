package slobodan.siuvs2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Provincija;

import slobodan.siuvs2.model.Supervising;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
//@Repository("supervisingRepository")
public interface SupervisingRepository extends JpaRepository<Supervising, Integer> {

    Supervising findFirstByDistriktId(Integer distriktId);

    Supervising findFirstByProvincijaId(Integer provincijaId);

    Supervising findFirstByDistrikt(Distrikt distrikt);

    Supervising findFirstByProvincija(Provincija provincija);

}
