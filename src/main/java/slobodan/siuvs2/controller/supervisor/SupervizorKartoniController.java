/*
 * 
 */
package slobodan.siuvs2.controller.supervisor;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.controller.admin.clients.*;
import slobodan.siuvs2.controller.admin.*;
import slobodan.siuvs2.controller.client.*;
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
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Delatnost;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonClanovaStaba;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DelatnostService;
import slobodan.siuvs2.service.DetaljiMTSService;
import slobodan.siuvs2.service.GrupaMTSService;
import slobodan.siuvs2.service.KadroviService;
import slobodan.siuvs2.service.KartonClanovaStabaService;
import slobodan.siuvs2.service.KartonSubjektiService;
import slobodan.siuvs2.service.KartonUdruzenjaService;
import slobodan.siuvs2.service.OrgJedinicaMTSService;
import slobodan.siuvs2.service.PodvrstaMTSService;
import slobodan.siuvs2.service.VlasnikMTSService;
import slobodan.siuvs2.service.VrstaMTSService;
import slobodan.siuvs2.service.ZanimanjaService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DelatnostId;
import slobodan.siuvs2.valueObject.DetaljiMTSId;
import slobodan.siuvs2.valueObject.KartonClanovaStabaId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.KartonUdruzenjaId;
import slobodan.siuvs2.valueObject.ZanimanjaId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class SupervizorKartoniController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private KartonSubjektiService kartonSubjektiService;
    @Autowired
    private KartonUdruzenjaService kartonUdruzenjaService;
    
    
    @Autowired
    private ZanimanjaService zanimanjaService;
    @Autowired
    private KadroviService kadroviService;
    @Autowired
    private DelatnostService delatnostService;
    
    
    
    @Autowired
    private DetaljiMTSService detaljiMTSService;

    @Autowired
    private VlasnikMTSService vlasnikMTSService;

    @Autowired
    private OrgJedinicaMTSService orgJedinicaMTSService;

    @Autowired
    private GrupaMTSService grupaMTSService;

    @Autowired
    private VrstaMTSService vrstaMTSService;

    @Autowired
    private PodvrstaMTSService podvrstaMTSService;

    
     @Autowired
    private KartonClanovaStabaService kartonClanovaStabaService;

    @GetMapping(value = "/supervisor/clients/{clientId}/Kartoni")
    public String adminZbirniObrasci(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);

        return "supervisor/listaKartona";
    }

    @GetMapping(value = "/supervisor/clients/{clientId}/kartonSubjekti")
    public String adminkartonSubjekti(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<KartonSubjekti> listaKartona = kartonSubjektiService.findAllByClientOrderByPunnazivAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "supervisor/kartonSubjektiLista";
    }

    @GetMapping(value = "/supervisor/clients/{clientId}/kartonSubjekti/{kartonId}")
    public String adminkartonSubjektiJedan(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton = kartonSubjektiService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "supervisor/kartonSubjekta";
    }
    
    @GetMapping(value = "/supervisor/clients/{clientId}/kartonUdruzenja")
    public String adminkartonUdruzenja(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<KartonUdruzenja> listaKartona = kartonUdruzenjaService.findAllByClientOrderByPunnazivAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "supervisor/kartonUdruzenjaLista";
    }

    @GetMapping(value = "/supervisor/clients/{clientId}/kartonUdruzenja/{kartonId}")
    public String adminkartonUdruzenjaJedan(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "supervisor/kartonUdruzenja";
    }

    
    
       @GetMapping(value = "/supervisor/clients/{clientId}/kartonClanaStaba")
    public String supervisorkartonClanaStaba(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<KartonClanovaStaba> listaKartona = kartonClanovaStabaService.findAllByClientOrderByPunoimeAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "supervisor/kartonClanaStabaLista";
    }

    @GetMapping(value = "/supervisor/clients/{clientId}/kartonClanaStaba/{kartonId}")
    public String supervisorkartonClanaStabaJedan(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonClanovaStabaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "supervisor/kartonClanaStaba";
    }
    
    
        @GetMapping(value = "/supervisor/clients/{clientId}/kartonMTS")
    public String supervisorkartonMTS(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<DetaljiMTS> listaKartona = detaljiMTSService.findAllByClient(client);
        model.addAttribute("listaKartona", listaKartona);

        return "supervisor/kartonMTSLista";
    }
    
     @GetMapping(value = "/supervisor/clients/{clientId}/kartonMTS/{kartonId}")
    public String supervisorkartonMTSJedan(
            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        model.addAttribute("karton", karton);

        return "supervisor/kartonMTS";
    }
    
    
    
}
