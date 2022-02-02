/*
 * 
 */
package slobodan.siuvs2.controller.client;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Client;
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
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.PodvrstaMTS;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.model.VlasnikMTS;
import slobodan.siuvs2.model.VrstaMTS;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DetaljiMTSService;
import slobodan.siuvs2.service.GrupaMTSService;
import slobodan.siuvs2.service.OrgJedinicaMTSService;
import slobodan.siuvs2.service.PodvrstaMTSService;
import slobodan.siuvs2.service.VlasnikMTSService;
import slobodan.siuvs2.service.VrstaMTSService;
import slobodan.siuvs2.valueObject.GrupaMTSId;
import slobodan.siuvs2.valueObject.OrgJedinicaMTSId;
import slobodan.siuvs2.valueObject.PodvrstaMTSId;
import slobodan.siuvs2.valueObject.VlasnikMTSId;
import slobodan.siuvs2.valueObject.VrstaMTSId;
import slobodan.siuvs2.valueObject.DetaljiMTSId;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class KartonMTSController {

    @Autowired
    private ClientService clientService;

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

    @GetMapping(value = "/client/kartonMTS")
    public String clientKartonMTS(
            //            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);
        List<DetaljiMTS> listaKartona = detaljiMTSService.findAllByClient(client);
        model.addAttribute("listaKartona", listaKartona);

                List<VlasnikMTS> listaVlasnika = vlasnikMTSService.findAllByClient(client);
        model.addAttribute("listaVlasnika", listaVlasnika);

        List<OrgJedinicaMTS> listaOrgJedinica = orgJedinicaMTSService.findAllByClient(client);
        model.addAttribute("listaOrgJedinica", listaOrgJedinica);
        
        return "client/kartonMTS/kartonMTSLista";
    }

//    izmena detalja
    @GetMapping(value = "/client/kartonMTS/{kartonId}/izmeniKarton")
    public String clientKartonDetaljaEdit(
            //            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        model.addAttribute("karton", karton);

        List<VlasnikMTS> listaVlasnika = vlasnikMTSService.findAllByClient(client);
        model.addAttribute("listaVlasnika", listaVlasnika);

        List<OrgJedinicaMTS> listaOrgJedinica = orgJedinicaMTSService.findAllByClient(client);
        model.addAttribute("listaOrgJedinica", listaOrgJedinica);

        List<GrupaMTS> listaGrupa = grupaMTSService.findAllBy();
        model.addAttribute("listaGrupa", listaGrupa);

        List<VrstaMTS> listaVrsta = vrstaMTSService.findAllBy();
        model.addAttribute("listaVrsta", listaVrsta);

        List<PodvrstaMTS> listaPodvrsta = podvrstaMTSService.findAllBy();
        model.addAttribute("listaPodvrsta", listaPodvrsta);

        return "client/kartonMTS/editKartonDetalja";
    }

    @PostMapping(value = "/client/kartonMTS/{kartonId}/izmeniKarton")
    public String clientKartonDetaljaEditSave(
            //            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            @RequestParam(value = "vlasnik_id", defaultValue = "1") VlasnikMTSId vlasnik_id,
            @RequestParam(value = "orgJedinicaMTS_id", defaultValue = "1") OrgJedinicaMTSId orgJedinicaMTS_id,
            @RequestParam(value = "marka", defaultValue = "/") String marka,
            @RequestParam(value = "brojMTS_kolicina", defaultValue = "1") Integer brojMTS_kolicina,
            @RequestParam(value = "tip", defaultValue = "/") String tip,
            @RequestParam(value = "registracija", defaultValue = "/") String registracija,
            @RequestParam(value = "godina_proizvodnje", defaultValue = "/") String godina_proizvodnje,
            @RequestParam(value = "snaga", defaultValue = "/") String snaga,
            @RequestParam(value = "nosivost", defaultValue = "/") String nosivost,
            @RequestParam(value = "kapacitet", defaultValue = "/") String kapacitet,
            @RequestParam(value = "pogonsko_gorivo", defaultValue = "/") String pogonsko_gorivo,
            @RequestParam(value = "opisMTS", defaultValue = "/") String opisMTS,
            @RequestParam(value = "napomena", defaultValue = "/") String napomena,
            @RequestParam(value = "grupaMTS_id", defaultValue = "1") GrupaMTSId grupaMTS_id,
            @RequestParam(value = "vrstaMTS_id", defaultValue = "1") VrstaMTSId vrstaMTS_id,
            @RequestParam(value = "podvrstaMTS_id", defaultValue = "1") PodvrstaMTSId podvrstaMTS_id,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        
        if(vlasnik_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati vlasnika");
        return "redirect:/client/kartonMTS";
}
if(orgJedinicaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate organizacionu jedinicu");
        return "redirect:/client/kartonMTS";
}
if(grupaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati grupu MTS");
        return "redirect:/client/kartonMTS";
}
if(vrstaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati vrstu MTS");
        return "redirect:/client/kartonMTS";
}
if(podvrstaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati podvrstu MTS, izaberite \"/\" ukoliko nema drugog izbora");
        return "redirect:/client/kartonMTS";
}
        
        
        DetaljiMTS detalji = detaljiMTSService.findOne(kartonId);

        VlasnikMTS vlasnik = vlasnikMTSService.findOne(vlasnik_id);

        OrgJedinicaMTS orgJedinica = orgJedinicaMTSService.findOne(orgJedinicaMTS_id);

        GrupaMTS grupa = grupaMTSService.findOne(grupaMTS_id);

        VrstaMTS vrsta = vrstaMTSService.findOne(vrstaMTS_id);

        PodvrstaMTS podvrsta = podvrstaMTSService.findOne(podvrstaMTS_id);

        detalji.setClient(client);
        detalji.setVlasnikMTS(vlasnik);
        detalji.setOrgJedinicaMTS(orgJedinica);
        detalji.setMarka(marka);
        detalji.setBrojMTS_kolicina(brojMTS_kolicina);
        detalji.setTip(tip);
        detalji.setRegistracija(registracija);
        detalji.setGodina_proizvodnje(godina_proizvodnje);
        detalji.setSnaga(snaga);
        detalji.setNosivost(nosivost);
        detalji.setKapacitet(kapacitet);
        detalji.setPogonsko_gorivo(pogonsko_gorivo);
        detalji.setOpisMTS(opisMTS);
        detalji.setNapomena(napomena);
        detalji.setGrupaMTS(grupa);
        detalji.setVrstaMTS(vrsta);
        detalji.setPodvrstaMTS(podvrsta);

        try {
            detaljiMTSService.save(detalji);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
        }

        redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
        return "redirect:/client/kartonMTS";

    }

    /*izmena vlasnika*/
    @GetMapping(value = "/client/kartonMTS/{kartonId}/izmeniVlasnika")
    public String clientKartonVlasnikaEdit(
            //            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        model.addAttribute("karton", karton);

        List<VlasnikMTS> listaVlasnika = vlasnikMTSService.findAllByClient(client);
        model.addAttribute("listaVlasnika", listaVlasnika);

        return "client/kartonMTS/editVlasnika";
    }

    @PostMapping(value = "/client/kartonMTS/{kartonId}/izmeniVlasnika")
    public String clientKartonVlasnikaEditSave(
            //            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            //   @PathVariable final VlasnikMTSId kartonId,
            @RequestParam(value = "vlasnik_naziv", defaultValue = "/") String vlasnik_naziv,
            //            @RequestParam(value = "vlasnik_id", defaultValue = "1") VlasnikMTSId vlasnik_id,
            @RequestParam(value = "adresa", defaultValue = "/") String adresa,
            @RequestParam(value = "gradOpstina", defaultValue = "/") String gradOpstina,
            @RequestParam(value = "upravni_okrug", defaultValue = "/") String upravni_okrug,
            @RequestParam(value = "telefon", defaultValue = "/") String telefon,
            @RequestParam(value = "email", defaultValue = "/") String email,
            @RequestParam(value = "odgovorno_lice", defaultValue = "/") String odgovorno_lice,
            @RequestParam(value = "fiksni_tel_odg_lica", defaultValue = "/") String fiksni_tel_odg_lica,
            @RequestParam(value = "mobilni_tel_odg_lica", defaultValue = "/") String mobilni_tel_odg_lica,
            @RequestParam(value = "email_odg_lica", defaultValue = "/") String email_odg_lica,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        VlasnikMTS vlasnik = karton.getVlasnikMTS();

        //     VlasnikMTS vlasnik = vlasnikMTSService.findOne(kartonId);
        vlasnik.setClient(client);
        vlasnik.setVlasnik_naziv(vlasnik_naziv);

        vlasnik.setAdresa(adresa);
        vlasnik.setGradOpstina(gradOpstina);
        vlasnik.setUpravni_okrug(upravni_okrug);
        vlasnik.setTelefon(telefon);
        vlasnik.setEmail(email);
        vlasnik.setOdgovorno_lice(odgovorno_lice);
        vlasnik.setFiksni_tel_odg_lica(fiksni_tel_odg_lica);
        vlasnik.setMobilni_tel_odg_lica(mobilni_tel_odg_lica);
        vlasnik.setEmail_odg_lica(fiksni_tel_odg_lica);

        try {
//            detaljiMTSService.save(detalji);
            vlasnikMTSService.save(vlasnik);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
        }

        redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
        return "redirect:/client/kartonMTS";

    }

    /*izmena org jedinice*/
    @GetMapping(value = "/client/kartonMTS/{kartonId}/izmeniOrgJedinicu")
    public String clientOrgJedinicaEdit(
            //            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        
        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        
        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        model.addAttribute("karton", karton);

        List<OrgJedinicaMTS> listaOrgJedinica = orgJedinicaMTSService.findAllByClient(client);
        model.addAttribute("listaOrgJedinica", listaOrgJedinica);

        return "client/kartonMTS/editOrgJedinica";
    }

    @PostMapping(value = "/client/kartonMTS/{kartonId}/izmeniOrgJedinicu")
    public String clientOrgJedinicaEditSave(

            @PathVariable final DetaljiMTSId kartonId,
       //   @PathVariable final OrgJedinicaMTSId orgJedinicaId,
       //   @RequestParam(value = "vlasnik_naziv", defaultValue = "/") String vlasnik_naziv,
            @RequestParam(value = "vlasnik_id", defaultValue = "1") VlasnikMTSId vlasnik_id,
       //   @RequestParam(value = "orgJedinicaMTS_id", defaultValue = "1") OrgJedinicaMTSId orgJedinicaMTS_id,
            @RequestParam(value = "odgovorno_lice", defaultValue = "/") String odgovorno_lice,
            @RequestParam(value = "fiksni_tel_odg_lica", defaultValue = "/") String fiksni_tel_odg_lica,
            @RequestParam(value = "mobilni_tel_odg_lica", defaultValue = "/") String mobilni_tel_odg_lica,
            @RequestParam(value = "email_odg_lica", defaultValue = "/") String email_odg_lica,
            @RequestParam(value = "mts_adresa", defaultValue = "/") String mts_adresa,
            @RequestParam(value = "geografska_sirina_mts", defaultValue = "/") String geografska_sirina_mts,
            @RequestParam(value = "geografska_duzina_mts", defaultValue = "/") String geografska_duzina_mts,
            @RequestParam(value = "nadlezna_org_jednica_svs", defaultValue = "/") String nadlezna_org_jednica_svs,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        VlasnikMTS vlasnik = vlasnikMTSService.findOne(vlasnik_id);

        OrgJedinicaMTS orgJedinica = karton.getOrgJedinicaMTS();
        model.addAttribute("orgJedinica", orgJedinica);

        orgJedinica.setClient(client);

        orgJedinica.setVlasnikMTS(vlasnik);
        orgJedinica.setOdgovorno_lice(odgovorno_lice);
        orgJedinica.setFiksni_tel_odg_lica(fiksni_tel_odg_lica);
        orgJedinica.setMobilni_tel_odg_lica(mobilni_tel_odg_lica);
        orgJedinica.setEmail_odg_lica(email_odg_lica);
        orgJedinica.setMts_adresa(mts_adresa);
        orgJedinica.setGeografska_sirina_mts(geografska_sirina_mts);
        orgJedinica.setGeografska_duzina_mts(geografska_duzina_mts);
        orgJedinica.setNadlezna_org_jednica_svs(nadlezna_org_jednica_svs);

        try {
//            detaljiMTSService.save(detalji);
            orgJedinicaMTSService.save(orgJedinica);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Organizaciona jedinica nije uspešno izmenjena!");
        }

        redirectAttributes.addFlashAttribute("successMessage", "Organizaciona jedinica je uspešno izmenjena!");
        return "redirect:/client/kartonMTS";

    }

    @GetMapping(value = "/client/kartonMTS/{kartonId}")
    public String clientKartonMTSJedan(
            //            @PathVariable final ClientId clientId,
            @PathVariable final DetaljiMTSId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        DetaljiMTS karton = detaljiMTSService.findOne(kartonId);
        model.addAttribute("karton", karton);

        return "client/kartonMTS/kartonMTS";
    }

    @GetMapping(value = "/client/kartonMTS/dodajVlasnika")
    public String clientKartonMTSVlasnikNew(
            //            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        //   List<VlasnikMTS> listaVlasnika = vlasnikMTSService.findAllByClient(client);
        // model.addAttribute("listaVlasnika", listaVlasnika);
        return "client/kartonMTS/newVlasnik";
    }

    @PostMapping(value = "/client/kartonMTS/dodajVlasnika")
    public String clientKartonMTSVlasnikNewSave(
            //            @PathVariable final ClientId clientId,
            @RequestParam(value = "vlasnik_naziv", defaultValue = "/") String vlasnik_naziv,
            @RequestParam(value = "adresa", defaultValue = "/") String adresa,
            @RequestParam(value = "gradOpstina", defaultValue = "/") String gradOpstina,
            @RequestParam(value = "upravni_okrug", defaultValue = "/") String upravni_okrug,
            @RequestParam(value = "telefon", defaultValue = "/") String telefon,
            @RequestParam(value = "email", defaultValue = "/") String email,
            @RequestParam(value = "odgovorno_lice", defaultValue = "/") String odgovorno_lice,
            @RequestParam(value = "fiksni_tel_odg_lica", defaultValue = "/") String fiksni_tel_odg_lica,
            @RequestParam(value = "mobilni_tel_odg_lica", defaultValue = "/") String mobilni_tel_odg_lica,
            @RequestParam(value = "email_odg_lica", defaultValue = "/") String email_odg_lica,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        VlasnikMTS noviVlasnik = new VlasnikMTS();

//        KartonUdruzenja karton = new KartonUdruzenja();
        noviVlasnik.setClient(client);

        noviVlasnik.setVlasnik_naziv(vlasnik_naziv);
        noviVlasnik.setAdresa(adresa);
        noviVlasnik.setGradOpstina(gradOpstina);
        noviVlasnik.setUpravni_okrug(upravni_okrug);
        noviVlasnik.setTelefon(telefon);
        noviVlasnik.setEmail(email);
        noviVlasnik.setOdgovorno_lice(odgovorno_lice);
        noviVlasnik.setFiksni_tel_odg_lica(fiksni_tel_odg_lica);
        noviVlasnik.setMobilni_tel_odg_lica(mobilni_tel_odg_lica);
        noviVlasnik.setEmail_odg_lica(email_odg_lica);

        try {
            vlasnikMTSService.save(noviVlasnik);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }

        redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
        return "redirect:/client/kartonMTS";

    }

    @GetMapping(value = "/client/kartonMTS/dodajOrgJedinicu")
    public String clientKartonMTSOrgJedinicaNew(
            //            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        List<VlasnikMTS> listaVlasnika = vlasnikMTSService.findAllByClient(client);
        model.addAttribute("listaVlasnika", listaVlasnika);

        return "client/kartonMTS/newOrgJedinica";
    }

    @PostMapping(value = "/client/kartonMTS/dodajOrgJedinicu")
    public String clientKartonMTSOrgJedinicaNewSave(
            //            @PathVariable final ClientId clientId,
            @RequestParam(value = "vlasnik_id", defaultValue = "1") VlasnikMTSId vlasnik_id,
            @RequestParam(value = "naziv_org_jedinice", defaultValue = "/") String naziv_org_jedinice,
            @RequestParam(value = "odgovorno_lice", defaultValue = "/") String odgovorno_lice,
            @RequestParam(value = "fiksni_tel_odg_lica", defaultValue = "/") String fiksni_tel_odg_lica,
            @RequestParam(value = "mobilni_tel_odg_lica", defaultValue = "/") String mobilni_tel_odg_lica,
            @RequestParam(value = "email_odg_lica", defaultValue = "/") String email_odg_lica,
            @RequestParam(value = "mts_adresa", defaultValue = "/") String mts_adresa,
            @RequestParam(value = "geografska_sirina_mts", defaultValue = "/") String geografska_sirina_mts,
            @RequestParam(value = "geografska_duzina_mts", defaultValue = "/") String geografska_duzina_mts,
            @RequestParam(value = "nadlezna_org_jednica_svs", defaultValue = "/") String nadlezna_org_jednica_svs,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        VlasnikMTS vlasnik = vlasnikMTSService.findOne(vlasnik_id);

        OrgJedinicaMTS novaOrgJedinica = new OrgJedinicaMTS();

        novaOrgJedinica.setClient(client);

        novaOrgJedinica.setVlasnikMTS(vlasnik);

        novaOrgJedinica.setNaziv_org_jedinice(naziv_org_jedinice);
        novaOrgJedinica.setFiksni_tel_odg_lica(fiksni_tel_odg_lica);
        novaOrgJedinica.setMobilni_tel_odg_lica(mobilni_tel_odg_lica);
        novaOrgJedinica.setEmail_odg_lica(email_odg_lica);
        novaOrgJedinica.setMts_adresa(mts_adresa);
        novaOrgJedinica.setGeografska_sirina_mts(geografska_sirina_mts);
        novaOrgJedinica.setGeografska_duzina_mts(geografska_duzina_mts);
        novaOrgJedinica.setNadlezna_org_jednica_svs(nadlezna_org_jednica_svs);

        try {
            orgJedinicaMTSService.save(novaOrgJedinica);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nova organizaciona jedinica nije uspešno sačuvana!");
        }

        redirectAttributes.addFlashAttribute("successMessage", "Nova organizaciona jedinica je uspešno sačuvana!");
        return "redirect:/client/kartonMTS";

    }

    @GetMapping(value = "/client/kartonMTS/newKarton")
    public String clientKartonMTSNew(
            //            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        List<VlasnikMTS> listaVlasnika = vlasnikMTSService.findAllByClient(client);
        model.addAttribute("listaVlasnika", listaVlasnika);

        List<OrgJedinicaMTS> listaOrgJedinica = orgJedinicaMTSService.findAllByClient(client);
        model.addAttribute("listaOrgJedinica", listaOrgJedinica);

        List<GrupaMTS> listaGrupa = grupaMTSService.findAllBy();
        model.addAttribute("listaGrupa", listaGrupa);

        List<VrstaMTS> listaVrsta = vrstaMTSService.findAllBy();
        model.addAttribute("listaVrsta", listaVrsta);

        List<PodvrstaMTS> listaPodvrsta = podvrstaMTSService.findAllBy();
        model.addAttribute("listaPodvrsta", listaPodvrsta);

        return "client/kartonMTS/newKarton";
    }

    @PostMapping(value = "/client/kartonMTS/newKarton")
    public String clientKartonMTSNewSave(
            //            @PathVariable final ClientId clientId,
            @RequestParam(value = "vlasnik_id", defaultValue = "1") VlasnikMTSId vlasnik_id,
            @RequestParam(value = "orgJedinicaMTS_id", defaultValue = "1") OrgJedinicaMTSId orgJedinicaMTS_id,
            @RequestParam(value = "marka", defaultValue = "/") String marka,
            @RequestParam(value = "brojMTS_kolicina", defaultValue = "1") Integer brojMTS_kolicina,
            @RequestParam(value = "tip", defaultValue = "/") String tip,
            @RequestParam(value = "registracija", defaultValue = "/") String registracija,
            @RequestParam(value = "godina_proizvodnje", defaultValue = "/") String godina_proizvodnje,
            @RequestParam(value = "snaga", defaultValue = "/") String snaga,
            @RequestParam(value = "nosivost", defaultValue = "/") String nosivost,
            @RequestParam(value = "kapacitet", defaultValue = "/") String kapacitet,
            @RequestParam(value = "pogonsko_gorivo", defaultValue = "/") String pogonsko_gorivo,
            @RequestParam(value = "opisMTS", defaultValue = "/") String opisMTS,
            @RequestParam(value = "napomena", defaultValue = "/") String napomena,
            @RequestParam(value = "grupaMTS_id", defaultValue = "1") GrupaMTSId grupaMTS_id,
            @RequestParam(value = "vrstaMTS_id", defaultValue = "1") VrstaMTSId vrstaMTS_id,
            @RequestParam(value = "podvrstaMTS_id", defaultValue = "1") PodvrstaMTSId podvrstaMTS_id,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
//        Client client = clientService.findOne(clientId);
//        model.addAttribute("client", client);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        if(vlasnik_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati vlasnika");
        return "redirect:/client/kartonMTS";
}
if(orgJedinicaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate organizacionu jedinicu");
        return "redirect:/client/kartonMTS";
}
if(grupaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati grupu MTS");
        return "redirect:/client/kartonMTS";
}
if(vrstaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati vrstu MTS");
        return "redirect:/client/kartonMTS";
}
if(podvrstaMTS_id.getValue()==0){
 redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen! Morate izabrati podvrstu MTS, izaberite \"/\" ukoliko nema drugog izbora");
        return "redirect:/client/kartonMTS";
}
        VlasnikMTS vlasnik = vlasnikMTSService.findOne(vlasnik_id);

        OrgJedinicaMTS orgJedinica = orgJedinicaMTSService.findOne(orgJedinicaMTS_id);

        GrupaMTS grupa = grupaMTSService.findOne(grupaMTS_id);

        VrstaMTS vrsta = vrstaMTSService.findOne(vrstaMTS_id);

        PodvrstaMTS podvrsta = podvrstaMTSService.findOne(podvrstaMTS_id);

        // OrgJedinicaMTS novaOrgJedinica = new OrgJedinicaMTS();
        DetaljiMTS detalji = new DetaljiMTS();
        detalji.setClient(client);
        detalji.setVlasnikMTS(vlasnik);
        detalji.setOrgJedinicaMTS(orgJedinica);
        detalji.setMarka(marka);
        detalji.setBrojMTS_kolicina(brojMTS_kolicina);
        detalji.setTip(tip);
        detalji.setRegistracija(registracija);
        detalji.setGodina_proizvodnje(godina_proizvodnje);
        detalji.setSnaga(snaga);
        detalji.setNosivost(nosivost);
        detalji.setKapacitet(kapacitet);
        detalji.setPogonsko_gorivo(pogonsko_gorivo);
        detalji.setOpisMTS(opisMTS);
        detalji.setNapomena(napomena);
        detalji.setGrupaMTS(grupa);
        detalji.setVrstaMTS(vrsta);
        detalji.setPodvrstaMTS(podvrsta);

        try {
            detaljiMTSService.save(detalji);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }

        redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
        return "redirect:/client/kartonMTS";

    }

}
