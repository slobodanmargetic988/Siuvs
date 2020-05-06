/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import java.util.List;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.valueObject.ProvincijaID;


public interface ProvincijaService {
    Provincija findOne(ProvincijaID provincijaID);
List<Provincija> findAllOrderByNameAsc();
    void save(Provincija Provincija);
    void delete(ProvincijaID provincijaID);
    Provincija findFirstByName(String name);
}
