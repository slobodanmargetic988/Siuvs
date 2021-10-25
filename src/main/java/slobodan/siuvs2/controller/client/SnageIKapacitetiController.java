/*
 * 
 */
package slobodan.siuvs2.controller.client;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
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
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.model.ZanimanjaPodvrsta;
import slobodan.siuvs2.service.DelatnostService;
import slobodan.siuvs2.service.KadroviService;
import slobodan.siuvs2.service.KartonSubjektiService;
import slobodan.siuvs2.service.ZanimanjaPodvrstaService;
import slobodan.siuvs2.service.ZanimanjaService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DelatnostId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.ZanimanjaId;
import slobodan.siuvs2.valueObject.ZanimanjaPodvrstaId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class SnageIKapacitetiController {


    @Autowired
    private KartonSubjektiService kartonSubjektiService;
    @Autowired
    private ZanimanjaService zanimanjaService;
     @Autowired
    private ZanimanjaPodvrstaService zanimanjaPodvrstaService;
    
    @Autowired
    private KadroviService kadroviService;
    @Autowired
    private DelatnostService delatnostService;

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

        return "client/kartonSubjekta/listaKartona";
    }

    @GetMapping(value = "/client/kartonSubjekti")
    public String clientkartonSubjekti(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        List<KartonSubjekti> listaKartona = kartonSubjektiService.findAllByClientOrderByPunnazivAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "client/kartonSubjekta/kartonSubjektiLista";
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
        KartonSubjekti karton = kartonSubjektiService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "client/kartonSubjekta/kartonSubjekta";
    }

    @GetMapping(value = "/client/kartonSubjekti/{kartonId}/izmeniKarton")
    public String clientkartonSubjektiEdit(
            @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonSubjekti karton = kartonSubjektiService.findOne(kartonId);
        model.addAttribute("karton", karton);
        model.addAttribute("listaDelatnosti", delatnostService.findAllByOrderByNazivAsc());

        return "client/kartonSubjekta/editKartonSubjekta";
    }

    @PostMapping(value = "/client/kartonSubjekti/{kartonId}/izmeniKarton")
    public String clientkartonSubjektiEditSave(
            @PathVariable final KartonSubjektiId kartonId,
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
            @RequestParam(value = "delatnost", defaultValue = "1") DelatnostId delatnost,
            @RequestParam(value = "oblik_organizovanja", defaultValue = "/") String oblik_organizovanja,
            @RequestParam(value = "nivo_odredjivanja", defaultValue = "/") String nivo_odredjivanja,
   
            @RequestParam(value = "kontakt_ime", defaultValue = "/") String kontakt_ime,
            @RequestParam(value = "kontakt_adresa", defaultValue = "/") String kontakt_adresa,
            @RequestParam(value = "kontakt_telposao", defaultValue = "/") String kontakt_telposao,
            @RequestParam(value = "kontakt_telstan", defaultValue = "/") String kontakt_telstan,
            @RequestParam(value = "kontakt_mob", defaultValue = "/") String kontakt_mob,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonSubjekti karton = kartonSubjektiService.findOne(kartonId);
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
      
        karton.setKontakt_ime(kontakt_ime);
        karton.setKontakt_adresa(kontakt_adresa);
        karton.setKontakt_telposao(kontakt_telposao);
        karton.setKontakt_telstan(kontakt_telstan);
        karton.setKontakt_mob(kontakt_mob);

        try {
            kartonSubjektiService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
            return "redirect:/client/kartonSubjekti";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen možete dodati kadrove!");
            return "redirect:/clients/kartonSubjekti/" + karton.getId() + "/dodajKadar";
        }

    }

    @GetMapping(value = "/client/kartonSubjekti/newKarton")
    public String clientkartonSubjektiNew(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        model.addAttribute("listaDelatnosti", delatnostService.findAllByOrderByNazivAsc());

        return "client/kartonSubjekta/newKartonSubjekta";
    }

    @PostMapping(value = "/client/kartonSubjekti/newKarton")
    public String clientkartonSubjektiNewSave(
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
            @RequestParam(value = "delatnost", defaultValue = "1") DelatnostId delatnost,
            @RequestParam(value = "oblik_organizovanja", defaultValue = "/") String oblik_organizovanja,
            @RequestParam(value = "nivo_odredjivanja", defaultValue = "/") String nivo_odredjivanja,
   
            @RequestParam(value = "kontakt_ime", defaultValue = "/") String kontakt_ime,
            @RequestParam(value = "kontakt_adresa", defaultValue = "/") String kontakt_adresa,
            @RequestParam(value = "kontakt_telposao", defaultValue = "/") String kontakt_telposao,
            @RequestParam(value = "kontakt_telstan", defaultValue = "/") String kontakt_telstan,
            @RequestParam(value = "kontakt_mob", defaultValue = "/") String kontakt_mob,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonSubjekti karton = new KartonSubjekti();
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
      
        karton.setKontakt_ime(kontakt_ime);
        karton.setKontakt_adresa(kontakt_adresa);
        karton.setKontakt_telposao(kontakt_telposao);
        karton.setKontakt_telstan(kontakt_telstan);
        karton.setKontakt_mob(kontakt_mob);

        try {
            kartonSubjektiService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
            return "redirect:/client/kartonSubjekti";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati kadrove!");
            return "redirect:/client/kartonSubjekti/" + karton.getId() + "/dodajKadar";
        }

    }

    @GetMapping(value = "/client/kartonSubjekti/{kartonId}/dodajKadar")
    public String clientkartonSubjektidodajKadar(
            @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonSubjekti karton = kartonSubjektiService.findOne(kartonId);
        model.addAttribute("karton", karton);
        model.addAttribute("zanimanja", zanimanjaService.findAllByOrderByNazivAsc());

 model.addAttribute("zanimanjaPodvrste", zanimanjaPodvrstaService.findAllByOrderByNazivAsc());
        return "client/kartonSubjekta/newKadar";
    }

    @PostMapping(value = "/client/kartonSubjekti/{kartonId}/dodajKadar")
    public String adminkartonSubjektidodajKadarSave(
          
            @PathVariable final KartonSubjektiId kartonId,
            @RequestParam(value = "action", required = true) String action,
             @RequestParam(value = "nazivStruke", required = true) ZanimanjaId nazivStrukeId,
            @RequestParam(value = "nazivPodStruke", required = true) ZanimanjaPodvrstaId nazivPodStrukeId,
            @RequestParam(value = "brojIzvrsilaca", required = true) Integer brojIzvrsilaca,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonSubjekti karton = kartonSubjektiService.findOne(kartonId);
        Kadrovi novKadar = new Kadrovi();
        Zanimanja zanimanje = zanimanjaService.findOne(nazivStrukeId);
            ZanimanjaPodvrsta zanimanjePodvrsta=zanimanjaPodvrstaService.findOne(nazivPodStrukeId);
        Kadrovi checkKadar = kadroviService.findFirstByZanimanjeAndKartonsubjekti(zanimanjePodvrsta, karton);
        if (checkKadar != null)//provera da li vec postoji ako postoji menjamo samo broj eventualno
        {
            novKadar = checkKadar;
        }

        novKadar.setBroj(brojIzvrsilaca);

        novKadar.setZanimanjepodvrsta(zanimanjePodvrsta);
        novKadar.setKartonsubjekti(karton);
        try {
            kadroviService.save(novKadar);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov kadar nije uspešno sačuvan!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Nov kadar je uspešno sačuvan!");
            return "redirect:/client/kartonSubjekti/" + kartonId;
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Nov kadar je uspešno sačuvan!");
            return "redirect:/client/kartonSubjekti/" + kartonId + "/dodajKadar";
        }
    }
    
    @GetMapping(value = "/client/kartonSubjekti/{kartonId}/dodajDelatnost")
    public String clientDodajDelatnost(
         
            @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);

        return "client/kartonSubjekta/newDelatnost";
    }

    @GetMapping(value = "/client/kartonSubjekti/dodajDelatnost")
    public String clientDodajDelatnostIzNovogKartona(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
//nema karton id
        KartonSubjektiId kartonId = new KartonSubjektiId(-1);
        model.addAttribute("kartonId", -1);
        return "client/kartonSubjekta/newDelatnost";
    }

    @PostMapping(value = "/client/kartonSubjekti/{kartonId}/dodajDelatnost")
    public String clientDodajDelatnostSave(
            @PathVariable final KartonSubjektiId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
            @RequestParam(value = "sifra", defaultValue = "/") String sifra,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        Delatnost newDelatnost = new Delatnost();
        newDelatnost.setNaziv(naziv);
        newDelatnost.setSifra(sifra);
        try {
            delatnostService.save(newDelatnost);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nova delatnost nije uspešno sačuvana!");
            return "redirect:/client/kartonSubjekti/";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Nova delatnost je uspešno sačuvana!");
        if (kartonId.getValue() == -1) {
            return "redirect:/client/kartonSubjekti/newKarton";
        } else {
            return "redirect:/client/kartonSubjekti/" + kartonId.getValue() + "/izmeniKarton";
        }

    }

    @GetMapping(value = "/client/kartonSubjekti/{kartonId}/dodajZanimanje")
    public String clientDodajZanimanje(
            @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);

        return "client/kartonSubjekta/newZanimanje";
    }

    @PostMapping(value = "/client/kartonSubjekti/{kartonId}/dodajZanimanje")
    public String clientDodajZanimanjeSave(
            @PathVariable final KartonSubjektiId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
       
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);
        Zanimanja newZanimanje = new Zanimanja();
        newZanimanje.setNaziv(naziv);
 
        try {
            zanimanjaService.save(newZanimanje);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Novo zanimanje nije uspešno sačuvano!");
            return "redirect:/client/kartonSubjekti/" + kartonId.getValue() + "/dodajKadar";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Novo delatnost je uspešno sačuvano!");
        return "redirect:/client/kartonSubjekti/" + kartonId.getValue() + "/dodajKadar";
    }
    
    
    
       @GetMapping(value = "/client/kartonSubjekti/{kartonId}/dodajPodZanimanje")
    public String adminDodajPodZanimanje(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonSubjektiId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);
     model.addAttribute("zanimanja", zanimanjaService.findAllByOrderByNazivAsc());
        

        return "admin/clients/kartonSubjekta/newPodZanimanje";
    }

    @PostMapping(value = "/client/kartonSubjekti/{kartonId}/dodajPodZanimanje")
    public String adminDodajPodZanimanjeSave(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonSubjektiId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
         @RequestParam(value = "nazivStruke", required = true) ZanimanjaId nazivStrukeId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);
        ZanimanjaPodvrsta newZanimanje = new ZanimanjaPodvrsta();
        newZanimanje.setNaziv(naziv);
        newZanimanje.setZanimanje(zanimanjaService.findOne(nazivStrukeId));
  
        try {
           zanimanjaPodvrstaService.save(newZanimanje);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Novo pod-zanimanje nije uspešno sačuvano!");
            return "redirect:/admin/clients/" + clientId + "/kartonSubjekti/" + kartonId.getValue() + "/dodajKadar";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Novo pod-zanimanje je uspešno sačuvano!");
        return "redirect:/admin/clients/" + clientId + "/kartonSubjekti/" + kartonId.getValue() + "/dodajKadar";
    }

}
