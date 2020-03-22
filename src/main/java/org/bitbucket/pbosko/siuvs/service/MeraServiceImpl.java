/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Mera;
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;
import org.bitbucket.pbosko.siuvs.repository.MeraRepository;
import org.bitbucket.pbosko.siuvs.repository.RezultatRepository;
import org.bitbucket.pbosko.siuvs.valueObject.MeraID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeraServiceImpl implements MeraService{
    @Autowired
    private MeraRepository meraRepository;
    /*
    @Autowired
    private RezultatRepository rezultatRepository;
    @Autowired
    private RezultatFactory rezultatFactory;
    */
        @Override
    public Mera findOne(MeraID meraID) {
        return meraRepository.findOne(meraID.getValue());
    }

    @Override
    public void save(Mera mera) {
        //rezultatRepository.save(rezultatFactory.empty(mera));
        meraRepository.save(mera);
    }
    
            @Override
   // @Transactional
    public void delete(MeraID meraID){
   meraRepository.delete(meraID.getValue());
    };
}
