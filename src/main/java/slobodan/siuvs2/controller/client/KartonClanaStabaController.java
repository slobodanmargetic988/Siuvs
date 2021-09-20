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
import slobodan.siuvs2.model.KartonClanovaStaba;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.CiljeviUdruzenjaService;
import slobodan.siuvs2.service.ClanoviUdruzenjaService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.KartonClanovaStabaService;
import slobodan.siuvs2.service.SpecijalnostService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.KartonClanovaStabaId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class KartonClanaStabaController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private KartonClanovaStabaService kartonClanovaStabaService;
    @Autowired
    private SpecijalnostService specijalnostiService;
    @Autowired
    private ClanoviUdruzenjaService clanoviUdruzenjaService;
    @Autowired
    private CiljeviUdruzenjaService ciljeviUdruzenjaService;

    @GetMapping(value = "/client/kartonClanaStaba")
    public String clientkartonClanaStaba(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        model.addAttribute("client", client);
        List<KartonClanovaStaba> listaKartona = kartonClanovaStabaService.findAllByClientOrderByPunoimeAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "/client/kartonClanovaStaba/kartonClanaStabaLista";
    }

    @GetMapping(value = "/client/kartonClanaStaba/{kartonId}")
    public String adminkartonUdruzenjaJedan(
            @PathVariable final KartonClanovaStabaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "/client/kartonClanovaStaba/kartonClanaStaba";
    }

    @GetMapping(value = "/client/kartonClanaStaba/newKarton")
    public String clientkartonClanaStabaNew(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        return "/client/kartonClanovaStaba/newKartonClanaStaba";
    }

    @PostMapping(value = "/client/kartonClanaStaba/newKarton")
    public String clientkartonClanovaStabaNewSave(
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
            @RequestParam(value = "naziv_operativnog_staba", defaultValue = "/") String naziv_operativnog_staba,
            @RequestParam(value = "naziv_strucno_operativnog_tima", defaultValue = "/") String naziv_strucno_operativnog_tima,
            @RequestParam(value = "funkcija_u_stabu", defaultValue = "/") String funkcija_u_stabu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
            @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
            @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
            @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
            @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
            @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
            @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
            @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        KartonClanovaStaba karton = new KartonClanovaStaba();

//        KartonUdruzenja karton = new KartonUdruzenja();
        karton.setClient(client);

        karton.setNaziv_staba_vanr_situacije(naziv_staba_vanr_situacije);
        karton.setAdresa_staba(adresa_staba);
        karton.setFiksni_telefon_staba(fiksni_telefon_staba);
        karton.setMobilni_telefon_staba(mobilni_telefon_staba);
        karton.setEmail_staba(email_staba);
        karton.setNaziv_operativnog_staba(naziv_operativnog_staba);
        karton.setNaziv_strucno_operativnog_tima(naziv_strucno_operativnog_tima);
        karton.setFunkcija_u_stabu(funkcija_u_stabu);
        karton.setPunoime(punoime);
        karton.setNaziv_organa_gde_radi(naziv_organa_gde_radi);
        karton.setAdresa_organa_gde_radi(adresa_organa_gde_radi);
        karton.setFunkcija_na_radu(funkcija_na_radu);
        karton.setFiksni_telefon_sluzbeni(fiksni_telefon_sluzbeni);
        karton.setMobilni_telefon_sluzbeni(mobilni_telefon_sluzbeni);
        karton.setEmail_sluzbeni(email_sluzbeni);
        karton.setEmail_licni(email_licni);
        karton.setAdresa_stanovanja(adresa_stanovanja);
        karton.setTelefon_u_stanu(telefon_u_stanu);
        karton.setUcesce_na_obukama(ucesce_na_obukama);

        try {
            kartonClanovaStabaService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
            return "redirect:/client/kartonClanaStaba";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati kadrove!");
            return "redirect:/client/" + karton.getId() + "/dodajClana";
        }

    }

    @GetMapping(value = "/client/kartonClanaStaba/{kartonId}/izmeniKarton")
    public String clientkartonClanaStabaEdit(
            @PathVariable final KartonClanovaStabaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        model.addAttribute("karton", karton);

        return "/client/kartonClanovaStaba/editKartonClanaStaba";
    }

    @PostMapping(value = "/client/kartonClanaStaba/{kartonId}/izmeniKarton")
    public String clientkartonClanaStabaEditSave(
            @PathVariable final KartonClanovaStabaId kartonId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
            @RequestParam(value = "naziv_operativnog_staba", defaultValue = "/") String naziv_operativnog_staba,
            @RequestParam(value = "naziv_strucno_operativnog_tima", defaultValue = "/") String naziv_strucno_operativnog_tima,
            @RequestParam(value = "funkcija_u_stabu", defaultValue = "/") String funkcija_u_stabu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
            @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
            @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
            @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
            @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
            @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
            @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
            @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        karton.setNaziv_staba_vanr_situacije(naziv_staba_vanr_situacije);
        karton.setAdresa_staba(adresa_staba);
        karton.setFiksni_telefon_staba(fiksni_telefon_staba);
        karton.setMobilni_telefon_staba(mobilni_telefon_staba);
        karton.setEmail_staba(email_staba);
        karton.setNaziv_operativnog_staba(naziv_operativnog_staba);
        karton.setNaziv_strucno_operativnog_tima(naziv_strucno_operativnog_tima);
        karton.setFunkcija_u_stabu(funkcija_u_stabu);
        karton.setPunoime(punoime);
        karton.setNaziv_organa_gde_radi(naziv_organa_gde_radi);
        karton.setAdresa_organa_gde_radi(adresa_organa_gde_radi);
        karton.setFunkcija_na_radu(funkcija_na_radu);
        karton.setFiksni_telefon_sluzbeni(fiksni_telefon_sluzbeni);
        karton.setMobilni_telefon_sluzbeni(mobilni_telefon_sluzbeni);
        karton.setEmail_sluzbeni(email_sluzbeni);
        karton.setEmail_licni(email_licni);
        karton.setAdresa_stanovanja(adresa_stanovanja);
        karton.setTelefon_u_stanu(telefon_u_stanu);
        karton.setUcesce_na_obukama(ucesce_na_obukama);

        try {
            kartonClanovaStabaService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
            return "redirect:/client/kartonClanaStaba";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen možete dodati kadrove!");
            return "redirect:/client/kartonClanaStaba/" + karton.getId() + "/dodajClana";
        }

    }

}
