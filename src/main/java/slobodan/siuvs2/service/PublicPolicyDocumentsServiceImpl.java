/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.repository.PublicPolicyDocumentsRepository;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicPolicyDocumentsServiceImpl implements PublicPolicyDocumentsService{
@Autowired
PublicPolicyDocumentsRepository publicPolicyDocumentsRepository;

        @Override
    public PublicPolicyDocuments findOne(PublicPolicyDocumentsID publicPolicyDocumentsID) {
        return publicPolicyDocumentsRepository.findOne(publicPolicyDocumentsID.getValue());
    }

    @Override
    public void save(PublicPolicyDocuments distrikt) {
      
        publicPolicyDocumentsRepository.save(distrikt);
    }
    
            @Override
    public void delete(PublicPolicyDocumentsID publicPolicyDocumentsID){
   publicPolicyDocumentsRepository.delete(publicPolicyDocumentsID.getValue());
    };
}
