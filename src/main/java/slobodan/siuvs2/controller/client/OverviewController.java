/*
 * 
 */
package slobodan.siuvs2.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.InternationalAgreementsFactory;
import slobodan.siuvs2.service.InternationalAgreementsService;
import slobodan.siuvs2.service.PublicPolicyDocumentsFactory;
import slobodan.siuvs2.service.PublicPolicyDocumentsService;
import slobodan.siuvs2.service.TasksFactory;
import slobodan.siuvs2.service.TasksService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;
import slobodan.siuvs2.valueObject.TasksID;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class OverviewController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TasksService tasksService;
    @Autowired
    private PublicPolicyDocumentsService PPDService;
    @Autowired
    private InternationalAgreementsService IAService;
    @Autowired
    private TasksFactory tasksFactory;
    @Autowired
    private PublicPolicyDocumentsFactory PPDFactory;
    @Autowired
    private InternationalAgreementsFactory IAFactory;

    @GetMapping(value = "/overview")
    public String list(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());

        model.addAttribute("client", clientService.findOne(client.getClientId()));
        return "client/overview/view";
    }


    @GetMapping(value = "/overview/newNadleznost")
    public String newNadleznost( final Model model) {
        return "/client/overview/newNadleznost";
    }
    @GetMapping(value = "/overview/newIA")
    public String newIA( final Model model) {
        
        return "/client/overview/newIA";
    }
    @GetMapping(value = "/overview/newPPD")
    public String newPPD( final Model model) {
       
        return "/client/overview/newPPD";
    }
    
    @PostMapping(value = "/overview/newNadleznost")
    public String savenewNadleznost(
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        Tasks task=tasksFactory.empty(name);
        try{
        tasksService.save(task);
        client.getOpstina().getTasks().add(task);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/newNadleznost";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/newIA")
    public String savenewIA(
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        
        InternationalAgreements ia=IAFactory.empty(name);
        try{
        IAService.save(ia);
        client.getOpstina().getInternationalAgreements().add(ia);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/newIA";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/newPPD")
    public String savenewPPD(
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PublicPolicyDocuments ppd=PPDFactory.empty(name);
        try{
        PPDService.save(ppd);
        client.getOpstina().getPublicPolicyDocuments().add(ppd);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Jавни документ документ успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/newPPD";
            }
        return "redirect:/client/overview";
    }
    @GetMapping(value = "/overview/deleteNadleznost/{Nadleznostid}")
    public String deleteNadleznost(
            @PathVariable final TasksID Nadleznostid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        Tasks task=tasksService.findOne(Nadleznostid);
        try{        
        client.getOpstina().getTasks().remove(task);
        clientService.save(client);
        tasksService.delete(Nadleznostid);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    @GetMapping(value = "/overview/deleteIA/{IAid}")
    public String deleteIA(
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        InternationalAgreements ia=IAService.findOne(IAid);
        try{        
        client.getOpstina().getInternationalAgreements().remove(ia);
        clientService.save(client);
        IAService.delete(IAid);
        redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    
    @GetMapping(value = "/overview/deletePPD/{PPDid}")
    public String deletePPD(
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        try{        
        client.getOpstina().getPublicPolicyDocuments().remove(PPD);
        clientService.save(client);
        PPDService.delete(PPDid);
        redirectAttributes.addFlashAttribute("successMessage", "Jавни документ успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    
    
      @GetMapping(value = "/overview/editNadleznost/{Nadleznostid}")
    public String editNadleznost(
            @PathVariable final TasksID Nadleznostid,           
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Tasks task=tasksService.findOne(Nadleznostid);
        model.addAttribute("task", task);
        return "/client/overview/editNadleznost";
    }
    
        @GetMapping(value = "/overview/editIA/{IAid}")
    public String editIA(
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        InternationalAgreements IA = IAService.findOne(IAid);
        model.addAttribute("IA", IA);
        return "/client/overview/editIA";
    }
    @GetMapping(value = "/overview/editPPD/{PPDid}")
    public String editPPD(
            @PathVariable final PublicPolicyDocumentsID PPDid,           
            final RedirectAttributes redirectAttributes,
            final Model model) {
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        model.addAttribute("PPD", PPD);
        return "/client/overview/editPPD";
    }
    @PostMapping(value = "/overview/editNadleznost/{Nadleznostid}")
    public String saveEditNadleznost(
            @PathVariable final TasksID Nadleznostid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        Tasks task=tasksService.findOne(Nadleznostid);
        try{        
        task.setName(name);       
        tasksService.save(task);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/editIA/{IAid}")
    public String saveEditIA(
            @PathVariable final InternationalAgreementsID IAid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        InternationalAgreements IA=IAService.findOne(IAid);
        try{        
        IA.setName(name);       
        IAService.save(IA);
        redirectAttributes.addFlashAttribute("successMessage", "Међународни документ успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/editPPD/{PPDid}")
    public String saveeditPPD(
            @PathVariable final PublicPolicyDocumentsID PPDid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        try{        
        PPD.setName(name);       
        PPDService.save(PPD);
        redirectAttributes.addFlashAttribute("successMessage", "Јавни документ успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
}
