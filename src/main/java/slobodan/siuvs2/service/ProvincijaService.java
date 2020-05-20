/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.valueObject.ProvincijaID;

public interface ProvincijaService {

    Provincija findOne(ProvincijaID provincijaID);

    List<Provincija> findAllOrderByNameAsc();

    void save(Provincija Provincija);

    void delete(ProvincijaID provincijaID);

    Provincija findFirstByName(String name);

    Page<Provincija> findAllByOrderByNameAsc(Pageable pageable);

    Boolean isNameUsed(String name);

    Boolean isNameUsed(ProvincijaID provincijaID, String name);
}
