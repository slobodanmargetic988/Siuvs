/*
 *
 */
package slobodan.siuvs2.controller.admin.clients;

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
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.model.OsobaSOT;
import slobodan.siuvs2.model.OsobaStab;
import slobodan.siuvs2.model.StabVS;
import slobodan.siuvs2.model.StrucnoOT;

import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.OsobaSOTService;
import slobodan.siuvs2.service.OsobaService;
import slobodan.siuvs2.service.OsobaStabService;

import slobodan.siuvs2.service.StabVSService;
import slobodan.siuvs2.service.StrucnoOTService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.OsobaId;
import slobodan.siuvs2.valueObject.OsobaSOTId;
import slobodan.siuvs2.valueObject.OsobaStabId;
import slobodan.siuvs2.valueObject.StrucnoOTId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class ClientsKartonClanaStabaController {

    @Autowired
    private ClientService clientService;
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
    
     @GetMapping(value = "/admin/clients/{clientId}/kartonClanaStaba")
    public String adminkartonStabaiSotova(
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
    
        return "admin/clients/kartonClanovaStaba/kartonClanaStabaLista";
    }
    
     @GetMapping(value = "/admin/clients/{clientId}/pregledSOTA/{sotId}")
    public String adminpregledSOTA(
            @PathVariable final ClientId clientId,
             @PathVariable final StrucnoOTId sotId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
    
        model.addAttribute("sot", sotService.findOne(sotId));
    
        return "admin/clients/kartonClanovaStaba/kartonClanaSotaLista";
    }
    
    
    
    
@GetMapping(value = "/admin/clients/{clientId}/kartonClanaSOT/{osobaSOTId}/editKarton")
    public String adminkartonOsobaSOTEdit(
            @PathVariable final ClientId clientId,
            @PathVariable final OsobaSOTId osobaSOTId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
OsobaSOT sotoba=osobaSOTService.findOne(osobaSOTId);
       
model.addAttribute("osobaSot", sotoba);
model.addAttribute("osoba", sotoba.getOsoba());
        return "admin/clients/kartonClanovaStaba/editKartonClanaSota";
    }
   
    
   
         
    @GetMapping(value = "/admin/clients/{clientId}/kartonClanaSOT/{osobaSOTId}")
    public String adminClanSOTaPregled(
            @PathVariable final ClientId clientId,
            @PathVariable final OsobaSOTId osobaSOTId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
         model.addAttribute("osobaSOT", osobaSOTService.findOne(osobaSOTId));
    
        return "admin/clients/kartonClanovaStaba/KartonClanaSota";
    }
    
    
     @GetMapping(value = "/admin/clients/{clientId}/kartonClanaSOT/{sotId}/newKarton")
    public String adminNewClanSOTa(
            @PathVariable final ClientId clientId,
            @PathVariable final StrucnoOTId sotId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
         model.addAttribute("sot", sotService.findOne(sotId));
    
        return "admin/clients/kartonClanovaStaba/newKartonClanaSota";
    }
     
    
    @GetMapping(value = "/admin/clients/{clientId}/kartonClanaStaba/newSOT")
    public String adminNewSOT(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
       
    
        return "admin/clients/kartonClanovaStaba/newSOT";
    }
   
    
    @GetMapping(value = "/admin/clients/{clientId}/kartonClanaStaba/{osobaStabId}")
    public String adminClanStabaPregled(
            @PathVariable final ClientId clientId,
               @PathVariable final OsobaStabId osobaStabId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
       
model.addAttribute("osobaStab", osobaStabService.findOne(osobaStabId));

        return "admin/clients/kartonClanovaStaba/kartonClanaStaba";
    }
        @GetMapping(value = "/admin/clients/{clientId}/kartonClanaStaba/{osobaId}/editKarton")
    public String adminkartonOsobaEdit(
            @PathVariable final ClientId clientId,
            @PathVariable final OsobaId osobaId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
Osoba osoba = osobaService.findOne(osobaId);
        StabVS stab = stabVSService.findFirstByClient(client);
       
model.addAttribute("osobaStab", osobaStabService.findFirstByOsobaAndStab(osoba, stab));
model.addAttribute("osoba", osoba);
        return "admin/clients/kartonClanovaStaba/editKartonClanaStaba";
    }
    
    
    @GetMapping(value = "/admin/clients/{clientId}/newStab")
    public String adminStabDodaj(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);

        return "admin/clients/kartonClanovaStaba/newStab";
    }
        @GetMapping(value = "/admin/clients/{clientId}/editStab")
    public String adminStabEdit(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
 model.addAttribute("stab", stabVSService.findFirstByClient(client));
        return "admin/clients/kartonClanovaStaba/editStab";
    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonClanaStaba/newKarton")
    public String adminkartonUdruzenjaNew(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);

        return "admin/clients/kartonClanovaStaba/newKartonClanaStaba";
    }
    
    
    
@PostMapping(value = "/admin/clients/{clientId}/kartonClanaStaba/{osobaId}/editKarton")
    public String adminEditClanStabaSave(
            @PathVariable final ClientId clientId,
            @PathVariable final OsobaId osobaId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "funkcija_u_stabu", defaultValue = "/") String funkcija_u_stabu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
             @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
              @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
               @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
                @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
                 @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
                  @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
                  @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        StabVS stab = stabVSService.findFirstByClient(client);
        
        Osoba novaOsoba= osobaService.findOne(osobaId);
        OsobaStab noviClan= osobaStabService.findFirstByOsobaAndStab(novaOsoba, stab);
  
        noviClan.setFunkcija(funkcija_u_stabu);
        
    novaOsoba.setIme(punoime);
    novaOsoba.setRadnomesto(naziv_organa_gde_radi);
novaOsoba.setAdresaposao(adresa_organa_gde_radi);
novaOsoba.setFunkcijaposao(funkcija_na_radu);
novaOsoba.setMobilniposao(mobilni_telefon_sluzbeni);
novaOsoba.setFiksniposao(fiksni_telefon_sluzbeni);
novaOsoba.setEmailposao(email_sluzbeni);
novaOsoba.setEmail(email_licni);
novaOsoba.setAdresa(adresa_stanovanja);
novaOsoba.setTelefon(telefon_u_stanu);
novaOsoba.setObuke(ucesce_na_obukama);
        try {
            osobaService.save(novaOsoba);
       
            osobaStabService.save(noviClan);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji štaba su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }

    
@PostMapping(value = "/admin/clients/{clientId}/kartonClanaStaba/newKarton")
    public String adminNewClanStabaSave(
            @PathVariable final ClientId clientId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "funkcija_u_stabu", defaultValue = "/") String funkcija_u_stabu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
             @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
              @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
               @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
                @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
                 @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
                  @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
                  @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        StabVS stab = stabVSService.findFirstByClient(client);
        
        Osoba novaOsoba= new Osoba();
        OsobaStab noviClan= new OsobaStab();
        noviClan.setStab(stab);
        noviClan.setFunkcija(funkcija_u_stabu);
        
    novaOsoba.setIme(punoime);
    novaOsoba.setRadnomesto(naziv_organa_gde_radi);
novaOsoba.setAdresaposao(adresa_organa_gde_radi);
novaOsoba.setFunkcijaposao(funkcija_na_radu);
novaOsoba.setMobilniposao(mobilni_telefon_sluzbeni);
novaOsoba.setFiksniposao(fiksni_telefon_sluzbeni);
novaOsoba.setEmailposao(email_sluzbeni);
novaOsoba.setEmail(email_licni);
novaOsoba.setAdresa(adresa_stanovanja);
novaOsoba.setTelefon(telefon_u_stanu);
novaOsoba.setObuke(ucesce_na_obukama);
        try {
            osobaService.save(novaOsoba);
            noviClan.setOsoba(novaOsoba);
            osobaStabService.save(noviClan);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji štaba su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }
    
    @PostMapping(value = "/admin/clients/{clientId}/newStab")
    public String adminStabNewSave(
            @PathVariable final ClientId clientId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        StabVS karton = new StabVS();
        karton.setClient(client);
        karton.setNaziv(naziv_staba_vanr_situacije);
        karton.setAdresa(adresa_staba);
        karton.setFiksni(fiksni_telefon_staba);
        karton.setMobilni(mobilni_telefon_staba);
        karton.setEmail(email_staba);
        try {
            stabVSService.save(karton);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji štaba su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }
    @PostMapping(value = "/admin/clients/{clientId}/editStab")
    public String adminStabaEditSave(
            @PathVariable final ClientId clientId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        StabVS karton = stabVSService.findFirstByClient(client);
        karton.setNaziv(naziv_staba_vanr_situacije);
        karton.setAdresa(adresa_staba);
        karton.setFiksni(fiksni_telefon_staba);
        karton.setMobilni(mobilni_telefon_staba);
        karton.setEmail(email_staba);
        try {
            stabVSService.save(karton);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji štaba su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }
    
     @PostMapping(value = "/admin/clients/{clientId}/newSOT")
    public String adminNewSOTSave(
            @PathVariable final ClientId clientId,
            @RequestParam(value = "action", required = true) String action,
     
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
           
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        StrucnoOT sot = new StrucnoOT();
       sot.setClient(client);
       sot.setNaziv(naziv);
        try {
            sotService.save(sot);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov tim nije uspešno sačuvan!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji SOT su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }
    
    
@PostMapping(value = "/admin/clients/{clientId}/kartonClanaSOT/{osobaSOTId}/editKarton")
    public String adminEditClanSOTSave(
            @PathVariable final ClientId clientId,
            @PathVariable final OsobaSOTId osobaSOTId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "funkcija_u_sotu", defaultValue = "/") String funkcija_u_sotu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
             @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
              @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
               @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
                @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
                 @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
                  @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
                  @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
  OsobaSOT sotoba=osobaSOTService.findOne(osobaSOTId);
        
        Osoba novaOsoba= sotoba.getOsoba();
       
  
        sotoba.setFunkcija(funkcija_u_sotu);
        
    novaOsoba.setIme(punoime);
    novaOsoba.setRadnomesto(naziv_organa_gde_radi);
novaOsoba.setAdresaposao(adresa_organa_gde_radi);
novaOsoba.setFunkcijaposao(funkcija_na_radu);
novaOsoba.setMobilniposao(mobilni_telefon_sluzbeni);
novaOsoba.setFiksniposao(fiksni_telefon_sluzbeni);
novaOsoba.setEmailposao(email_sluzbeni);
novaOsoba.setEmail(email_licni);
novaOsoba.setAdresa(adresa_stanovanja);
novaOsoba.setTelefon(telefon_u_stanu);
novaOsoba.setObuke(ucesce_na_obukama);
        try {
            osobaService.save(novaOsoba);
       
            osobaSOTService.save(sotoba);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Detalji nisu uspešno sačuvani!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }
    
@PostMapping(value = "/admin/clients/{clientId}/kartonClanaSOT/{sotId}/newKarton")
    public String adminNewClanStabaSave(
            @PathVariable final ClientId clientId,
             @PathVariable final StrucnoOTId sotId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "funkcija_u_sotu", defaultValue = "/") String funkcija_u_sotu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
             @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
              @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
               @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
                @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
                 @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
                  @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
                  @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        StrucnoOT sot = sotService.findOne(sotId);
        
        Osoba novaOsoba= new Osoba();
        OsobaSOT noviClan= new OsobaSOT();
        noviClan.setSot(sot);
        noviClan.setFunkcija(funkcija_u_sotu);
        
    novaOsoba.setIme(punoime);
    novaOsoba.setRadnomesto(naziv_organa_gde_radi);
novaOsoba.setAdresaposao(adresa_organa_gde_radi);
novaOsoba.setFunkcijaposao(funkcija_na_radu);
novaOsoba.setMobilniposao(mobilni_telefon_sluzbeni);
novaOsoba.setFiksniposao(fiksni_telefon_sluzbeni);
novaOsoba.setEmailposao(email_sluzbeni);
novaOsoba.setEmail(email_licni);
novaOsoba.setAdresa(adresa_stanovanja);
novaOsoba.setTelefon(telefon_u_stanu);
novaOsoba.setObuke(ucesce_na_obukama);
        try {
            osobaService.save(novaOsoba);
            noviClan.setOsoba(novaOsoba);
            osobaSOTService.save(noviClan);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }
            redirectAttributes.addFlashAttribute("successMessage", "Detalji štaba su uspešno sačuvani!");
            return "redirect:/admin/clients/" + clientId + "/kartonClanaStaba";
    
    }
    
    
}
