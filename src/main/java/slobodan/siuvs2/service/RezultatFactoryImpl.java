/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import java.util.ArrayList;
import java.util.List;
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.model.Rezultat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RezultatFactoryImpl implements RezultatFactory{
    @Autowired
    private PodRezultatFactory podRezultatFactory;
    @Autowired
    private PodRezultatService podRezultatService;
         @Override
    public Rezultat empty(Mera mera) {
        Rezultat rezultat = new Rezultat();
        rezultat.setMera(mera);        
        rezultat.setRedosled(0);
        rezultat.setRezultatText("Нови резултат");
        /*
        PodRezultat podRezultat =podRezultatFactory.empty(rezultat);
        podRezultatService.save(podRezultat);
        List<PodRezultat> podRezultatlist=new ArrayList();
        podRezultatlist.add(podRezultat);
        rezultat.setChildren(podRezultatlist);
*/
        return rezultat;
    }
}
