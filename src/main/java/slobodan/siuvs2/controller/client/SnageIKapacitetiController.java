/*
 * 
 */
package slobodan.siuvs2.controller.client;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
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
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DelatnostService;
import slobodan.siuvs2.service.KadroviService;
import slobodan.siuvs2.service.KartonSubjektiService;
import slobodan.siuvs2.service.ZanimanjaService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DelatnostId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.ZanimanjaId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class SnageIKapacitetiController {
    
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


    
    @GetMapping(value = "/admin/clients/{clientId}/Kartoni")
    public String adminZbirniObrasci(
              @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        
        return "admin/clients/EK/listaKartona";
    }
    
    
    
    
     @Autowired
    private KartonSubjektiService kartonSubjektiService;
    
        @GetMapping(value = "/admin/clients/{clientId}/kartonSubjekti")
    public String adminkartonSubjekti(
              @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<KartonSubjekti> listaKartona  = kartonSubjektiService.findAllByClientOrderByPunnazivAsc(client);
         model.addAttribute("listaKartona", listaKartona);
        
        
        return "admin/clients/EK/kartonSubjektiLista";
    }
    
         @GetMapping(value = "/admin/clients/{clientId}/kartonSubjekti/{kartonId}")
    public String adminkartonSubjektiJedan(
              @PathVariable final ClientId clientId,
                @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton  = kartonSubjektiService.findOne(kartonId);
         model.addAttribute("karton", karton);
        return "admin/clients/EK/kartonSubjekta";
    }
    
    
    
    
     @Autowired
    private ZanimanjaService zanimanjaService;
             
            @GetMapping(value = "/admin/clients/{clientId}/kartonSubjekti/{kartonId}/dodajKadar")
    public String adminkartonSubjektidodajKadar(
              @PathVariable final ClientId clientId,
                @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton  = kartonSubjektiService.findOne(kartonId);
         model.addAttribute("karton", karton);
          model.addAttribute("zanimanja", zanimanjaService.findAllByOrderByNazivAsc());
         
         
        return "admin/clients/EK/newKadar";
    }
    
    
       
     @Autowired
    private KadroviService kadroviService;
       @PostMapping(value = "/admin/clients/{clientId}/kartonSubjekti/{kartonId}/dodajKadar")
    public String adminkartonSubjektidodajKadarSave(
              @PathVariable final ClientId clientId,
                @PathVariable final KartonSubjektiId kartonId,
                @RequestParam(value="action", required=true) String action,
                @RequestParam(value="nazivStruke", required=true) ZanimanjaId nazivStrukeId,
                @RequestParam(value="brojIzvrsilaca", required=true) Integer brojIzvrsilaca,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton  = kartonSubjektiService.findOne(kartonId);
        Kadrovi novKadar= new Kadrovi();
        Zanimanja zanimanje=zanimanjaService.findOne(nazivStrukeId);
       Kadrovi checkKadar=kadroviService.findFirstByZanimanjeAndKartonsubjekti(zanimanje, karton);
       if(checkKadar!=null)//provera da li vec postoji ako postoji menjamo samo broj eventualno
       {novKadar=checkKadar;}
       
        novKadar.setBroj(brojIzvrsilaca);
        
        novKadar.setZanimanje(zanimanje);
        novKadar.setKartonsubjekti(karton);
      try{  kadroviService.save(novKadar);
      }catch(Exception e){
         redirectAttributes.addFlashAttribute("errorMessage", "Nov kadar nije uspešno sačuvan!");
      }
        
        if (action.equals("save")) {
             redirectAttributes.addFlashAttribute("successMessage", "Nov kadar je uspešno sačuvan!");
        return "redirect:/admin/clients/"+clientId+"/kartonSubjekti/"+kartonId;
    }
        else{
             redirectAttributes.addFlashAttribute("successMessage", "Nov kadar je uspešno sačuvan!");
        return "redirect:/admin/clients/"+clientId+"/kartonSubjekti/"+kartonId+"/dodajKadar";}
        
        
    }
    @Autowired
    private DelatnostService delatnostService;
    
           @GetMapping(value = "/admin/clients/{clientId}/kartonSubjekti/{kartonId}/izmeniKarton")
    public String adminkartonSubjektiEdit(
              @PathVariable final ClientId clientId,
                @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton  = kartonSubjektiService.findOne(kartonId);
         model.addAttribute("karton", karton);
          model.addAttribute("listaDelatnosti", delatnostService.findAllByOrderByNazivAsc());
         
        return "admin/clients/EK/editKartonSubjekta";
    }
    
      @PostMapping(value = "/admin/clients/{clientId}/kartonSubjekti/{kartonId}/izmeniKarton")
    public String adminkartonSubjektiEditSave(
              @PathVariable final ClientId clientId,
                @PathVariable final KartonSubjektiId kartonId,
                 @RequestParam(value="action", required=true) String action,
               
                 @RequestParam(value="maticnibroj",  defaultValue = "0") Integer maticnibroj,
                 @RequestParam(value="pib", defaultValue = "0") Integer pib,
                 @RequestParam(value="punnaziv",  defaultValue = "/") String punnaziv,
                 @RequestParam(value="skraceninaziv", defaultValue = "/") String skraceninaziv,
                 @RequestParam(value="brojtekucegracuna", defaultValue = "/") String brojtekucegracuna,
                 @RequestParam(value="posta_ime_pb", defaultValue = "/") String posta_ime_pb,
                 @RequestParam(value="adresa", defaultValue = "/") String adresa,
                 @RequestParam(value="geosirina", defaultValue = "/") String geosirina,
                 @RequestParam(value="geoduzina", defaultValue = "/") String geoduzina,
                 @RequestParam(value="tel", defaultValue = "/") String tel,
                 @RequestParam(value="fax", defaultValue = "/") String fax,
                 @RequestParam(value="email", defaultValue = "/") String email,
                 @RequestParam(value="website", defaultValue = "/") String website,
                 @RequestParam(value="rukovodilac_ime", defaultValue = "/") String rukovodilac_ime,
                 @RequestParam(value="rukovodilac_tel", defaultValue = "/") String rukovodilac_tel,
                 @RequestParam(value="rukovodilac_mob", defaultValue = "/") String rukovodilac_mob,
                 @RequestParam(value="delatnost", defaultValue = "1") DelatnostId delatnost,
                 @RequestParam(value="oblik_organizovanja", defaultValue = "/") String oblik_organizovanja,
                 @RequestParam(value="nivo_odredjivanja", defaultValue = "/") String nivo_odredjivanja,
                 @RequestParam(value="nadleznaSVS", defaultValue = "/") String nadleznaSVS,
                 @RequestParam(value="kontakt_ime", defaultValue = "/") String kontakt_ime,
                 @RequestParam(value="kontakt_adresa", defaultValue = "/") String kontakt_adresa,
                 @RequestParam(value="kontakt_telposao", defaultValue = "/") String kontakt_telposao,
                 @RequestParam(value="kontakt_telstan", defaultValue = "/") String kontakt_telstan,
                 @RequestParam(value="kontakt_mob", defaultValue = "/") String kontakt_mob,
                
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton  = kartonSubjektiService.findOne(kartonId);
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
        karton.setDelatnost(delatnostService.findOne(delatnost));
        karton.setOblik_organizovanja(oblik_organizovanja);
        karton.setNivo_odredjivanja(nivo_odredjivanja);
        karton.setNadleznaSVS(nadleznaSVS);
        karton.setKontakt_ime(kontakt_ime);
        karton.setKontakt_adresa(kontakt_adresa);
        karton.setKontakt_telposao(kontakt_telposao);
        karton.setKontakt_telstan(kontakt_telstan);
        karton.setKontakt_mob(kontakt_mob);
          
        try{
            kartonSubjektiService.save(karton);
            
         }catch(Exception e){
         redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
      }
         
       
          if (action.equals("save")) {
             redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
        return "redirect:/admin/clients/"+clientId+"/kartonSubjekti";
    }
        else{
             redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen možete dodati kadrove!");
        return "redirect:/admin/clients/"+clientId+"/kartonSubjekti/"+karton.getId()+"/dodajKadar";}
        
    }
    
        @GetMapping(value = "/admin/clients/{clientId}/kartonSubjekti/newKarton")
    public String adminkartonSubjektiNew(
              @PathVariable final ClientId clientId,           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
      
          model.addAttribute("listaDelatnosti", delatnostService.findAllByOrderByNazivAsc());
         
        return "admin/clients/EK/newKartonSubjekta";
    }
    
      @PostMapping(value = "/admin/clients/{clientId}/kartonSubjekti/newKarton")
    public String adminkartonSubjektiNewSave(
              @PathVariable final ClientId clientId,           
                 @RequestParam(value="action", required=true) String action,
                 
                 @RequestParam(value="maticnibroj",  defaultValue = "0") Integer maticnibroj,
                 @RequestParam(value="pib", defaultValue = "0") Integer pib,
                 @RequestParam(value="punnaziv",  defaultValue = "/") String punnaziv,
                 @RequestParam(value="skraceninaziv", defaultValue = "/") String skraceninaziv,
                 @RequestParam(value="brojtekucegracuna", defaultValue = "/") String brojtekucegracuna,
                 @RequestParam(value="posta_ime_pb", defaultValue = "/") String posta_ime_pb,
                 @RequestParam(value="adresa", defaultValue = "/") String adresa,
                 @RequestParam(value="geosirina", defaultValue = "/") String geosirina,
                 @RequestParam(value="geoduzina", defaultValue = "/") String geoduzina,
                 @RequestParam(value="tel", defaultValue = "/") String tel,
                 @RequestParam(value="fax", defaultValue = "/") String fax,
                 @RequestParam(value="email", defaultValue = "/") String email,
                 @RequestParam(value="website", defaultValue = "/") String website,
                 @RequestParam(value="rukovodilac_ime", defaultValue = "/") String rukovodilac_ime,
                 @RequestParam(value="rukovodilac_tel", defaultValue = "/") String rukovodilac_tel,
                 @RequestParam(value="rukovodilac_mob", defaultValue = "/") String rukovodilac_mob,
                 @RequestParam(value="delatnost", defaultValue = "1") DelatnostId delatnost,
                 @RequestParam(value="oblik_organizovanja", defaultValue = "/") String oblik_organizovanja,
                 @RequestParam(value="nivo_odredjivanja", defaultValue = "/") String nivo_odredjivanja,
                 @RequestParam(value="nadleznaSVS", defaultValue = "/") String nadleznaSVS,
                 @RequestParam(value="kontakt_ime", defaultValue = "/") String kontakt_ime,
                 @RequestParam(value="kontakt_adresa", defaultValue = "/") String kontakt_adresa,
                 @RequestParam(value="kontakt_telposao", defaultValue = "/") String kontakt_telposao,
                 @RequestParam(value="kontakt_telstan", defaultValue = "/") String kontakt_telstan,
                 @RequestParam(value="kontakt_mob", defaultValue = "/") String kontakt_mob,
                
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonSubjekti karton  = new KartonSubjekti();
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
        karton.setDelatnost(delatnostService.findOne(delatnost));
        karton.setOblik_organizovanja(oblik_organizovanja);
        karton.setNivo_odredjivanja(nivo_odredjivanja);
        karton.setNadleznaSVS(nadleznaSVS);
        karton.setKontakt_ime(kontakt_ime);
        karton.setKontakt_adresa(kontakt_adresa);
        karton.setKontakt_telposao(kontakt_telposao);
        karton.setKontakt_telstan(kontakt_telstan);
        karton.setKontakt_mob(kontakt_mob);
          
        try{
            kartonSubjektiService.save(karton);
            
         }catch(Exception e){
         redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
      }
         
       
          if (action.equals("save")) {
             redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
        return "redirect:/admin/clients/"+clientId+"/kartonSubjekti";
    }
        else{
             redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati kadrove!");
        return "redirect:/admin/clients/"+clientId+"/kartonSubjekti/"+karton.getId()+"/dodajKadar";}
        
    }
    
    
    
    
        @GetMapping(value = "/admin/clients/{clientId}/kartonSubjekti/{kartonId}/dodajDelatnost")
    public String adminDodajDelatnost(
              @PathVariable final ClientId clientId,
                @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
         model.addAttribute("kartonId", kartonId);
        
         
         
        return "admin/clients/EK/newDelatnost";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        /*  poseban cilj*/
    @GetMapping(value = "/client/Kartoni")
    public String clientZbirniObrasci(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        model.addAttribute("client", client);

      
        return "admin/clients/EK/listaKartona";
    }
    
    
    
    
    
         @GetMapping(value = "/client/kartonSubjekti/{kartonId}")
    public String clientkartonSubjektiJedan(
           
                @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        model.addAttribute("client", client);
        KartonSubjekti karton  = kartonSubjektiService.findOne(kartonId);
         model.addAttribute("karton", karton);
        return "admin/clients/EK/kartonSubjekta";
    }
    
    
}
