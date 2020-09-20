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
import slobodan.siuvs2.service.PosebanCiljService;
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
    private PosebanCiljService posebanCiljService;

    @Autowired
    private DynamicDataService dynamicDataService;

    private Integer checkPZS(Client pc) {
        Integer table_def_id = 4;
        DynamicData dd = dynamicDataService.checkIfExists(table_def_id, pc.getId());
        if (dd == null) {
            return 0;
        } else {
            return 1;
        }
    }

    private Integer checkPSR(Client pc) {

        Integer table_def_id = 4;
        DynamicData dd = dynamicDataService.checkIfExists(table_def_id, pc.getId());
        if (dd == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @GetMapping(value = "/admin/reports")
    public String adminReports(final Model model
    ) {
//broj oclijenata minus clijenti koji nisu prave opstine
        int brojOpstina = 0;
        int brojOPplanova = 0;
        int brojProcena = 0;
        int brojPZS = 0;
        int brojPSR = 0;
        List<Client> client = clientService.findAll();
        List<Client> clientBezOpstina = new ArrayList();

        List<Client> clientBezAssessment = new ArrayList();
        List<Client> clientSaAssessment = new ArrayList();

        List<Client> clientSaPZS = new ArrayList();
        List<Client> clientBezPZS = new ArrayList();

        List<Client> clientBezPSR = new ArrayList();
        List<Client> clientSaPSR = new ArrayList();

        List<Client> clientBezPPD = new ArrayList();
        List<Client> clientSaPPD = new ArrayList();

        for (Client pc : client) {
            if (pc.getOpstina() != null) {
                brojOpstina++;

                if (assessmentService.findOne(pc) != null) {
                    brojProcena++;
                    clientSaAssessment.add(pc);
                } else {
                    clientBezAssessment.add(pc);
                }

                if (checkPZS(pc) != 0) {
                    brojPZS++;
                    clientSaPZS.add(pc);
                } else {
                    clientBezPZS.add(pc);
                }

                if (posebanCiljService.findFirstByClient(pc) != null) {
                    brojPSR++;
                    clientSaPSR.add(pc);
                } else {
                    clientBezPSR.add(pc);
                }

                if (!pc.getOpstina().getPublicPolicyDocuments().isEmpty()) {
                    brojOPplanova++;
                    clientSaPPD.add(pc);
                } else {
                    clientBezPPD.add(pc);
                }

            } else {
                clientBezOpstina.add(pc);
            }

        }

        model.addAttribute("brojOpstina", brojOpstina);
        model.addAttribute("brojProcena", brojProcena);
        model.addAttribute("brojPZS", brojPZS);             
        model.addAttribute("brojPSR", brojPSR);
        model.addAttribute("brojOPplanova", brojOPplanova);
        
        model.addAttribute("clientBezOpstina", clientBezOpstina);
        
        model.addAttribute("clientSaAssessment", clientSaAssessment);
        model.addAttribute("clientBezAssessment", clientBezAssessment);
        
        
        model.addAttribute("clientBezPZS", clientBezPZS);
        model.addAttribute("clientSaPZS", clientSaPZS);
        
        model.addAttribute("clientSaPSR", clientSaPSR);
        model.addAttribute("clientBezPSR", clientBezPSR);
        
        model.addAttribute("clientBezPPD", clientBezPPD);
        model.addAttribute("clientSaPPD", clientSaPPD);
        
        
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
        int brojPSR = 0;
        List<Client> client = clientService.findAll();
        List<Client> clientBezOpstina = new ArrayList();

        List<Client> clientBezAssessment = new ArrayList();
        List<Client> clientSaAssessment = new ArrayList();

        List<Client> clientSaPZS = new ArrayList();
        List<Client> clientBezPZS = new ArrayList();

        List<Client> clientBezPSR = new ArrayList();
        List<Client> clientSaPSR = new ArrayList();

        List<Client> clientBezPPD = new ArrayList();
        List<Client> clientSaPPD = new ArrayList();

        for (Client pc : client) {
            if (pc.getOpstina() != null) {
                brojOpstina++;

                if (assessmentService.findOne(pc) != null) {
                    brojProcena++;
                    clientSaAssessment.add(pc);
                } else {
                    clientBezAssessment.add(pc);
                }

                if (checkPZS(pc) != 0) {
                    brojPZS++;
                    clientSaPZS.add(pc);
                } else {
                    clientBezPZS.add(pc);
                }

                if (posebanCiljService.findFirstByClient(pc) != null) {
                    brojPSR++;
                    clientSaPSR.add(pc);
                } else {
                    clientBezPSR.add(pc);
                }

                if (!pc.getOpstina().getPublicPolicyDocuments().isEmpty()) {
                    brojOPplanova++;
                    clientSaPPD.add(pc);
                } else {
                    clientBezPPD.add(pc);
                }

            } else {
                clientBezOpstina.add(pc);
            }

        }

        model.addAttribute("brojOpstina", brojOpstina);
        model.addAttribute("brojProcena", brojProcena);
        model.addAttribute("brojPZS", brojPZS);             
        model.addAttribute("brojPSR", brojPSR);
        model.addAttribute("brojOPplanova", brojOPplanova);
        
        model.addAttribute("clientBezOpstina", clientBezOpstina);
        
        model.addAttribute("clientSaAssessment", clientSaAssessment);
        model.addAttribute("clientBezAssessment", clientBezAssessment);
        
        
        model.addAttribute("clientBezPZS", clientBezPZS);
        model.addAttribute("clientSaPZS", clientSaPZS);
        
        model.addAttribute("clientSaPSR", clientSaPSR);
        model.addAttribute("clientBezPSR", clientBezPSR);
        
        model.addAttribute("clientBezPPD", clientBezPPD);
        model.addAttribute("clientSaPPD", clientSaPPD);
        
        
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
