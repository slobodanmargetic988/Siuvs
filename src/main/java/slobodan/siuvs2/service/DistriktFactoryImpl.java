/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.Distrikt;
import org.springframework.stereotype.Service;

@Service
public class DistriktFactoryImpl implements DistriktFactory{

  
    @Override
    public Distrikt empty(String name) {
        Distrikt distrikt = new Distrikt();
distrikt.setName(name);
        return distrikt;
    }
    
}
