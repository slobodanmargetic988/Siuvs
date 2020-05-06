/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;


public interface PublicPolicyDocumentsService {
    PublicPolicyDocuments findOne(PublicPolicyDocumentsID publicPolicyDocumentsID);

    void save(PublicPolicyDocuments PublicPolicyDocuments);
    void delete(PublicPolicyDocumentsID publicPolicyDocumentsID);
}
