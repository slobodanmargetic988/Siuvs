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
import slobodan.siuvs2.model.CiljeviUdruzenja;
import slobodan.siuvs2.model.ClanoviUdruzenja;
import slobodan.siuvs2.model.KartonUdruzenja;
import slobodan.siuvs2.model.Specijalnost;
import slobodan.siuvs2.service.CiljeviUdruzenjaService;
import slobodan.siuvs2.service.ClanoviUdruzenjaService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.KartonUdruzenjaService;
import slobodan.siuvs2.service.SpecijalnostService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.KartonUdruzenjaId;
import slobodan.siuvs2.valueObject.SpecijalnostId;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class ClientsKartonUdruzenjaController {

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

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja")
    public String adminkartonUdruzenja(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        List<KartonUdruzenja> listaKartona = kartonUdruzenjaService.findAllByClientOrderByPunnazivAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "admin/clients/kartonUdruzenja/kartonUdruzenjaLista";
    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}")
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
        return "admin/clients/kartonUdruzenja/kartonUdruzenja";
    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/izmeniKarton")
    public String adminkartonUdruzenjaEdit(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        model.addAttribute("karton", karton);

        return "admin/clients/kartonUdruzenja/editKartonUdruzenja";
    }

    @PostMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/izmeniKarton")
    public String adminkartonUdruzenjaEditSave(
            @PathVariable final ClientId clientId,
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
        Client client = clientService.findOne(clientId);
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
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja";
        } else {
            if (action.equals("saveMoreClanova")) {
                redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati članove!");
                return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + karton.getId() + "/dodajClana";
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati ciljeve!");
                return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + karton.getId() + "/dodajCilj";
            }
        }

    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/newKarton")
    public String adminkartonUdruzenjaNew(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);

        return "admin/clients/kartonUdruzenja/newKartonUdruzenja";
    }

    @PostMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/newKarton")
    public String adminkartonUdruzenjaNewSave(
            @PathVariable final ClientId clientId,
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
        Client client = clientService.findOne(clientId);
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
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja";
        } else {

            if (action.equals("saveMoreClanova")) {
                redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati članove!");
                return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + karton.getId() + "/dodajClana";
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati ciljeve!");
                return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + karton.getId() + "/dodajCilj";
            }
        }

    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/dodajClana")
    public String adminkartonUdruzenjadodajClana(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        model.addAttribute("specijalnosti", specijalnostiService.findAllByOrderByNazivAsc());

        return "admin/clients/kartonUdruzenja/newClan";
    }

    @PostMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/dodajClana")
    public String adminkartonUdruzenjadodajClanaSave(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "specijalnost", required = true) SpecijalnostId specijalnostId,
            @RequestParam(value = "brojIzvrsilaca", required = true) Integer brojIzvrsilaca,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonUdruzenja karton = kartonUdruzenjaService.findOne(kartonId);
        ClanoviUdruzenja novClan = new ClanoviUdruzenja();
        Specijalnost specijalnost = specijalnostiService.findOne(specijalnostId);
        ClanoviUdruzenja clan = clanoviUdruzenjaService.findFirstBySpecijalnostAndKartonudruzenja(specijalnost, karton);
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
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + kartonId;
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Nov član je uspešno sačuvan!");
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + kartonId + "/dodajClana";
        }
    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/dodajSpecijalnost")
    public String adminDodajSpecijalnost(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);

        return "admin/clients/kartonUdruzenja/newSpecijalnost";
    }

    @PostMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/dodajSpecijalnost")
    public String adminDodajSpecijalnostSave(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);
        Specijalnost newSpecijalnost = new Specijalnost();

        newSpecijalnost.setNaziv(naziv);

        try {
            specijalnostiService.save(newSpecijalnost);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nova specijalnost nije uspešno sačuvano!");
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + kartonId.getValue() + "/dodajClana";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Nova specijalnost je uspešno sačuvano!");
        return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + kartonId.getValue() + "/dodajClana";
    }

    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/dodajCilj")
    public String adminDodajCilj(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        model.addAttribute("kartonId", kartonId);

        return "admin/clients/kartonUdruzenja/newCilj";
    }

    /*
    @GetMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/dodajCilj")
    public String adminDodajCiljIzNovogKartona(
            @PathVariable final ClientId clientId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
//nema karton id
        KartonUdruzenjaId kartonId = new KartonUdruzenjaId(-1);
        model.addAttribute("kartonId", -1);
        return "admin/clients/kartonUdruzenja/newCilj";
    }*/

    @PostMapping(value = "/admin/clients/{clientId}/kartonUdruzenja/{kartonId}/dodajCilj")
    public String adminDodajCiljSave(
            @PathVariable final ClientId clientId,
            @PathVariable final KartonUdruzenjaId kartonId,
            @RequestParam(value = "naziv", defaultValue = "/") String naziv,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        KartonUdruzenja kartonUdruzenja = kartonUdruzenjaService.findOne(kartonId);
        CiljeviUdruzenja newCiljeviUdruzenja = new CiljeviUdruzenja();
        newCiljeviUdruzenja.setNaziv(naziv);
        newCiljeviUdruzenja.setKartonUdruzenja(kartonUdruzenja);

        try {
            ciljeviUdruzenjaService.save(newCiljeviUdruzenja);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov cilj nije uspešno sačuvan!");
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + kartonId.getValue();
        }
        redirectAttributes.addFlashAttribute("successMessage", "Nov cilj je uspešno sačuvan!");
        /*if (kartonId.getValue() == -1) {
            return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/newKarton";
        } else {*/
        return "redirect:/admin/clients/" + clientId + "/kartonUdruzenja/" + kartonId.getValue();
        // }

    }
}
