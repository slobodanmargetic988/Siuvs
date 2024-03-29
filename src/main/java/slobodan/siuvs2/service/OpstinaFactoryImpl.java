/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;
import org.springframework.stereotype.Service;

@Service
public class OpstinaFactoryImpl implements OpstinaFactory {

    @Override
    public Opstina empty(Distrikt distrikt, Provincija provincija) {
        Opstina opstina = new Opstina();
        opstina.setDistrikt(distrikt);
        opstina.setProvincija(provincija);
        opstina.setName("");
          opstina.setNamelatinica("");
        return opstina;
    }

}
