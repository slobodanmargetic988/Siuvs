/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import java.util.ArrayList;
import java.util.List;
import org.bitbucket.pbosko.siuvs.model.Mera;
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeraFactoryImpl implements MeraFactory{
    @Autowired
    private RezultatFactory rezultatFactory;
    @Autowired
    private RezultatService rezultatService;
     @Override
    public Mera empty(PosebanCilj posebanCilj) {
        Mera mera = new Mera();
        mera.setPosebanCilj(posebanCilj);
        mera.setMeraText("Нова мера");
        mera.setRedosled(0);
        /*
        Rezultat rezultat=rezultatFactory.empty(mera);
        rezultatService.save(rezultat);
        List<Rezultat> rezultatlist=new ArrayList();
        rezultatlist.add(rezultat);
        mera.setChildren(rezultatlist);
        */
        return mera;
    }
    
}
