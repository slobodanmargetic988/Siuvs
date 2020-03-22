/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.PodRezultat;
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bitbucket.pbosko.siuvs.repository.PodRezultatRepository;
import org.bitbucket.pbosko.siuvs.valueObject.PodRezultatID;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PodRezultatServiceImpl implements PodRezultatService{
    @Autowired
    private PodRezultatRepository podRezultatRepository;
    
        @Override
    public PodRezultat findOne(PodRezultatID podRezultatID) {
        return podRezultatRepository.findOne(podRezultatID.getValue());
    }

    @Override
   // @Transactional
    public void save(PodRezultat podRezultat) {
        podRezultatRepository.save(podRezultat);

    }
     @Override
   // @Transactional
    public void delete(PodRezultatID podRezultatID){
    podRezultatRepository.delete(podRezultatID.getValue());
    };
}
