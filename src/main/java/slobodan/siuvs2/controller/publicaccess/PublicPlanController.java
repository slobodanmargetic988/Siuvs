/*
 * 
 */
package slobodan.siuvs2.controller.publicaccess;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.controller.client.*;
import slobodan.siuvs2.controller.admin.clients.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import slobodan.siuvs2.model.*;
import slobodan.siuvs2.repository.MeraRepository;
import slobodan.siuvs2.repository.PlanRepository;
import slobodan.siuvs2.repository.PodRezultatRepository;
import slobodan.siuvs2.repository.PosebanCiljRepository;
import slobodan.siuvs2.repository.RezultatRepository;
import slobodan.siuvs2.service.*;
import slobodan.siuvs2.shared.SiuvsException;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.MeraID;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.PlanID;
import slobodan.siuvs2.valueObject.PodRezultatID;
import slobodan.siuvs2.valueObject.PosebanCiljID;
import slobodan.siuvs2.valueObject.RezultatID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/publicaccess/clients")
@SessionAttributes("plan")
public class PublicPlanController {

   

    @Autowired
    private ClientService clientService;

    @Autowired
    private PageService pageService;

    @Autowired
    private PlanService planService;
    @Autowired
    private MeraService meraService;
    @Autowired
    private PosebanCiljService posebanCiljService;
    @Autowired
    private PodRezultatService podRezultatService;
    @Autowired
    private RezultatService rezultatService;
    @Autowired
    private PlanFactory planFactory;

    @Autowired
    private PosebanCiljFactory posebanCiljFactory;

    @Autowired
    private MeraFactory meraFactory;

    @Autowired
    private RezultatFactory rezultatFactory;

    @Autowired
    private PodRezultatFactory podRezultatFactory;

    @Autowired
    private PodRezultatRepository podRezultatRepository;
    @Autowired
    private MeraRepository meraRepository;
    @Autowired
    private PosebanCiljRepository posebanCiljRepository;
    @Autowired
    private RezultatRepository rezultatRepository;
    @Autowired
    private PlanRepository planRepository;

    @GetMapping(value = "/{clientId}/plan/ceo/{pageId}")

    public String plan(           
                       @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
   
        
        
        Page page = pageService.findOne(pageId);
        Plan plan = planRepository.findFirstByClient(client);

      List<PosebanCilj> PClist = posebanCiljService.findAllByPlanOrderByPagePageIdAsc(plan);
       List<PosebanCilj> PClistK1 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,1);
         List<PosebanCilj> PClistK2 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,2);
          List<PosebanCilj> PClistK3 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,3);
           List<PosebanCilj> PClistK4 = posebanCiljService.findAllByClientAndPageAndKomponenta(client, page,4);
      
        String viewurl = "/publicaccess/clients/" + clientId + "/plan/ceo/" + pageId;
        List<Mera> meralist = new ArrayList();
        List<Rezultat> rezultatlist = new ArrayList();

        if (plan == null) {
            plan = planFactory.empty(client);
            model.addAttribute("planempty", true);
        } else {
            model.addAttribute("planempty", false);
        }
        if (PClist == null) {
            PClist = new ArrayList();
        } else {
            makeRezultatList(PClist, rezultatlist);
            makeMeraList(PClist, meralist);
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("plan", plan);
        model.addAttribute("PClist", PClist);
        
        model.addAttribute("PClistK1", PClistK1);
        model.addAttribute("PClistK2", PClistK2);
        model.addAttribute("PClistK3", PClistK3);
        model.addAttribute("PClistK4", PClistK4);
        model.addAttribute("meralist", meralist);
        model.addAttribute("rezultatlist", rezultatlist);
        model.addAttribute("planurl", viewurl);
        model.addAttribute("ceoplan", true);
        //sume
        List<Long> PCbudzetJls= new ArrayList();;
        List<Long>PCbudzetOstalo= new ArrayList();;
        List<Long>PCbudzetNeobezbedjeno= new ArrayList();;
        
        calculateSUMList(PClist,PCbudzetJls,PCbudzetOstalo,PCbudzetNeobezbedjeno);
        
        Long TotalbudzetJls=calculateTotals(PCbudzetJls);
        Long TotalbudzetOstalo=calculateTotals(PCbudzetOstalo);
        Long TotalbudzetNeobezbedjeno=calculateTotals(PCbudzetNeobezbedjeno);
        model.addAttribute("PCbudzetJls", PCbudzetJls);
        model.addAttribute("PCbudzetOstalo", PCbudzetOstalo);
        model.addAttribute("PCbudzetNeobezbedjeno", PCbudzetNeobezbedjeno);
        model.addAttribute("TotalbudzetJls", TotalbudzetJls);
        model.addAttribute("TotalbudzetOstalo", TotalbudzetOstalo);
        model.addAttribute("TotalbudzetNeobezbedjeno", TotalbudzetNeobezbedjeno);
        model.addAttribute("SumaLabel", "Укупно финансијска средства за цео план");

        return "publicaccess/planview";
    }
 public List<Mera> makeMeraList(List<PosebanCilj> PClist, List<Mera> meralist) {
        for (PosebanCilj pc : PClist) {
            for (Mera mera : pc.getChildren()) {
                meralist.add(mera);
            }
        }

        return meralist;
    }

    public List<Rezultat> makeRezultatList(List<PosebanCilj> PClist, List<Rezultat> rezultatlist) {
        for (PosebanCilj pc : PClist) {
            for (Mera mera : pc.getChildren()) {
                for (Rezultat rezultat : mera.getChildren()) {
                    rezultatlist.add(rezultat);
                }
            }
        }
        return rezultatlist;
    }
     private void calculateSUMList(List<PosebanCilj> PClist, List<Long> PCbudzetJls, List<Long> PCbudzetOstalo, List<Long> PCbudzetNeobezbedjeno) {
        int i = 0;
        Long middleStep;
        for (PosebanCilj pc : PClist) {
            PCbudzetJls.add(0L);
            PCbudzetOstalo.add(0L);
            PCbudzetNeobezbedjeno.add(0L);
            for (Mera mera : pc.getChildren()) {
                for (Rezultat rezultat : mera.getChildren()) {
                    for (PodRezultat podRezultat : rezultat.getChildren()) {
                        middleStep = PCbudzetJls.get(i) + podRezultat.getBudzetJls();
                        PCbudzetJls.set(i, middleStep);
                        middleStep = PCbudzetOstalo.get(i) + podRezultat.getBudzetOstalo();
                        PCbudzetOstalo.set(i, middleStep);
                        middleStep = PCbudzetNeobezbedjeno.get(i) + podRezultat.getBudzetNeobezbedjeno();
                        PCbudzetNeobezbedjeno.set(i, middleStep);
                    }
                }
            }
            i++;
        }
    }
        private Long calculateTotals(List<Long> longList) {
            Long total=0L;
for (Long longItem : longList) {
total+=longItem;
    }
return total;
        }
}


      