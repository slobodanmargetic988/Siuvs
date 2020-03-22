/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Mera;
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.repository.PodRezultatRepository;
import org.bitbucket.pbosko.siuvs.repository.RezultatRepository;
import org.bitbucket.pbosko.siuvs.valueObject.RezultatID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RezultatServiceImpl implements RezultatService{
    @Autowired
    private RezultatRepository rezultatRepository;
    /*
    @Autowired
    private PodRezultatRepository podRezultatRepository;
    @Autowired
    private PodRezultatFactory podRezultatFactory;
    */
    @Override
    public Rezultat findOne(RezultatID rezultatID) {
        return rezultatRepository.findOne(rezultatID.getValue());
    }

    @Override
    public void save(Rezultat rezultat) {
        
       // podRezultatRepository.save(podRezultatFactory.empty(rezultat));
        rezultatRepository.save(rezultat);
        
    }
         @Override
   // @Transactional
    public void delete(RezultatID rezultatID){
   rezultatRepository.delete(rezultatID.getValue());
    };
}
