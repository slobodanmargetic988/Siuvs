/*
 * 
 */
package slobodan.siuvs2.controller.client;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.controller.admin.clients.*;
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
import slobodan.siuvs2.model.CiljeviUdruzenja;
import slobodan.siuvs2.model.ClanoviUdruzenja;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.Specijalnost;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.CiljeviUdruzenjaService;
import slobodan.siuvs2.service.ClanoviUdruzenjaService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.KartonUdruzenjaService;
import slobodan.siuvs2.service.SpecijalnostService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.KartonUdruzenjaId;
import slobodan.siuvs2.valueObject.SpecijalnostId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class KartonUdruzenjaController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private KartonUdruzenjaService kartonUdruzenjaService;
    @Autowired
    private SpecijalnostService specijalnostiService;
    @Autowired
    private ClanoviUdruzenjaService clanoviUdruzenjaService;
    @Autowired
    private CiljeviUdruzenjaService ciljeviUdruzenjaService;

    

    @GetMapping(value = "/client/kartonUdruzenja")
    public String adminkartonUdruzenja(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        List<KartonUdruzenja> listaKartona = kartonUdruzenjaService.findAllByClientOrderByPunnazivAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "client/kartonUdruzenja/kartonUdruzenjaLista";
    }

    @GetMapping(value = "/client/kartonUdruzenja/{kartonId}")
    public String adminkartonUdruzenjaJedan(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "client/kartonUdruzenja/kartonUdruzenja";
    }

   

    @GetMapping(value = "/client/kartonUdruzenja/{kartonId}/izmeniKarton")
    public String adminkartonUdruzenjaEdit(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        

        return "client/kartonUdruzenja/editKartonUdruzenja";
    }

    @PostMapping(value = "/client/kartonUdruzenja/{kartonId}/izmeniKarton")
    public String adminkartonUdruzenjaEditSave(                                                                 
            
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "maticnibroj", defaultValue = "0") Integer maticnibroj,
            @RequestParam(value = "pib", defaultValue = "0") Integer pib,
            @RequestParam(value = "punnaziv", defaultValue = "/") String punnaziv,
            @RequestParam(value = "skraceninaziv", defaultValue = "/") String skraceninaziv,
            @RequestParam(value = "brojtekucegracuna", defaultValue = "/") String brojtekucegracuna,
            @RequestParam(value = "posta_ime_pb", defaultValue = "/") String posta_ime_pb,
            @RequestParam(value = "adresa", defaultValue = "/") String adresa,
            @RequestParam(value = "geosirina", defaultValue = "/") String geosirina,
            @RequestParam(value = "geoduzina", defaultValue = "/") String geoduzina,
            @RequestParam(value = "tel", defaultValue = "/") String tel,
            @RequestParam(value = "fax", defaultValue = "/") String fax,
            @RequestParam(value = "email", defaultValue = "/") String email,
            @RequestParam(value = "website", defaultValue = "/") String website,
            @RequestParam(value = "rukovodilac_ime", defaultValue = "/") String rukovodilac_ime,
            @RequestParam(value = "rukovodilac_tel", defaultValue = "/") String rukovodilac_tel,
            @RequestParam(value = "rukovodilac_mob", defaultValue = "/") String rukovodilac_mob,
            
        
            @RequestParam(value = "nivo_odredjivanja", defaultValue = "/") String nivo_odredjivanja,
        
            @RequestParam(value = "kontakt_ime", defaultValue = "/") String kontakt_ime,
            @RequestParam(value = "kontakt_adresa", defaultValue = "/") String kontakt_adresa,
            @RequestParam(value = "kontakt_telposao", defaultValue = "/") String kontakt_telposao,
            @RequestParam(value = "kontakt_telstan", defaultValue = "/") String kontakt_telstan,
            @RequestParam(value = "kontakt_mob", defaultValue = "/") String kontakt_mob,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        karton.setMaticnibroj(maticnibroj);
        karton.setPib(pib);
        karton.setPunnaziv(punnaziv);
        karton.setSkraceninaziv(skraceninaziv);
        karton.setBrojtekucegracuna(brojtekucegracuna);
        karton.setPosta_ime_pb(posta_ime_pb);
        karton.setAdresa(adresa);
        karton.setGeosirina(geosirina);
        karton.setGeoduzina(geoduzina);
        karton.setTel(tel);
        karton.setFax(fax);
        karton.setEmail(email);
        karton.setWebsite(website);
        karton.setRukovodilac_ime(rukovodilac_ime);
        karton.setRukovodilac_mob(rukovodilac_mob);
        karton.setRukovodilac_tel(rukovodilac_tel);
       
       
        karton.setNivo_odredjivanja(nivo_odredjivanja);

        karton.setKontakt_ime(kontakt_ime);
        karton.setKontakt_adresa(kontakt_adresa);
        karton.setKontakt_telposao(kontakt_telposao);
        karton.setKontakt_telstan(kontakt_telstan);
        karton.setKontakt_mob(kontakt_mob);

        try {
            kartonUdruzenjaService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
            return "redirect:/client/kartonUdruzenja";
        } else {
             if (action.equals("saveMoreClanova")) {    
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati članove!");
            return "redirect:/client/kartonUdruzenja/" + karton.getId() + "/dodajClana";
                  }
                  else {    
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati ciljeve!");
            return "redirect:/client/kartonUdruzenja/" + karton.getId() + "/dodajCilj";
                  }
        }

    }

    @GetMapping(value = "/client/kartonUdruzenja/newKarton")
    public String adminkartonUdruzenjaNew(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);

        

        return "client/kartonUdruzenja/newKartonUdruzenja";
    }

    @PostMapping(value = "/client/kartonUdruzenja/newKarton")
    public String adminkartonUdruzenjaNewSave(
            
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "maticnibroj", defaultValue = "0") Integer maticnibroj,
            @RequestParam(value = "pib", defaultValue = "0") Integer pib,
            @RequestParam(value = "punnaziv", defaultValue = "/") String punnaziv,
            @RequestParam(value = "skraceninaziv", defaultValue = "/") String skraceninaziv,
            @RequestParam(value = "brojtekucegracuna", defaultValue = "/") String brojtekucegracuna,
            @RequestParam(value = "posta_ime_pb", defaultValue = "/") String posta_ime_pb,
            @RequestParam(value = "adresa", defaultValue = "/") String adresa,
            @RequestParam(value = "geosirina", defaultValue = "/") String geosirina,
            @RequestParam(value = "geoduzina", defaultValue = "/") String geoduzina,
            @RequestParam(value = "tel", defaultValue = "/") String tel,
            @RequestParam(value = "fax", defaultValue = "/") String fax,
            @RequestParam(value = "email", defaultValue = "/") String email,
            @RequestParam(value = "website", defaultValue = "/") String website,
            @RequestParam(value = "rukovodilac_ime", defaultValue = "/") String rukovodilac_ime,
            @RequestParam(value = "rukovodilac_tel", defaultValue = "/") String rukovodilac_tel,
            @RequestParam(value = "rukovodilac_mob", defaultValue = "/") String rukovodilac_mob,
          
            
            @RequestParam(value = "nivo_odredjivanja", defaultValue = "/") String nivo_odredjivanja,
 
            @RequestParam(value = "kontakt_ime", defaultValue = "/") String kontakt_ime,
            @RequestParam(value = "kontakt_adresa", defaultValue = "/") String kontakt_adresa,
            @RequestParam(value = "kontakt_telposao", defaultValue = "/") String kontakt_telposao,
            @RequestParam(value = "kontakt_telstan", defaultValue = "/") String kontakt_telstan,
            @RequestParam(value = "kontakt_mob", defaultValue = "/") String kontakt_mob,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        KartonUdruzenja karton = new KartonUdruzenja();
        karton.setClient(client);

        karton.setMaticnibroj(maticnibroj);
        karton.setPib(pib);
        karton.setPunnaziv(punnaziv);
        karton.setSkraceninaziv(skraceninaziv);
        karton.setBrojtekucegracuna(brojtekucegracuna);
        karton.setPosta_ime_pb(posta_ime_pb);
        karton.setAdresa(adresa);
        karton.setGeosirina(geosirina);
        karton.setGeoduzina(geoduzina);
        karton.setTel(tel);
        karton.setFax(fax);
        karton.setEmail(email);
        karton.setWebsite(website);
        karton.setRukovodilac_ime(rukovodilac_ime);
        karton.setRukovodilac_mob(rukovodilac_mob);
        karton.setRukovodilac_tel(rukovodilac_tel);
     
       
        karton.setNivo_odredjivanja(nivo_odredjivanja);
   
        karton.setKontakt_ime(kontakt_ime);
        karton.setKontakt_adresa(kontakt_adresa);
        karton.setKontakt_telposao(kontakt_telposao);
        karton.setKontakt_telstan(kontakt_telstan);
        karton.setKontakt_mob(kontakt_mob);

        try {
            kartonUdruzenjaService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
            return "redirect:/client/kartonUdruzenja";
        } else {
    
                  if (action.equals("saveMoreClanova")) {    
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati članove!");
            return "redirect:/client/kartonUdruzenja/" + karton.getId() + "/dodajClana";
                  }
                  else {    
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati ciljeve!");
            return "redirect:/client/kartonUdruzenja/" + karton.getId() + "/dodajCilj";
                  }
        }

    }
 @GetMapping(value = "/client/kartonUdruzenja/{kartonId}/dodajClana")
    public String adminkartonUdruzenjadodajClana(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        model.addAttribute("specijalnosti", specijalnostiService.findAllByOrderByNazivAsc());

        return "client/kartonUdruzenja/newClan";
    }

    @PostMapping(value = "/client/kartonUdruzenja/{kartonId}/dodajClana")
    public String adminkartonUdruzenjadodajClanaSave(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "specijalnost", required = true) SpecijalnostId specijalnostId,
            @RequestParam(value = "brojIzvrsilaca", required = true) Integer brojIzvrsilaca,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        ClanoviUdruzenja novClan = new ClanoviUdruzenja();
        Specijalnost specijalnost = specijalnostiService.findOne(specijalnostId);
        ClanoviUdruzenja clan = clanoviUdruzenjaService.findFirstBySpecijalnostAndKartonudruzenja(specijalnost, karton) ;
        if (clan != null)//provera da li vec postoji ako postoji menjamo samo broj eventualno
        {
            novClan = clan;
        }

        novClan.setBroj(brojIzvrsilaca);

        novClan.setSpecijalnost(specijalnost);
        novClan.setKartonUdruzenja(karton);
        try {
            clanoviUdruzenjaService.save(novClan);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov član nije uspešno sačuvan!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Nov član je uspešno sačuvan!");
            return "redirect:/client/kartonUdruzenja/" + kartonId;
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Nov član je uspešno sačuvan!");
            return "redirect:/client/kartonUdruzenja/" + kartonId + "/dodajClana";
        }
    }
    
    

    @GetMapping(value = "/client/kartonUdruzenja/{kartonId}/dodajSpecijalnost")
    public String adminDodajSpecijalnost(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);

        return "client/kartonUdruzenja/newSpecijalnost";
    }

    @PostMapping(value = "/client/kartonUdruzenja/{kartonId}/dodajSpecijalnost")
    public String adminDodajSpecijalnostSave(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);
         Specijalnost newSpecijalnost =  new Specijalnost();
      
        newSpecijalnost.setNaziv(naziv);
  
        try {
            specijalnostiService.save(newSpecijalnost);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nova specijalnost nije uspešno sačuvano!");
            return "redirect:/client/kartonUdruzenja/" + kartonId.getValue() + "/dodajClana";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Nova specijalnost je uspešno sačuvano!");
        return "redirect:/client/kartonUdruzenja/" + kartonId.getValue() + "/dodajClana";
    }

    
    @GetMapping(value = "/client/kartonUdruzenja/{kartonId}/dodajCilj")
    public String adminDodajCilj(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);

        return "client/kartonUdruzenja/newCilj";
    }
/*
    @GetMapping(value = "/client/kartonUdruzenja/dodajCilj")
    public String adminDodajCiljIzNovogKartona(
            
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
//nema karton id
        KartonUdruzenjaId kartonId = new KartonUdruzenjaId(-1);
        model.addAttribute("kartonId", -1);
        return "client/kartonUdruzenja/newCilj";
    }*/

    @PostMapping(value = "/client/kartonUdruzenja/{kartonId}/dodajCilj")
    public String adminDodajCiljSave(
            
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();        Client client = currentUser.getClient();
        model.addAttribute("client", client);
KartonUdruzenja kartonUdruzenja=kartonUdruzenjaService.findOne(kartonId);
        CiljeviUdruzenja newCiljeviUdruzenja = new CiljeviUdruzenja();
        newCiljeviUdruzenja.setNaziv(naziv);
        newCiljeviUdruzenja.setKartonUdruzenja(kartonUdruzenja);
      
        try {
            ciljeviUdruzenjaService.save(newCiljeviUdruzenja);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov cilj nije uspešno sačuvan!");
            return "redirect:/client/kartonUdruzenja/"+kartonId.getValue();
        }
        redirectAttributes.addFlashAttribute("successMessage", "Nov cilj je uspešno sačuvan!");
        /*if (kartonId.getValue() == -1) {
            return "redirect:/client/kartonUdruzenja/newKarton";
        } else {*/
            return "redirect:/client/kartonUdruzenja/" + kartonId.getValue() ;
       // }

    }
}
