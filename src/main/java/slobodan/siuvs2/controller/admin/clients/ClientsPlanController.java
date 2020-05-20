/*
 * 
 */
package slobodan.siuvs2.controller.admin.clients;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.service.PosebanCiljFactory;
import slobodan.siuvs2.service.RezultatFactory;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.RezultatService;
import slobodan.siuvs2.service.PosebanCiljService;
import slobodan.siuvs2.service.PodRezultatFactory;
import slobodan.siuvs2.service.MeraService;
import slobodan.siuvs2.service.MeraFactory;
import slobodan.siuvs2.service.PodRezultatService;
import slobodan.siuvs2.service.PlanService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PlanFactory;
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Rezultat;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin/clients")
@SessionAttributes("plan")
public class ClientsPlanController {

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

    @GetMapping(value = "/{clientId}/plan/{pageId}")
    public String plan(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        switch (page.getType()) {//switch for when users edit something via ceoplan page or opsti plan page
            case CEOPLAN:
                return "redirect:/admin/clients/" + clientId + "/plan/ceo/" + pageId;
            case OPSTIPLAN:
                return "redirect:/admin/clients/" + clientId + "/plan/opstideo/" + pageId;
        }
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        String viewurl = "/admin/clients/" + clientId + "/plan/" + pageId;
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
        model.addAttribute("meralist", meralist);
        model.addAttribute("rezultatlist", rezultatlist);
        model.addAttribute("planurl", viewurl);
        return "admin/clients/plan/view";
    }

    @GetMapping(value = "/{clientId}/plan/opstideo/{pageId}")
    public String planOpsti(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        String viewurl = "/admin/clients/" + clientId + "/plan/" + pageId;
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
        model.addAttribute("meralist", meralist);
        model.addAttribute("rezultatlist", rezultatlist);
        model.addAttribute("planurl", viewurl);
        model.addAttribute("ceoplan", false);
        return "admin/clients/plan/opstiplan";
    }

    @GetMapping(value = "/{clientId}/plan/ceo/{pageId}")
    public String planCeo(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByPlanOrderByPagePageIdAsc(plan);
        String viewurl = "/admin/clients/" + clientId + "/plan/" + pageId;
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
        model.addAttribute("meralist", meralist);
        model.addAttribute("rezultatlist", rezultatlist);
        model.addAttribute("planurl", viewurl);
        model.addAttribute("ceoplan", true);
        return "admin/clients/plan/opstiplan";
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

    @PostMapping(value = "/{clientId}/plan/{pageId}/posebanCilj")
    public String addPosebanCilj(
            final Model model,
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam(name = "posebanCiljText") String posebanCiljText,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPV") String indikatorPV,
            @RequestParam(name = "indikatorCV") String indikatorCV,
            @PageableDefault final Pageable pageable, final RedirectAttributes redirectAttributes
    ) {
        Page page = pageService.findOne(pageId);
        Client client = clientService.findOne(clientId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        PosebanCilj pc = posebanCiljFactory.empty(plan, page);
        pc.setRedosled(PClist.size() + 1);
        pc.setPosebanCiljText(posebanCiljText);
        pc.setIndikator(indikator);
        pc.setIndikatorPv(indikatorPV);
        pc.setIndikatorCv(indikatorCV);
        try {
            posebanCiljService.save(pc);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови посебан циљ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#new-pc";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/mera")
    public String addMera(
            final Model model,
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam(name = "meraText") String meraText,
            @RequestParam(name = "pcID") PosebanCiljID posebanCiljID,
            @ModelAttribute("plan") Plan plan,
            @PageableDefault final Pageable pageable, final RedirectAttributes redirectAttributes
    ) {
        PosebanCilj pc = posebanCiljService.findOne(posebanCiljID);
        Mera mera = meraFactory.empty(pc);
        mera.setMeraText(meraText);
        mera.setRedosled(pc.getChildren().size() + 1);
        try {
            meraService.save(mera);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову меру!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#new-mera";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/rezultat")
    public String addRezultat(
            final Model model,
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam(name = "rezultatText") String rezultatText,
            @RequestParam(name = "meraID") MeraID meraID,
            @ModelAttribute("plan") Plan plan,
            @PageableDefault final Pageable pageable, final RedirectAttributes redirectAttributes
    ) {

        Mera mera = meraService.findOne(meraID);
        Rezultat rezultat = rezultatFactory.empty(mera);
        rezultat.setRezultatText(rezultatText);
        rezultat.setRedosled(mera.getChildren().size() + 1);
        try {
            rezultatService.save(rezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нов резултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#new-rezultat";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/podrezultat")
    public String addPodRezultat(
            final Model model,
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam(name = "aktivnostText") String aktivnostText,
            @RequestParam(name = "indikatorText") String indikatorText,
            @RequestParam(name = "odgInstText") String odgInstText,
            // @RequestParam(name = "partInstText") String partInstText,
            @RequestParam(name = "kratkoObrazlozenje") String kratkoObrazlozenje,
            @RequestParam(name = "periodText") String periodText,
            @RequestParam(name = "budzetJLS") int budzetJLS,
            @RequestParam(name = "budzetOstalo") int budzetOstalo,
            @RequestParam(name = "budzetNeobezbedjeno") int budzetNeobezbedjeno,
            @RequestParam(name = "periodKompletiran") int periodKompletiran,
            @RequestParam(name = "rezultatID") RezultatID rezultatID,
            @ModelAttribute("plan") Plan plan,
            @PageableDefault final Pageable pageable, final RedirectAttributes redirectAttributes
    ) {

        Rezultat rezultat = rezultatService.findOne(rezultatID);
        PodRezultat podRezultat = podRezultatFactory.empty(rezultat);
        podRezultat.setAktivnostiText(aktivnostText);
        podRezultat.setIndikatoriText(indikatorText);
        podRezultat.setOdgovornaInstitucijaText(odgInstText);
        //  podRezultat.setPartnerInstitucijaText(partInstText);
        podRezultat.setKratkoObrazlozenje(kratkoObrazlozenje);
        podRezultat.setPeriodText(periodText);
        podRezultat.setPeriodKompletiran(periodKompletiran);
        podRezultat.setBudzetJls(budzetJLS);
        podRezultat.setBudzetOstalo(budzetOstalo);
        podRezultat.setBudzetNeobezbedjeno(budzetNeobezbedjeno);
        podRezultat.setRedosled(rezultat.getChildren().size() + 1);

        try {
            podRezultatService.save(podRezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нов подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#new-podrezultat";
    }

    @GetMapping(value = "/{clientId}/plan/{pageId}/editPlan")
    public String editPlan(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        return "admin/clients/plan/editPlan";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveEditPlan")
    public String saveEditPlan(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam(name = "planText") String planText,
            @RequestParam(name = "periodOd") String periodOd,
            @RequestParam(name = "periodDo") String periodDo,
            @RequestParam(name = "opstiCilj") String opstiCilj,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPV") String indikatorPV,
            @RequestParam(name = "indikatorCV") String indikatorCV,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        plan.setPlanText(planText);
        plan.setPeriodOd(periodOd);
        plan.setPeriodDo(periodDo);
        plan.setOpstiCilj(opstiCilj);
        plan.setIndikator(indikator);
        plan.setIndikatorPv(indikatorPV);
        plan.setIndikatorCv(indikatorCV);
        try {
            planService.save(plan);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте изменили план!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#izmenjenplan";
    }

    /*  poseban cilj*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/deletePosebanCilj/{posebanCiljId}")
    public String deletePosebanCilj(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            posebanCiljService.delete(posebanCiljId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали посебан циљ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Из безбедносних разлога, пре брисања посебног циља је неопходно да обришете све мере које припадају том циљу као и све резултате и подрезултате који припадају његовим мерама!");
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#Obrisancilj";
    }

    @GetMapping(value = "/{clientId}/plan/{pageId}/editPosebanCilj/{posebanCiljId}")
    public String editPosebanCilj(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        PosebanCilj posebanCilj = posebanCiljService.findOne(posebanCiljId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("posebanCilj", posebanCilj);
        return "admin/clients/plan/editPosebanCilj";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveEditPosebanCilj/{posebanCiljId}")
    public String saveEditPosebanCilj(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            @RequestParam(name = "posebanCiljText") String posebanCiljText,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPv") String indikatorPV,
            @RequestParam(name = "indikatorCv") String indikatorCV,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        PosebanCilj posebanCilj = posebanCiljService.findOne(posebanCiljId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        posebanCilj.setPosebanCiljText(posebanCiljText);
        posebanCilj.setIndikator(indikator);
        posebanCilj.setIndikatorPv(indikatorPV);
        posebanCilj.setIndikatorCv(indikatorCV);
        try {
            posebanCiljService.save(posebanCilj);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте изменили посебан циљ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#izmenjencilj";
    }

    /* mera edit*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/deleteMera/{meraId}")
    public String deleteMera(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            meraService.delete(meraId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали меру!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Из безбедносних разлога, пре брисања мере је неопходно да обришете све  резултате и подрезултате који припадају тој мери!");
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#Obrisanamera";
    }

    @GetMapping(value = "/{clientId}/plan/{pageId}/editMera/{meraId}")
    public String editMera(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Mera mera = meraService.findOne(meraId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("mera", mera);
        return "admin/clients/plan/editMera";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveEditMera/{meraId}")
    public String saveEditMera(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            @RequestParam(name = "meraText") String meraText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Mera mera = meraService.findOne(meraId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        mera.setMeraText(meraText);
        try {
            meraService.save(mera);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте изменили меру!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#izmenjenamera";

    }

    /**/
 /* rezultat edit*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/deleteRezultat/{rezultatId}")
    public String deleteRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            rezultatService.delete(rezultatId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали резултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Из безбедносних разлога, пре брисања резултата је неопходно да обришете све подрезултате!");
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#Obrisanrezultat";
    }

    @GetMapping(value = "/{clientId}/plan/{pageId}/editRezultat/{rezultatId}")
    public String editRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Rezultat rezultat = rezultatService.findOne(rezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("rezultat", rezultat);
        return "admin/clients/plan/editRezultat";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveEditRezultat/{rezultatId}")
    public String saveEditRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            @RequestParam(name = "rezultatText") String rezultatText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Rezultat rezultat = rezultatService.findOne(rezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        rezultat.setRezultatText(rezultatText);
        try {
            rezultatService.save(rezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте изменили резултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#izmenjenrezultat";
    }

    /**/

 /* PODrezultat edit*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/deletePodRezultat/{podRezultatId}")
    public String deletePodRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PodRezultatID podRezultatId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            podRezultatService.delete(podRezultatId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#Obrisanpodrezultat";
    }

    @GetMapping(value = "/{clientId}/plan/{pageId}/editPodRezultat/{podRezultatId}")
    public String editPodRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PodRezultatID podRezultatId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        PodRezultat podRezultat = podRezultatService.findOne(podRezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("podRezultat", podRezultat);
        return "admin/clients/plan/editPodRezultat";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveEditPodRezultat/{podRezultatId}")
    public String saveEditPodRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PodRezultatID podRezultatId,
            @RequestParam(name = "aktivnostiText") String aktivnostiText,
            @RequestParam(name = "indikatoriText") String indikatoriText,
            @RequestParam(name = "odgovornaInstitucijaText") String odgovornaInstitucijaText,
            //  @RequestParam(name = "partnerInstitucijaText") String partnerInstitucijaText,
            @RequestParam(name = "kratkoObrazlozenje") String kratkoObrazlozenje,
            @RequestParam(name = "periodText") String periodText,
            @RequestParam(name = "periodKompletiran") int periodKompletiran,
            @RequestParam(name = "budzetJls") int budzetJls,
            @RequestParam(name = "budzetOstalo") int budzetOstalo,
            @RequestParam(name = "budzetNeobezbedjeno") int budzetNeobezbedjeno,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        PodRezultat podRezultat = podRezultatService.findOne(podRezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        podRezultat.setAktivnostiText(aktivnostiText);
        podRezultat.setIndikatoriText(indikatoriText);
        podRezultat.setOdgovornaInstitucijaText(odgovornaInstitucijaText);
        // podRezultat.setPartnerInstitucijaText(partnerInstitucijaText);
        podRezultat.setKratkoObrazlozenje(kratkoObrazlozenje);
        podRezultat.setPeriodText(periodText);
        podRezultat.setPeriodKompletiran(periodKompletiran);
        podRezultat.setBudzetJls(budzetJls);
        podRezultat.setBudzetOstalo(budzetOstalo);
        podRezultat.setBudzetNeobezbedjeno(budzetNeobezbedjeno);
        try {
            podRezultatService.save(podRezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте изменили подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#izmenjenpodRezultat";

    }

    /**/
 /* new pc*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/newPosebanCilj")
    public String editNewPosebanCilj(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        return "admin/clients/plan/newPosebanCilj";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveNewPosebanCilj/{planId}")
    public String saveNewPosebanCilj(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PlanID planId,
            @RequestParam(name = "posebanCiljText") String posebanCiljText,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPv") String indikatorPV,
            @RequestParam(name = "indikatorCv") String indikatorCV,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        Plan plan = planService.findFirstByClient(client);
        PosebanCilj pc = posebanCiljFactory.empty(plan, page);
        pc.setRedosled(plan.getChildren().size() + 1);
        pc.setPosebanCiljText(posebanCiljText);
        pc.setIndikator(indikator);
        pc.setIndikatorPv(indikatorPV);
        pc.setIndikatorCv(indikatorCV);
        try {
            posebanCiljService.save(pc);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови посебан циљ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#newcilj";
    }

    /**/

 /*new mera*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/newMera/{posebanCiljId}")
    public String editNewMera(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("posebanCiljId", posebanCiljId);
        return "admin/clients/plan/newMera";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveNewMera/{posebanCiljId}")
    public String saveNewMera(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            @RequestParam(name = "meraText") String meraText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        PosebanCilj pc = posebanCiljService.findOne(posebanCiljId);
        Mera mera = meraFactory.empty(pc);
        mera.setMeraText(meraText);
        mera.setRedosled(pc.getChildren().size() + 1);
        try {
            meraService.save(mera);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нову меру!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#newmera";
    }

    /**/
 /*new rezultat*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/newRezultat/{meraId}")
    public String editNewRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("meraId", meraId);
        return "admin/clients/plan/newRezultat";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveNewRezultat/{meraId}")
    public String saveNewRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            @RequestParam(name = "rezultatText") String rezultatText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        Mera mera = meraService.findOne(meraId);
        Rezultat rezultat = rezultatFactory.empty(mera);
        rezultat.setRezultatText(rezultatText);
        rezultat.setRedosled(mera.getChildren().size() + 1);
        try {
            rezultatService.save(rezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нов резултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#new-rezultat";
    }

    /**/
 /*new rezultat*/
    @GetMapping(value = "/{clientId}/plan/{pageId}/newPodRezultat/{rezultatId}")
    public String editNewPodRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("rezultatId", rezultatId);
        return "admin/clients/plan/newPodRezultat";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveNewPodRezultat/{rezultatId}")
    public String saveNewPodRezultat(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            @RequestParam(name = "aktivnostText") String aktivnostText,
            @RequestParam(name = "indikatorText") String indikatorText,
            @RequestParam(name = "odgInstText") String odgInstText,
            //@RequestParam(name = "partInstText") String partInstText,
            @RequestParam(name = "periodText") String periodText,
            @RequestParam(name = "budzetJLS") int budzetJLS,
            @RequestParam(name = "budzetOstalo") int budzetOstalo,
            @RequestParam(name = "budzetNeobezbedjeno") int budzetNeobezbedjeno,
            @RequestParam(name = "periodKompletiran") int periodKompletiran,
            @RequestParam(name = "kratkoObrazlozenje") String kratkoObrazlozenje,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        Rezultat rezultat = rezultatService.findOne(rezultatId);
        PodRezultat podRezultat = podRezultatFactory.empty(rezultat);
        podRezultat.setAktivnostiText(aktivnostText);
        podRezultat.setIndikatoriText(indikatorText);
        podRezultat.setOdgovornaInstitucijaText(odgInstText);
        // podRezultat.setPartnerInstitucijaText(partInstText);
        podRezultat.setKratkoObrazlozenje(kratkoObrazlozenje);
        podRezultat.setPeriodText(periodText);
        podRezultat.setPeriodKompletiran(periodKompletiran);
        podRezultat.setBudzetJls(budzetJLS);
        podRezultat.setBudzetOstalo(budzetOstalo);
        podRezultat.setBudzetNeobezbedjeno(budzetNeobezbedjeno);
        podRezultat.setRedosled(rezultat.getChildren().size() + 1);
        try {
            podRezultatService.save(podRezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нов подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#new-podrezultat";
    }

    /**/
    @GetMapping(value = "/{clientId}/plan/{pageId}/NewPlan")
    public String newPlan(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        return "admin/clients/plan/newPlan";
    }

    @PostMapping(value = "/{clientId}/plan/{pageId}/saveNewPlan")
    public String saveNewPlan(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam(name = "planText") String planText,
            @RequestParam(name = "periodOd") String periodOd,
            @RequestParam(name = "periodDo") String periodDo,
            @RequestParam(name = "opstiCilj") String opstiCilj,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPV") String indikatorPV,
            @RequestParam(name = "indikatorCV") String indikatorCV,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Plan plan = planFactory.empty(client);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        plan.setPlanText(planText);
        plan.setPeriodOd(periodOd);
        plan.setPeriodDo(periodDo);
        plan.setOpstiCilj(opstiCilj);
        plan.setIndikator(indikator);
        plan.setIndikatorPv(indikatorPV);
        plan.setIndikatorCv(indikatorCV);
        try {
            planService.save(plan);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте додали план!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/clients/" + clientId + "/plan/" + pageId + "#noviplan";

    }
}
