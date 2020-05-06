/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.InternationalAgreements;
import org.springframework.stereotype.Service;

@Service
public class InternationalAgreementsFactoryImpl implements InternationalAgreementsFactory{

  
    @Override
    public InternationalAgreements empty(String name) {
        InternationalAgreements internationalAgreements = new InternationalAgreements();
internationalAgreements.setName(name);
        return internationalAgreements;
    }
    
}
