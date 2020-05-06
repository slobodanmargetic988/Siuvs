/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.PublicPolicyDocuments;
import org.springframework.stereotype.Service;

@Service
public class PublicPolicyDocumentsFactoryImpl implements PublicPolicyDocumentsFactory{

  
    @Override
    public PublicPolicyDocuments empty(String name) {
        PublicPolicyDocuments publicPolicyDocuments = new PublicPolicyDocuments();
publicPolicyDocuments.setName(name);
        return publicPolicyDocuments;
    }
    
}
