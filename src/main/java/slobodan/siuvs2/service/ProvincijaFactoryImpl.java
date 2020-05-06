/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.Provincija;
import org.springframework.stereotype.Service;

@Service
public class ProvincijaFactoryImpl implements ProvincijaFactory{

  
    @Override
    public Provincija empty(String name) {
        Provincija provincija = new Provincija();
provincija.setName(name);
        return provincija;
    }
    
}
