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
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.model.OsobaSOT;
import slobodan.siuvs2.model.OsobaStab;
import slobodan.siuvs2.model.SiuvsUserPrincipal;

import slobodan.siuvs2.model.StabVS;
import slobodan.siuvs2.model.StrucnoOT;
import slobodan.siuvs2.model.User;
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

public class KartonClanaStabaController {

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
    
     @GetMapping(value = "/client/kartonClanaStaba")
    public String clientkartonStabaiSotova(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        StabVS stab = stabVSService.findFirstByClient(client);
        List<StrucnoOT> sotovi = sotService.findAllByClient(client);
        model.addAttribute("stab", stab);
        model.addAttribute("sotovi", sotovi);
    
        return "client/kartonClanovaStaba/kartonClanaStabaLista";
    }
    
     @GetMapping(value = "/client/pregledSOTA/{sotId}")
    public String adminpregledSOTA(
            
             @PathVariable final StrucnoOTId sotId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
    
        model.addAttribute("sot", sotService.findOne(sotId));
    
        return "client/kartonClanovaStaba/kartonClanaSotaLista";
    }
    
    
    
    
@GetMapping(value = "/client/kartonClanaSOT/{osobaSOTId}/editKarton")
    public String clientkartonOsobaSOTEdit(
            
            @PathVariable final OsobaSOTId osobaSOTId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
OsobaSOT sotoba=osobaSOTService.findOne(osobaSOTId);
       
model.addAttribute("osobaSot", sotoba);
model.addAttribute("osoba", sotoba.getOsoba());
        return "client/kartonClanovaStaba/editKartonClanaSota";
    }
   
    
   
         
    @GetMapping(value = "/client/kartonClanaSOT/{osobaSOTId}")
    public String adminClanSOTaPregled(
            
            @PathVariable final OsobaSOTId osobaSOTId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
         model.addAttribute("osobaSOT", osobaSOTService.findOne(osobaSOTId));
    
        return "client/kartonClanovaStaba/KartonClanaSota";
    }
    
    
     @GetMapping(value = "/client/kartonClanaSOT/{sotId}/newKarton")
    public String adminNewClanSOTa(
            
            @PathVariable final StrucnoOTId sotId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
         model.addAttribute("sot", sotService.findOne(sotId));
    
        return "client/kartonClanovaStaba/newKartonClanaSota";
    }
     
    
    @GetMapping(value = "/client/kartonClanaStaba/newSOT")
    public String adminNewSOT(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
       
    
        return "client/kartonClanovaStaba/newSOT";
    }
   
    
    @GetMapping(value = "/client/kartonClanaStaba/{osobaStabId}")
    public String adminClanStabaPregled(
            
               @PathVariable final OsobaStabId osobaStabId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
       
model.addAttribute("osobaStab", osobaStabService.findOne(osobaStabId));

        return "client/kartonClanovaStaba/kartonClanaStaba";
    }
        @GetMapping(value = "/client/kartonClanaStaba/{osobaId}/editKarton")
    public String clientkartonOsobaEdit(
            
            @PathVariable final OsobaId osobaId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
Osoba osoba = osobaService.findOne(osobaId);
        StabVS stab = stabVSService.findFirstByClient(client);
       
model.addAttribute("osobaStab", osobaStabService.findFirstByOsobaAndStab(osoba, stab));
model.addAttribute("osoba", osoba);
        return "client/kartonClanovaStaba/editKartonClanaStaba";
    }
    
    
    @GetMapping(value = "/client/newStab")
    public String adminStabDodaj(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        return "client/kartonClanovaStaba/newStab";
    }
        @GetMapping(value = "/client/editStab")
    public String adminStabEdit(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
 model.addAttribute("stab", stabVSService.findFirstByClient(client));
        return "client/kartonClanovaStaba/editStab";
    }

    @GetMapping(value = "/client/kartonClanaStaba/newKarton")
    public String clientkartonUdruzenjaNew(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        return "client/kartonClanovaStaba/newKartonClanaStaba";
    }
    
    
    
@PostMapping(value = "/client/kartonClanaStaba/{osobaId}/editKarton")
    public String adminEditClanStabaSave(
            
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
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }

    
@PostMapping(value = "/client/kartonClanaStaba/newKarton")
    public String adminNewClanStabaSave(
            
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }
    
    @PostMapping(value = "/client/newStab")
    public String adminStabNewSave(
            
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }
    @PostMapping(value = "/client/editStab")
    public String adminStabaEditSave(
            
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }
    
     @PostMapping(value = "/client/newSOT")
    public String adminNewSOTSave(
            
            @RequestParam(value = "action", required = true) String action,
     
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
           
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }
    
    
@PostMapping(value = "/client/kartonClanaSOT/{osobaSOTId}/editKarton")
    public String adminEditClanSOTSave(
            
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }
    
@PostMapping(value = "/client/kartonClanaSOT/{sotId}/newKarton")
    public String adminNewClanStabaSave(
            
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
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
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
            return "redirect:/client/kartonClanaStaba";
    
    }
    
    
}
