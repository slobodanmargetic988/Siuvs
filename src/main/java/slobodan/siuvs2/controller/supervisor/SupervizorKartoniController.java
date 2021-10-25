/*
 * 
 */
package slobodan.siuvs2.controller.supervisor;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.StabVS;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DelatnostService;
import slobodan.siuvs2.service.DetaljiMTSService;
import slobodan.siuvs2.service.GrupaMTSService;
import slobodan.siuvs2.service.KadroviService;
import slobodan.siuvs2.service.KartonSubjektiService;
import slobodan.siuvs2.service.KartonUdruzenjaService;
import slobodan.siuvs2.service.OrgJedinicaMTSService;
import slobodan.siuvs2.service.OsobaSOTService;
import slobodan.siuvs2.service.OsobaService;
import slobodan.siuvs2.service.OsobaStabService;
import slobodan.siuvs2.service.PodvrstaMTSService;
import slobodan.siuvs2.service.StabVSService;
import slobodan.siuvs2.service.StrucnoOTService;
import slobodan.siuvs2.service.VlasnikMTSService;
import slobodan.siuvs2.service.VrstaMTSService;
import slobodan.siuvs2.service.ZanimanjaService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DetaljiMTSId;
import slobodan.siuvs2.valueObject.KartonClanovaStabaId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.KartonUdruzenjaId;
import slobodan.siuvs2.valueObject.OsobaSOTId;
import slobodan.siuvs2.valueObject.OsobaStabId;
import slobodan.siuvs2.valueObject.StrucnoOTId;

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
    private DetaljiMTSService detaljiMTSService;
    @Autowired
    private OsobaSOTService osobaSOTService;
@Autowired
    private OsobaStabService osobaStabService;
@Autowired
    private OsobaService osobaService;
@Autowired
    private StabVSService stabVSService;
@Autowired
    private StrucnoOTService sotService;
   

    

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
    
       @GetMapping(value = "/supervisor/clients/{clientId}/kartonClanaStaba")
    public String supervisorkartonClanaStaba(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
 StabVS stab = stabVSService.findFirstByClient(client);
        List<StrucnoOT> sotovi = sotService.findAllByClient(client);
        model.addAttribute("stab", stab);
        model.addAttribute("sotovi", sotovi);
    

        return "supervisor/kartonClanaStabaLista";
    }

    @GetMapping(value = "/supervisor/clients/{clientId}/kartonClanaStaba/{osobaStabId}")
    public String supervisorClanStabaPregled(
            @PathVariable final ClientId clientId,
               @PathVariable final OsobaStabId osobaStabId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
       
model.addAttribute("osobaStab", osobaStabService.findOne(osobaStabId));

        return "supervisor/kartonClanaStaba";
    }
    
    @GetMapping(value = "/supervisor/clients/{clientId}/pregledSOTA/{sotId}")
    public String supervisorpregledSOTA(
            @PathVariable final ClientId clientId,
             @PathVariable final StrucnoOTId sotId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
    
        model.addAttribute("sot", sotService.findOne(sotId));
    
        return "supervisor/kartonClanaSotaLista";
    }
     @GetMapping(value = "/supervisor/clients/{clientId}/kartonClanaSOT/{osobaSOTId}")
    public String adminClanSOTaPregled(
            @PathVariable final ClientId clientId,
            @PathVariable final OsobaSOTId osobaSOTId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
         model.addAttribute("osobaSOT", osobaSOTService.findOne(osobaSOTId));
    
        return "supervisor/KartonClanaSota";
    }
}
