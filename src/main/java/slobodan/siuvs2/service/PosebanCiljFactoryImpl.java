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
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosebanCiljFactoryImpl implements PosebanCiljFactory{
  @Autowired
    private MeraFactory meraFactory;
  @Autowired
    private MeraService meraService;
@Override
    public PosebanCilj empty(Plan plan){
    
       PosebanCilj posebanCilj = new PosebanCilj();
        posebanCilj.setPlan(plan);
        posebanCilj.setPosebanCiljText("Нови посебан циљ");
        posebanCilj.setIndikator("/");
        posebanCilj.setIndikatorCv("/");
        posebanCilj.setIndikatorPv("/");
        posebanCilj.setRedosled(0);
        /*
        Mera mera=meraFactory.empty(posebanCilj);
        meraService.save(mera);
        List<Mera> meralist=new ArrayList();
        meralist.add(mera);
        posebanCilj.setChildren(meralist);
        */
        return posebanCilj;
}
    
}
