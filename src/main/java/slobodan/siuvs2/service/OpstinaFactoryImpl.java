/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;
import org.springframework.stereotype.Service;

@Service
public class OpstinaFactoryImpl implements OpstinaFactory{

  
    @Override
    public Opstina empty(Distrikt distrikt,Provincija provincija) {
        Opstina opstina = new Opstina();
        opstina.setDistrikt(distrikt);
        opstina.setProvincija(provincija);
        opstina.setName("");
        return opstina;
    }
    
}
