/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;

public interface PublicPolicyDocumentsService {

    PublicPolicyDocuments findOne(PublicPolicyDocumentsID publicPolicyDocumentsID);

    void save(PublicPolicyDocuments PublicPolicyDocuments);

    void delete(PublicPolicyDocumentsID publicPolicyDocumentsID);
}
