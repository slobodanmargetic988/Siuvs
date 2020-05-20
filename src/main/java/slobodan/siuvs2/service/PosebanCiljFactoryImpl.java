/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Page;

@Service
public class PosebanCiljFactoryImpl implements PosebanCiljFactory {

    @Autowired
    private MeraFactory meraFactory;
    @Autowired
    private MeraService meraService;

    @Override
    public PosebanCilj empty(Plan plan, Page page) {

        PosebanCilj posebanCilj = new PosebanCilj();
        posebanCilj.setPlan(plan);
        posebanCilj.setClient(plan.getClient());
        posebanCilj.setPage(page);
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
