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
import slobodan.siuvs2.repository.InternationalAgreementsRepository;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternationalAgreementsServiceImpl implements InternationalAgreementsService {

    @Autowired
    InternationalAgreementsRepository internationalAgreementsRepository;

    @Override
    public InternationalAgreements findOne(InternationalAgreementsID internationalAgreementsID) {
        return internationalAgreementsRepository.findOne(internationalAgreementsID.getValue());
    }

    @Override
    public void save(InternationalAgreements distrikt) {

        internationalAgreementsRepository.save(distrikt);
    }

    @Override
    public void delete(InternationalAgreementsID internationalAgreementsID) {
        internationalAgreementsRepository.delete(internationalAgreementsID.getValue());
    }
;
}
