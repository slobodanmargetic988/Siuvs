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
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.Kadrovi;
import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.Zanimanja;
import slobodan.siuvs2.model.ZanimanjaPodvrsta;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DelatnostService;
import slobodan.siuvs2.service.DetaljiMTSService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.KadroviService;
import slobodan.siuvs2.service.KartonSubjektiService;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.service.ZanimanjaPodvrstaService;
import slobodan.siuvs2.service.ZanimanjaService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DelatnostId;
import slobodan.siuvs2.valueObject.KartonSubjektiId;
import slobodan.siuvs2.valueObject.TableColumnId;
import slobodan.siuvs2.valueObject.TableDefinitionId;
import slobodan.siuvs2.valueObject.ZanimanjaId;
import slobodan.siuvs2.valueObject.ZanimanjaPodvrstaId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class SnageIKapacitetiController {

    @Autowired
    private ClientService clientService;

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

    @Autowired
    private DynamicTableService dynamicTableService;
    @Autowired
    private TableColumnService tableColumnService;
  @Autowired
    private DetaljiMTSService detaljiMTSService;

    /*  poseban cilj*/
    @GetMapping(value = "/client/Kartoni")
    public String clientListakartonaKapaciteta(
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
  
 @GetMapping(value = "/client/zbirniObrasci")
    public String adminZbirniObrasciJedinstveniPregled(
           
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

/////////////////////////////////////////////////////////////////SEGMENT ZA SIRENE
        TableDefinitionId tableDefinitionIdSirene = new TableDefinitionId(6002);
        DynamicTable tabelaSirene = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdSirene, client); //sirene 6002
        TableColumn vrstaSirene = new TableColumn();
        Integer elektronskeSirene = 0;
        Integer pneumatskeSirene = 0;
        Integer elektricneSirene = 0;
        for (TableColumn kolona : tabelaSirene.getTableDefinition().getColumns(client)) {//trazimo kolonu koja nas zanima i cuvamo je za dalju referencu
            if (kolona.getTitle().equalsIgnoreCase("Врста сирене")) {
                vrstaSirene = kolona;
            }
        }

        for (DynamicRow red : tabelaSirene.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {   //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                if (data.getColumn().equals(vrstaSirene)) {

                    if (data.getValue().equals("600022")) {
                        elektricneSirene++;
                    }// cuvamo broj razlicitih unosa

                    if (data.getValue().equals("600021")) {
                        elektronskeSirene++;
                    }

                    if (data.getValue().equals("600023")) {
                        pneumatskeSirene++;
                    }

                };
            }

        }
        model.addAttribute("elektronskeSirene", elektronskeSirene);
        model.addAttribute("pneumatskeSirene", pneumatskeSirene);
        model.addAttribute("elektricneSirene", elektricneSirene);
/////////////////////////////////////////////////////////////////SEGMENT ZA SIRENE

  
        /////////////////////////////////////////////////////////////////SEGMENT ZA POVERENIKE CIVILNE ZASTITE
        TableDefinitionId tableDefinitionIdPoverenici = new TableDefinitionId(6003);
        DynamicTable tabelaPoverenici = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdPoverenici, client); //poverenici 6003
        TableColumn povereniciKolona1 = tableColumnService.findOne(new TableColumnId(60032));
        TableColumn povereniciKolona2 = tableColumnService.findOne(new TableColumnId(60033));
        TableColumn povereniciKolona3 = tableColumnService.findOne(new TableColumnId(60034));
        TableColumn povereniciKolona4 = tableColumnService.findOne(new TableColumnId(60035));
        TableColumn povereniciKolona5 = tableColumnService.findOne(new TableColumnId(60036));
        TableColumn povereniciKolona6 = tableColumnService.findOne(new TableColumnId(60038));
        TableColumn povereniciKolona7 = tableColumnService.findOne(new TableColumnId(60039));
        Integer povereniciBrojac1 = 0;
        Integer povereniciBrojac2 = 0;
        Integer povereniciBrojac3 = 0;
        Integer povereniciBrojac4 = 0;
        Integer povereniciBrojac5 = 0;
        Integer povereniciBrojac6 = 0;
        Integer povereniciBrojac7 = 0;
        //   Integer        povereniciBrojacSuma=0;

        for (DynamicRow red : tabelaPoverenici.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().equals(povereniciKolona1)) {
                        povereniciBrojac1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona2)) {
                        povereniciBrojac2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona3)) {
                        povereniciBrojac3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona4)) {
                        povereniciBrojac4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona5)) {
                        povereniciBrojac5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona6)) {
                        povereniciBrojac6 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona7)) {
                        povereniciBrojac7 += Integer.parseInt(data.getValue());
                    };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }
        model.addAttribute("povereniciBrojac1", povereniciBrojac1);
        model.addAttribute("povereniciBrojac2", povereniciBrojac2);
        model.addAttribute("povereniciBrojac3", povereniciBrojac3);
        model.addAttribute("povereniciBrojac4", povereniciBrojac4);
        model.addAttribute("povereniciBrojac5", povereniciBrojac5);
        model.addAttribute("povereniciBrojac6", povereniciBrojac6);
        model.addAttribute("povereniciBrojac7", povereniciBrojac7);
        model.addAttribute("povereniciBrojacSuma", povereniciBrojac1 + povereniciBrojac2 + povereniciBrojac3 + povereniciBrojac4 + povereniciBrojac5 + povereniciBrojac6);

/////////////////////////////////////////////////////////////////SEGMENT ZA POVERENIKE CIVILNE ZASTITE  

/////////////////////////////////////////////////////////////////SEGMENT ZA JEDINICE CIVILNE ZASTITE  
        TableDefinitionId tableDefinitionIdJedinice = new TableDefinitionId(6004);
        DynamicTable tabelaJedinice = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdJedinice, client); //poverenici 6003
       TableColumn vrstaJedinice = tableColumnService.findOne(new TableColumnId(60042));
        TableColumn jediniceKolona2 = tableColumnService.findOne(new TableColumnId(60044));
        TableColumn jediniceKolona3 = tableColumnService.findOne(new TableColumnId(60045));
        TableColumn jediniceKolona4 = tableColumnService.findOne(new TableColumnId(60046));
        TableColumn jediniceKolona5 = tableColumnService.findOne(new TableColumnId(60047));
        
        

        Integer jediniceBrojac1 = 0;
        Integer jediniceBrojac2 = 0;
        Integer jediniceBrojac3 = 0;
        Integer jediniceBrojac4 = 0;
        Integer jediniceBrojac5 = 0;
        Integer jediniceBrojac6 = 0;
        Integer jediniceBrojac7 = 0;
        Integer jediniceBrojac8 = 0;
        Integer jediniceBrojac9 = 0;
        Integer jediniceBrojac10 = 0;
        
                for (DynamicRow red : tabelaJedinice.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().equals(vrstaJedinice)) {

                    if (data.getValue().equals("600041")) {
                        jediniceBrojac1++;
                          for (DynamicData data2prolaz : red.getData()) {//drugi put prolazimo kroz red kad znamo koji je tip jedinice da saberemo ostale kolone
                          if (data2prolaz.getColumn().equals(jediniceKolona2)) {
                             jediniceBrojac2+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                              if (data2prolaz.getColumn().equals(jediniceKolona3)) {
                             jediniceBrojac3+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                 if (data2prolaz.getColumn().equals(jediniceKolona4)) {
                             jediniceBrojac4+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                    if (data2prolaz.getColumn().equals(jediniceKolona5)) {
                             jediniceBrojac5+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                          }
                        
                    }// cuvamo broj razlicitih unosa

                    if (data.getValue().equals("600042")) {
                        jediniceBrojac6++;
                                   for (DynamicData data2prolaz : red.getData()) {//drugi put prolazimo kroz red kad znamo koji je tip jedinice da saberemo ostale kolone
                          if (data2prolaz.getColumn().equals(jediniceKolona2)) {
                             jediniceBrojac7+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                              if (data2prolaz.getColumn().equals(jediniceKolona3)) {
                             jediniceBrojac8+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                 if (data2prolaz.getColumn().equals(jediniceKolona4)) {
                             jediniceBrojac9+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                    if (data2prolaz.getColumn().equals(jediniceKolona5)) {
                             jediniceBrojac10+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                          }
                    }
                };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }
        model.addAttribute("jediniceBrojac1", jediniceBrojac1);
        model.addAttribute("jediniceBrojac2", jediniceBrojac2);
        model.addAttribute("jediniceBrojac3", jediniceBrojac3);
        model.addAttribute("jediniceBrojac4", jediniceBrojac4);
        model.addAttribute("jediniceBrojac5", jediniceBrojac5);
        model.addAttribute("jediniceBrojac6", jediniceBrojac6);
        model.addAttribute("jediniceBrojac7", jediniceBrojac7);
        model.addAttribute("jediniceBrojac8", jediniceBrojac8);
        model.addAttribute("jediniceBrojac9", jediniceBrojac9);
        model.addAttribute("jediniceBrojac10", jediniceBrojac10);
        
/////////////////////////////////////////////////////////////////SEGMENT ZA JEDINICE CIVILNE ZASTITE  

/////////////////////////////////////////////////////////////////SEGMENT ZA SITUACIONE CENTRE
  TableDefinitionId tableDefinitionIdSCentar = new TableDefinitionId(6001);
        DynamicTable tabelaSCentar = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdSCentar, client);
        model.addAttribute("scentarRedovi", tabelaSCentar.getRows());
        
        
        TableColumn csentarKolona1 = tableColumnService.findOne(new TableColumnId(60011));
        TableColumn csentarKolona2 = tableColumnService.findOne(new TableColumnId(60012));
        TableColumn csentarKolona3 = tableColumnService.findOne(new TableColumnId(60013));
        TableColumn csentarKolona4 = tableColumnService.findOne(new TableColumnId(60014));
         TableColumn csentarKolona5 = tableColumnService.findOne(new TableColumnId(60015));
     
       
        model.addAttribute("scentar1", csentarKolona1);
        model.addAttribute("scentar2", csentarKolona2);
        model.addAttribute("scentar3", csentarKolona3);
        model.addAttribute("scentar4", csentarKolona4);
        model.addAttribute("scentar5", csentarKolona5);
/////////////////////////////////////////////////////////////////SEGMENT ZA SITUACIONE CENTRE


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MERE CIVILNE ZASTITE
     Integer evakuacija1=0;
        Integer evakuacija2=0;
        Integer evakuacija3=0;
        Integer evakuacija4=0;
        Integer evakuacija5=0;
        Integer evakuacija6=0;
       
         Integer zbirnjavanje1=0;
         Integer zbirnjavanje2=0;
         Integer zbirnjavanje3=0;
         Integer zbirnjavanje4=0;
         Integer zbirnjavanje5=0;
         Integer zbirnjavanje6=0;
         Integer zbirnjavanje7=0;
         
         Integer prvaPomoc1=0;
         Integer prvaPomoc2=0;
         Integer prvaPomoc3=0;
         Integer prvaPomoc4=0;
         Integer prvaPomoc5=0;
         Integer prvaPomoc6=0;
         Integer prvaPomoc7=0;
         Integer prvaPomoc8=0;
         Integer prvaPomoc9=0;
         Integer prvaPomoc10=0;
         Integer prvaPomoc11=0;
         Integer prvaPomoc12=0;
          Integer prvaPomoc13=0;
           Integer prvaPomoc14=0;
            Integer prvaPomoc15=0;
             Integer prvaPomoc16=0;
              Integer prvaPomoc17=0;
               Integer prvaPomoc18=0;
         
         Integer asanacija1=0;
         Integer asanacija2=0;
         Integer asanacija3=0;
         Integer asanacija4=0;
         Integer asanacija5=0;
         Integer asanacija6=0;
         Integer asanacija7=0;
         Integer asanacija8=0;
         Integer asanacija9=0;
         Integer asanacija10=0;
         Integer asanacija11=0;
         Integer asanacija12=0;
         
         

/////////////////////////////////////////////////////////////////SEGMENT ZA EVAKUACIJU
TableDefinitionId tableDefinitionIdEvakuacija = new TableDefinitionId(5001);
DynamicTable tabelaEvakuacija = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdEvakuacija, client);
        for (DynamicRow red : tabelaEvakuacija.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().getOrder()==14) {
                        evakuacija1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==15) {
                        evakuacija2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==16) {
                        evakuacija3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==17) {
                        evakuacija4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==18) {
                        evakuacija5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==19) {
                        evakuacija6 += Integer.parseInt(data.getValue());
                    };
                  
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("evakuacija1", evakuacija1);
model.addAttribute("evakuacija2", evakuacija2);
model.addAttribute("evakuacija3", evakuacija3);
model.addAttribute("evakuacija4", evakuacija4);
model.addAttribute("evakuacija5", evakuacija5);
model.addAttribute("evakuacija6", evakuacija6);


/////////////////////////////////////////////////////////////////SEGMENT ZA EVAKUACIJU
/////////////////////////////////////////////////////////////////SEGMENT ZA ZBRINJAVANJE
  TableDefinitionId tableDefinitionIdZbrinjavanje = new TableDefinitionId(5002);
        DynamicTable tabelaZbrinjavanje = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdZbrinjavanje, client);


for (DynamicRow red : tabelaZbrinjavanje.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().getOrder()==14) {
                        zbirnjavanje1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==15) {
                        zbirnjavanje2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==16) {
                        zbirnjavanje3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==17) {
                        zbirnjavanje4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==18) {
                        zbirnjavanje5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==19) {
                        zbirnjavanje6 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==20) {
                        zbirnjavanje7 += Integer.parseInt(data.getValue());
                    };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("zbirnjavanje1", zbirnjavanje1);
model.addAttribute("zbirnjavanje2", zbirnjavanje2);
model.addAttribute("zbirnjavanje3", zbirnjavanje3);
model.addAttribute("zbirnjavanje4", zbirnjavanje4);
model.addAttribute("zbirnjavanje5", zbirnjavanje5);
model.addAttribute("zbirnjavanje6", zbirnjavanje6);
model.addAttribute("zbirnjavanje7", zbirnjavanje7);
/////////////////////////////////////////////////////////////////SEGMENT ZA ZBRINJAVANJE
/////////////////////////////////////////////////////////////////SEGMENT ZA PRVU POMOC

  TableDefinitionId tableDefinitionIdPrvaPomoc = new TableDefinitionId(5003);
        DynamicTable tabelaPrvaPomoc = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdPrvaPomoc, client);


for (DynamicRow red : tabelaPrvaPomoc.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
//prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
if (data.getColumn().getOrder()==14) {
prvaPomoc1 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==15) {
prvaPomoc2 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==16) {
prvaPomoc3 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==17) {
prvaPomoc4 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==18) {
prvaPomoc5 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==19) {
prvaPomoc6 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==20) {
prvaPomoc7 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==21) {
prvaPomoc8 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==22) {
prvaPomoc9 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==23) {
prvaPomoc10 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==24) {
prvaPomoc11 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==25) {
prvaPomoc12 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==26) {
prvaPomoc13 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==27) {
prvaPomoc14 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==28) {
prvaPomoc15 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==29) {
prvaPomoc16 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==30) {
prvaPomoc17 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==31) {
prvaPomoc18 += Integer.parseInt(data.getValue());
};

                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("prvaPomoc1", prvaPomoc1);
model.addAttribute("prvaPomoc2", prvaPomoc2);
model.addAttribute("prvaPomoc3", prvaPomoc3);
model.addAttribute("prvaPomoc4", prvaPomoc4);
model.addAttribute("prvaPomoc5", prvaPomoc5);
model.addAttribute("prvaPomoc6", prvaPomoc6);
model.addAttribute("prvaPomoc7", prvaPomoc7);
model.addAttribute("prvaPomoc8", prvaPomoc8);
model.addAttribute("prvaPomoc9", prvaPomoc9);
model.addAttribute("prvaPomoc10", prvaPomoc10);
model.addAttribute("prvaPomoc11", prvaPomoc11);
model.addAttribute("prvaPomoc12", prvaPomoc12);
model.addAttribute("prvaPomoc13", prvaPomoc13);
model.addAttribute("prvaPomoc14", prvaPomoc14);
model.addAttribute("prvaPomoc15", prvaPomoc15);
model.addAttribute("prvaPomoc16", prvaPomoc16);
model.addAttribute("prvaPomoc17", prvaPomoc17);
model.addAttribute("prvaPomoc18", prvaPomoc18);

/////////////////////////////////////////////////////////////////SEGMENT ZA PRVU POMOC
/////////////////////////////////////////////////////////////////SEGMENT ZA ASANACIJU
 TableDefinitionId tableDefinitionIdAsanacija = new TableDefinitionId(5004);
        DynamicTable tabelaAsanacija = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdAsanacija, client);


for (DynamicRow red : tabelaAsanacija.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
//prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
if (data.getColumn().getOrder()==14) {
asanacija1 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==15) {
asanacija2 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==16) {
asanacija3 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==17) {
asanacija4 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==18) {
asanacija5 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==19) {
asanacija6 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==20) {
asanacija7 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==21) {
asanacija8 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==22) {
asanacija9 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==23) {
asanacija10 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==24) {
asanacija11 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==25) {
asanacija12 += Integer.parseInt(data.getValue());
};

                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("asanacija1", asanacija1);
model.addAttribute("asanacija2", asanacija2);
model.addAttribute("asanacija3", asanacija3);
model.addAttribute("asanacija4", asanacija4);
model.addAttribute("asanacija5", asanacija5);
model.addAttribute("asanacija6", asanacija6);
model.addAttribute("asanacija7", asanacija7);
model.addAttribute("asanacija8", asanacija8);
model.addAttribute("asanacija9", asanacija9);
model.addAttribute("asanacija10", asanacija10);
model.addAttribute("asanacija11", asanacija11);
model.addAttribute("asanacija12", asanacija12);
/////////////////////////////////////////////////////////////////SEGMENT ZA ASANACIJU

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MERE CIVILNE ZASTITE
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MATERIJALNO TEHNICKA SREDSTVA

        List<DetaljiMTS> listaKartonaMTS = detaljiMTSService.findAllByClient(client);
        model.addAttribute("listaKartonaMTS", listaKartonaMTS);
       
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MATERIJALNO TEHNICKA SREDSTVA

        return "client/kartonSubjekta/jedinstveniPregledZbirnihObrazaca";
    }
}
