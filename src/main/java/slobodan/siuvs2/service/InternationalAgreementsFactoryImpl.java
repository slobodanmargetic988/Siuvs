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
import slobodan.siuvs2.model.InternationalAgreements;
import org.springframework.stereotype.Service;

@Service
public class InternationalAgreementsFactoryImpl implements InternationalAgreementsFactory {

    @Override
    public InternationalAgreements empty(String name) {
        InternationalAgreements internationalAgreements = new InternationalAgreements();
        internationalAgreements.setName(name);
        return internationalAgreements;
    }

}
