/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */


import slobodan.siuvs2.model.PublicPolicyDocuments;
import org.springframework.stereotype.Service;

@Service
public class PublicPolicyDocumentsFactoryImpl implements PublicPolicyDocumentsFactory {

    @Override
    public PublicPolicyDocuments empty(String name) {
        PublicPolicyDocuments publicPolicyDocuments = new PublicPolicyDocuments();
        publicPolicyDocuments.setName(name);
        return publicPolicyDocuments;
    }

}
