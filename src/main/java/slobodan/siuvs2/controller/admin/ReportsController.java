/*
 * 
 */
package slobodan.siuvs2.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.service.AssessmentService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.service.DynamicDataService;
import slobodan.siuvs2.service.DynamicRowService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.PodRezultatService;
import slobodan.siuvs2.valueObject.DistriktID;
import slobodan.siuvs2.valueObject.TableDefinitionId;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
//@RequestMapping(value = "/admin")
public class ReportsController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PodRezultatService podRezultatService;
    @Autowired
    private AssessmentService assessmentService;
    
    @Autowired
    private DynamicTableService dynamicTableService;
    @Autowired
    private DynamicRowService dynamicRowService;
    @Autowired
    private DynamicDataService dynamicDataService;
    
 private Integer checkPZS(Client pc) {
  //   SELECT * FROM dynamic_table where client_id=1 and table_definition_id=4;//one result
     
 //table id= dynamic table id from first query    SELECT * FROM dynamic_row where table_id=24;//get first result
  //row id= dynamic row id from first hit on second query   SELECT * FROM dynamic_data where row_id=14236;//check for blank
       TableDefinitionId id= new TableDefinitionId(4);
       DynamicTable dt=dynamicTableService.findByTableDefinitionIdAndClient(id, pc);
       DynamicRow dr=dynamicRowService.findFirstByDynamicTable(dt);
       DynamicData dd=dynamicDataService.findFirstByDynamicRow(dr);
       if (dd == null){
        return 0;}
       else{return 1;}
    }
    @GetMapping(value = "/admin/reports")
    public String adminReports(final Model model
    ) {
//broj oclijenata minus clijenti koji nisu prave opstine
        int brojOpstina = 0;
        int brojOPplanova = 0;
        int brojProcena = 0;
        int brojPZS = 29;
        List<Client> client = clientService.findAll();
        List<Client> clientBezPPD = new ArrayList();
        List<Client> clientBezAssessment = new ArrayList();
        List<Client> clientBezOpstina = new ArrayList();
        //List<Client> clientBezPZS= new ArrayList();
        for (Client pc : client) {
            if (pc.getOpstina() != null) {
                brojOpstina++;
                if (!pc.getOpstina().getPublicPolicyDocuments().isEmpty()) {
                    brojOPplanova++;
                } else {
                    clientBezPPD.add(pc);
                }
                if (assessmentService.findOne(pc) != null) {
                    brojProcena++;
                } else {
                    clientBezAssessment.add(pc);
                }
               
                /*if (checkPZS(pc) !=0) {
                    brojPZS++;
                } else {
                    clientBezPZS.add(pc);
                }*/
            }else {
                    clientBezOpstina.add(pc);
                }

        }
        model.addAttribute("clientBezPPD", clientBezPPD);
        model.addAttribute("clientBezAssessment", clientBezAssessment);
        model.addAttribute("brojOpstina", brojOpstina);
        model.addAttribute("brojProcena", brojProcena);
        model.addAttribute("brojPZS", brojPZS);
        model.addAttribute("brojOPplanova", brojOPplanova);
        model.addAttribute("clientBezOpstina", clientBezOpstina);
     //   model.addAttribute("clientBezPZS", clientBezPZS);
//client counter done
        List<PodRezultat> podRezultat = podRezultatService.findAll();
        int brojAktivnostiGotovih = 0;
        int brojAktivnostiOdustalih = 0;
        int brojAktivnostiUToku = 0;
        for (PodRezultat pr : podRezultat) {
            if (pr.getPeriodKompletiran() == 2) {
                brojAktivnostiGotovih++;
            };
            if (pr.getPeriodKompletiran() == 3) {
                brojAktivnostiOdustalih++;
            }
            if (pr.getPeriodKompletiran() == 1) {
                brojAktivnostiUToku++;
            }
            if (pr.getPeriodKompletiran() == 4) {
                brojAktivnostiUToku++;
            }
        }
        model.addAttribute("brojAktivnostiGotovih", brojAktivnostiGotovih);
        model.addAttribute("brojAktivnostiOdustalih", brojAktivnostiOdustalih);
        model.addAttribute("brojAktivnostiUToku", brojAktivnostiUToku);
//number of clients who uploaded a public document.

        return "admin/reports/report";
    }
    
    @GetMapping(value = "/supervisor/reports")
    public String supervisorReports(final Model model
    ) {
//broj oclijenata minus clijenti koji nisu prave opstine
        int brojOpstina = 0;
        int brojOPplanova = 0;
        int brojProcena = 0;
        int brojPZS = 0;
        List<Client> client = clientService.findAll();
        List<Client> clientBezPPD = new ArrayList();
        List<Client> clientBezAssessment = new ArrayList();
        List<Client> clientBezOpstina = new ArrayList();
        List<Client> clientBezPZS= new ArrayList();
        for (Client pc : client) {
            if (pc.getOpstina() != null) {
                brojOpstina++;
                if (!pc.getOpstina().getPublicPolicyDocuments().isEmpty()) {
                    brojOPplanova++;
                } else {
                    clientBezPPD.add(pc);
                }
                if (assessmentService.findOne(pc) != null) {
                    brojProcena++;
                } else {
                    clientBezAssessment.add(pc);
                }
                if (checkPZS(pc) !=0) {
                    brojPZS++;
                } else {
                    clientBezPZS.add(pc);
                }
            }else {
                    clientBezOpstina.add(pc);
                }

        }
        model.addAttribute("clientBezPPD", clientBezPPD);
        model.addAttribute("clientBezAssessment", clientBezAssessment);
        model.addAttribute("brojOpstina", brojOpstina);
        model.addAttribute("brojProcena", brojProcena);
        model.addAttribute("brojOPplanova", brojOPplanova);
        model.addAttribute("clientBezOpstina", clientBezOpstina);
        model.addAttribute("clientBezPZS", clientBezPZS);
//client counter done
        List<PodRezultat> podRezultat = podRezultatService.findAll();
        int brojAktivnostiGotovih = 0;
        int brojAktivnostiOdustalih = 0;
        int brojAktivnostiUToku = 0;
        for (PodRezultat pr : podRezultat) {
            if (pr.getPeriodKompletiran() == 2) {
                brojAktivnostiGotovih++;
            };
            if (pr.getPeriodKompletiran() == 3) {
                brojAktivnostiOdustalih++;
            }
            if (pr.getPeriodKompletiran() == 1) {
                brojAktivnostiUToku++;
            }
            if (pr.getPeriodKompletiran() == 4) {
                brojAktivnostiUToku++;
            }
        }
        model.addAttribute("brojAktivnostiGotovih", brojAktivnostiGotovih);
        model.addAttribute("brojAktivnostiOdustalih", brojAktivnostiOdustalih);
        model.addAttribute("brojAktivnostiUToku", brojAktivnostiUToku);
//number of clients who uploaded a public document.

        return "supervisor/report";
    }
    
    
    
}
