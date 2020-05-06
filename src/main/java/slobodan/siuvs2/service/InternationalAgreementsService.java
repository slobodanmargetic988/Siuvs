/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;


public interface InternationalAgreementsService {
    InternationalAgreements findOne(InternationalAgreementsID internationalAgreementsID);

    void save(InternationalAgreements InternationalAgreements);
    void delete(InternationalAgreementsID internationalAgreementsID);
}
