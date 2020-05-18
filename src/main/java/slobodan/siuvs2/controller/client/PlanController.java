/*
 * 
 */
package slobodan.siuvs2.controller.client;

/**
 *
 * @author slobaodanmargetic988@gmail.com
 */
import slobodan.siuvs2.service.PosebanCiljFactory;
import slobodan.siuvs2.service.RezultatFactory;
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
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.PodRezultat;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.model.User;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.PageType;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
@SessionAttributes("plan")
public class PlanController {

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

    @GetMapping(value = "/plan/{pageId}")
    public String plan(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        Page page = pageService.findOne(pageId);
          switch (page.getType()){//switch for when users edit something via ceoplan page or opsti plan page
          case CEOPLAN:
         return "redirect:/client/plan/ceo/" + pageId;
        
         case OPSTIPLAN:
         return "redirect:/client/plan/opstideo/" + pageId;
        
         }
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        List<Mera> meralist = new ArrayList();
        List<Rezultat> rezultatlist = new ArrayList();
        String viewurl = "/client/plan/" + pageId;

        if (plan == null) {
            plan = planFactory.empty(client);
            model.addAttribute("planempty", true);
        } else {
            model.addAttribute("planempty", false);
        }
        if (PClist == null) {
            PClist = new ArrayList();
            // posebanCiljFactory.empty(plan, page);
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
        return "client/plan/view";
    }
 @GetMapping(value = "/plan/opstideo/{pageId}")
    public String planOpsti(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
        List<PosebanCilj> PClist = posebanCiljService.findAllByClientAndPage(client, page);
        List<Mera> meralist = new ArrayList();
        List<Rezultat> rezultatlist = new ArrayList();
        String viewurl = "/client/plan/" + pageId;

        if (plan == null) {
            plan = planFactory.empty(client);
            model.addAttribute("planempty", true);
        } else {
            model.addAttribute("planempty", false);
        }
        if (PClist == null) {
            PClist = new ArrayList();
            // posebanCiljFactory.empty(plan, page);
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
        return "client/plan/opstiplan";
    }
    @GetMapping(value = "/plan/ceo/{pageId}")
    public String planCeo(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        Page page = pageService.findOne(pageId);
        Plan plan = planService.findFirstByClient(client);
       List<PosebanCilj> PClist = posebanCiljService.findAllByPlanOrderByPagePageIdAsc(plan);
        List<Mera> meralist = new ArrayList();
        List<Rezultat> rezultatlist = new ArrayList();
        String viewurl = "/client/plan/" + pageId;

        if (plan == null) {
            plan = planFactory.empty(client);
            model.addAttribute("planempty", true);
        } else {
            model.addAttribute("planempty", false);
        }
        if (PClist == null) {
            PClist = new ArrayList();
            // posebanCiljFactory.empty(plan, page);
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
        return "client/plan/opstiplan";
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

    @PostMapping(value = "/plan/{pageId}/posebanCilj")
    public String addPosebanCilj(
            final Model model,
            @PathVariable final PageId pageId,
            @RequestParam(name = "posebanCiljText") String posebanCiljText,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPV") String indikatorPV,
            @RequestParam(name = "indikatorCV") String indikatorCV,
            @PageableDefault final Pageable pageable, final RedirectAttributes redirectAttributes
    ) {
        Page page = pageService.findOne(pageId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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

        return "redirect:/client/plan/" + pageId + "#new-pc";
    }

    @PostMapping(value = "/plan/{pageId}/mera")
    public String addMera(
            final Model model,
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
        return "redirect:/client/plan/" + pageId + "#new-mera";
    }

    @PostMapping(value = "/plan/{pageId}/rezultat")
    public String addRezultat(
            final Model model,
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
        return "redirect:/client/plan/" + pageId + "#new-rezultat";
    }

    @PostMapping(value = "/plan/{pageId}/podrezultat")
    public String addPodRezultat(
            final Model model,
            @PathVariable final PageId pageId,
            @RequestParam(name = "aktivnostText") String aktivnostText,
            @RequestParam(name = "indikatorText") String indikatorText,
            @RequestParam(name = "odgInstText") String odgInstText,
            @RequestParam(name = "partInstText") String partInstText,
            @RequestParam(name = "periodText") String periodText,
            @RequestParam(name = "budzetJLS") String budzetJLS,
            @RequestParam(name = "budzetOstalo") String budzetOstalo,
            @RequestParam(name = "budzetNeobezbedjeno") String budzetNeobezbedjeno,
            @RequestParam(name = "periodKompletirano") int periodKompletirano,
            @RequestParam(name = "rezultatID") RezultatID rezultatID,
            @ModelAttribute("plan") Plan plan,
            @PageableDefault final Pageable pageable, final RedirectAttributes redirectAttributes
    ) {

        Rezultat rezultat = rezultatService.findOne(rezultatID);
        PodRezultat podRezultat = podRezultatFactory.empty(rezultat);
        podRezultat.setAktivnostiText(aktivnostText);
        podRezultat.setIndikatoriText(indikatorText);
        podRezultat.setOdgovornaInstitucijaText(odgInstText);
        podRezultat.setPartnerInstitucijaText(partInstText);
        podRezultat.setPeriodText(periodText);
        podRezultat.setPeriodKompletiran(periodKompletirano);
        podRezultat.setBudzetJlsText(budzetJLS);
        podRezultat.setBudzetOstaloText(budzetOstalo);
        podRezultat.setBudzetNeobezbedjenoText(budzetNeobezbedjeno);
        podRezultat.setRedosled(rezultat.getChildren().size() + 1);

        try {
            podRezultatService.save(podRezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нов подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/plan/" + pageId + "#new-podrezultat";
    }

    @GetMapping(value = "/plan/{pageId}/editPlan")
    public String editPlan(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        Page page = pageService.findOne(pageId);
        //  Plan plan = planService.findFirstByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        //model.addAttribute("plan", plan);

        return "/client/plan/editPlan";
    }

    @PostMapping(value = "/plan/{pageId}/saveEditPlan")
    public String saveEditPlan(
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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
        return "redirect:/client/plan/" + pageId + "#izmenjenplan";

    }

    /*  poseban cilj*/

    @GetMapping(value = "/plan/{pageId}/deletePosebanCilj/{posebanCiljId}")
    public String deletePosebanCilj(
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            posebanCiljService.delete(posebanCiljId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали посебан циљ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Из безбедносних разлога, пре брисања посебног циља је неопходно да обришете све мере које припадају том циљу као и све резултате и подрезултате који припадају његовим мерама!");
        }
        return "redirect:/client/plan/" + pageId + "#Obrisancilj";
    }

    @GetMapping(value = "/plan/{pageId}/editPosebanCilj/{posebanCiljId}")
    public String editPosebanCilj(
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        // Plan plan = planService.findFirstByClient(client);
        PosebanCilj posebanCilj = posebanCiljService.findOne(posebanCiljId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("posebanCilj", posebanCilj);
        return "client/plan/editPosebanCilj";
    }

    @PostMapping(value = "/plan/{pageId}/saveEditPosebanCilj/{posebanCiljId}")
    public String saveEditPosebanCilj(
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            @RequestParam(name = "posebanCiljText") String posebanCiljText,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPv") String indikatorPV,
            @RequestParam(name = "indikatorCv") String indikatorCV,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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
        return "redirect:/client/plan/" + pageId + "#izmenjencilj";

    }

    /* mera edit*/
    @GetMapping(value = "/plan/{pageId}/deleteMera/{meraId}")
    public String deleteMera(
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            meraService.delete(meraId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали меру!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Из безбедносних разлога, пре брисања мере је неопходно да обришете све  резултате и подрезултате који припадају тој мери!");
        }
        return "redirect:/client/plan/" + pageId + "#Obrisanamera";
    }

    @GetMapping(value = "/plan/{pageId}/editMera/{meraId}")
    public String editMera(
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        Mera mera = meraService.findOne(meraId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("mera", mera);
        return "client/plan/editMera";
    }

    @PostMapping(value = "/plan/{pageId}/saveEditMera/{meraId}")
    public String saveEditMera(
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            @RequestParam(name = "meraText") String meraText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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
        return "redirect:/client/plan/" + pageId + "#izmenjenamera";

    }

    /**/
 /* rezultat edit*/
    @GetMapping(value = "/plan/{pageId}/deleteRezultat/{rezultatId}")
    public String deleteRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            rezultatService.delete(rezultatId);

            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали резултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Из безбедносних разлога, пре брисања резултата је неопходно да обришете све подрезултате!");
        }
        return "redirect:/client/plan/" + pageId + "#Obrisanrezultat";
    }

    @GetMapping(value = "/plan/{pageId}/editRezultat/{rezultatId}")
    public String editRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        Rezultat rezultat = rezultatService.findOne(rezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("rezultat", rezultat);
        return "client/plan/editRezultat";
    }

    @PostMapping(value = "/plan/{pageId}/saveEditRezultat/{rezultatId}")
    public String saveEditRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            @RequestParam(name = "rezultatText") String rezultatText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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
        return "redirect:/client/plan/" + pageId + "#izmenjenrezultat";

    }

    /**/

 /* PODrezultat edit*/
    @GetMapping(value = "/plan/{pageId}/deletePodRezultat/{podRezultatId}")
    public String deletePodRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final PodRezultatID podRezultatId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        try {
            podRezultatService.delete(podRezultatId);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте обрисали подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/plan/" + pageId + "#Obrisanpodrezultat";
    }

    @GetMapping(value = "/plan/{pageId}/editPodRezultat/{podRezultatId}")
    public String editPodRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final PodRezultatID podRezultatId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        PodRezultat podRezultat = podRezultatService.findOne(podRezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("podRezultat", podRezultat);
        return "client/plan/editPodRezultat";
    }

    @PostMapping(value = "/plan/{pageId}/saveEditPodRezultat/{podRezultatId}")
    public String saveEditPodRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final PodRezultatID podRezultatId,
            @RequestParam(name = "aktivnostiText") String aktivnostiText,
            @RequestParam(name = "indikatoriText") String indikatoriText,
            @RequestParam(name = "odgovornaInstitucijaText") String odgovornaInstitucijaText,
            @RequestParam(name = "partnerInstitucijaText") String partnerInstitucijaText,
            @RequestParam(name = "periodText") String periodText,
            @RequestParam(name = "periodKompletiran") int periodKompletiran,
            @RequestParam(name = "budzetJlsText") String budzetJlsText,
            @RequestParam(name = "budzetOstaloText") String budzetOstaloText,
            @RequestParam(name = "budzetNeobezbedjenoText") String budzetNeobezbedjenoText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        PodRezultat podRezultat = podRezultatService.findOne(podRezultatId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        podRezultat.setAktivnostiText(aktivnostiText);
        podRezultat.setIndikatoriText(indikatoriText);
        podRezultat.setOdgovornaInstitucijaText(odgovornaInstitucijaText);
        podRezultat.setPartnerInstitucijaText(partnerInstitucijaText);
        podRezultat.setPeriodText(periodText);
        podRezultat.setPeriodKompletiran(periodKompletiran);
        podRezultat.setBudzetJlsText(budzetJlsText);
        podRezultat.setBudzetOstaloText(budzetOstaloText);
        podRezultat.setBudzetNeobezbedjenoText(budzetNeobezbedjenoText);
        try {
            podRezultatService.save(podRezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно сте изменили подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/plan/" + pageId + "#izmenjenpodRezultat";

    }

    /**/
 /* new pc*/
    @GetMapping(value = "/plan/{pageId}/newPosebanCilj")
    public String editNewPosebanCilj(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        return "client/plan/newPosebanCilj";
    }

    @PostMapping(value = "/plan/{pageId}/saveNewPosebanCilj/{planId}")
    public String saveNewPosebanCilj(
            @PathVariable final PageId pageId,
            @PathVariable final PlanID planId,
            @RequestParam(name = "posebanCiljText") String posebanCiljText,
            @RequestParam(name = "indikator") String indikator,
            @RequestParam(name = "indikatorPv") String indikatorPV,
            @RequestParam(name = "indikatorCv") String indikatorCV,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
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
            //done empty children
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нови посебан циљ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/plan/" + pageId + "#newcilj";

    }

    /**/

 /*new mera*/
    @GetMapping(value = "/plan/{pageId}/newMera/{posebanCiljId}")
    public String editNewMera(
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("posebanCiljId", posebanCiljId);
        return "client/plan/newMera";
    }

    @PostMapping(value = "/plan/{pageId}/saveNewMera/{posebanCiljId}")
    public String saveNewMera(
            @PathVariable final PageId pageId,
            @PathVariable final PosebanCiljID posebanCiljId,
            @RequestParam(name = "meraText") String meraText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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
        return "redirect:/client/plan/" + pageId + "#newmera";

    }

    /**/
 /*new rezultat*/
    @GetMapping(value = "/plan/{pageId}/newRezultat/{meraId}")
    public String editNewRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("meraId", meraId);
        return "client/plan/newRezultat";
    }

    @PostMapping(value = "/plan/{pageId}/saveNewRezultat/{meraId}")
    public String saveNewRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final MeraID meraId,
            @RequestParam(name = "rezultatText") String rezultatText,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
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
        return "redirect:/client/plan/" + pageId + "#new-rezultat";
    }

    /**/
 /*new rezultat*/
    @GetMapping(value = "/plan/{pageId}/newPodRezultat/{rezultatId}")
    public String editNewPodRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("rezultatId", rezultatId);
        return "client/plan/newPodRezultat";
    }

    @PostMapping(value = "/plan/{pageId}/saveNewPodRezultat/{rezultatId}")
    public String saveNewPodRezultat(
            @PathVariable final PageId pageId,
            @PathVariable final RezultatID rezultatId,
            @RequestParam(name = "aktivnostText") String aktivnostText,
            @RequestParam(name = "indikatorText") String indikatorText,
            @RequestParam(name = "odgInstText") String odgInstText,
            @RequestParam(name = "partInstText") String partInstText,
            @RequestParam(name = "periodText") String periodText,
            @RequestParam(name = "budzetJLS") String budzetJLS,
            @RequestParam(name = "budzetOstalo") String budzetOstalo,
            @RequestParam(name = "budzetNeobezbedjeno") String budzetNeobezbedjeno,
            @RequestParam(name = "periodKompletirano") int periodKompletirano,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        Rezultat rezultat = rezultatService.findOne(rezultatId);
        PodRezultat podRezultat = podRezultatFactory.empty(rezultat);
        podRezultat.setAktivnostiText(aktivnostText);
        podRezultat.setIndikatoriText(indikatorText);
        podRezultat.setOdgovornaInstitucijaText(odgInstText);
        podRezultat.setPartnerInstitucijaText(partInstText);
        podRezultat.setPeriodText(periodText);
        podRezultat.setPeriodKompletiran(periodKompletirano);
        podRezultat.setBudzetJlsText(budzetJLS);
        podRezultat.setBudzetOstaloText(budzetOstalo);
        podRezultat.setBudzetNeobezbedjenoText(budzetNeobezbedjeno);
        podRezultat.setRedosled(rezultat.getChildren().size() + 1);
        try {
            podRezultatService.save(podRezultat);
            redirectAttributes.addFlashAttribute("successMessage", "Додали сте нов подрезултат!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/client/plan/" + pageId + "#new-podrezultat";
    }
    /**/
}
