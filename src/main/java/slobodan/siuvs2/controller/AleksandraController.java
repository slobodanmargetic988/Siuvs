package slobodan.siuvs2.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import slobodan.siuvs2.model.Biblioteka;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Knjiga;

import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.VolonterService;
import slobodan.siuvs2.valueObject.OpstinaID;

@Controller
@RequestMapping(value = "/aleksandra")
public class AleksandraController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(final Model model) {

        return "login";
    }

    @GetMapping(value = "/home")
    public String publicHome(final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");
        /* model.addAttribute("homePage");*/

        return "aleksandra/home";

    }

    @GetMapping(value = "/liste")
    public String publicListe(final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");

        Biblioteka nekabiblioteka = Biblioteka.test();
        Knjiga josjedna = new Knjiga();
        nekabiblioteka.dodajKnjigu(josjedna);
        model.addAttribute("biblioteka", nekabiblioteka);

        return "aleksandra/liste";

    }

    @GetMapping(value = "/home1")
    public String publicHome1(final Model model) {

        return "aleksandra/home_1";

    }

    @GetMapping(value = "/home2")
    public String publicHome2(final Model model) {

        return "aleksandra/home_2";

    }
    
    @GetMapping(value = "/home_agrostemin")
    public String publicHomeAgrostemin(final Model model) {

        return "aleksandra/home_agrostemin";

    }

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/home/listaKlijenata")
    public String publicHomeListaKlijenata(
            final Model model
    ) {
        List<Client> lista = clientService.findAll();
        model.addAttribute("listaKlijenata", lista);
        return "aleksandra/listaKlijenata";
    }

    @Autowired
    private OpstinaService opstinaService;

    @GetMapping(value = "/home/proceneRizika")
    public String publicHomeProceneRizika(
            final Model model
    ) {
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        //  model.addAttribute("proceneRizika", "tekst koji prosledjujemo modelu");
        return "aleksandra/proceneRizika";
    }

    @Autowired
    private MobileappdataService mobileappdataService;
    //  prvi nacin

    @PostMapping(value = "/home/proceneRizika/prikazi")
    public String publicHomeProceneRizika(
            @RequestParam(name = "opstinanamelatinica") String imeopstine,
            @RequestParam(name = "opasnost") String opasnost,
            final Model model
    ) {

        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());

        Mobileappdata map = mobileappdataService.findFirstByOpstinaAndOpasnost(imeopstine, opasnost);

        if (map == null) {
            map = null;
        } else {

            String poruka1 = "Izabrana opstina je " + map.getOpstina() + "\n";
            String poruka2 = "Procena rizika od " + map.getOpasnost() + "\n";
            String poruka3 = "Klasifikacija: " + map.getKlasifikacija() + "\n";
            String poruka4 = "Tekst: " + map.getTekst() + "\n";
            String poruka5 = "Link: " + map.getLink();

            model.addAttribute("prvaporuka", poruka1);
            model.addAttribute("drugaporuka", poruka2);
            model.addAttribute("trecaporuka", poruka3);
            model.addAttribute("cetvrtaporuka", poruka4);
            model.addAttribute("petaporuka", poruka5);

        }
        model.addAttribute("mobileappdata", map); //opis opasnosti
        return "aleksandra/proceneRizika";
    }
//drugi nacin

    @PostMapping(value = "/home/proceneRizika/prikazi2")
    public String publicHomeProceneRizika2(
            @RequestParam(name = "opstinanamelatinica") String imeopstine,
            @RequestParam(name = "opasnost") String opasnost,
            final Model model
    ) {
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        Mobileappdata map = mobileappdataService.findFirstByOpstinaAndOpasnost(imeopstine, opasnost);

        model.addAttribute("mobileappdata2", map);

        return "aleksandra/proceneRizika";
    }

    @Autowired
    private VolonterService volonterService;

    @GetMapping(value = "home/listaVolontera")
    public String publicHomeListaVolontera(final Model model) {
        List<Volonter> lista = volonterService.findAllBy();
        model.addAttribute("listaVolontera", lista);
        return "aleksandra/listaVolontera";
    }

    @GetMapping(value = "home/listaVolontera/Ograniceno")
    public String publicHomeListaVolonteraOgraniceno(final Model model) {
        List<Volonter> lista = volonterService.findAllBy();
        List<Volonter> lista5 = new ArrayList<Volonter>();
        List<Volonter> lista10 = new ArrayList<Volonter>();
        for (int i = 0; i <= 4; i++) {

            lista5.add(lista.get(i));
            lista10.add(lista.get(i + 5));
        }

        model.addAttribute("listaVolontera", lista5);
        model.addAttribute("listaVolontera2", lista10);
        return "aleksandra/listaVolonteraOgraniceno";
    }

    @GetMapping(value = "home/listaVolontera/Ograniceno/obeTabele")
    public String publicHomeListaVolonteraOgranicenoObeTabele(final Model model) {
        List<Volonter> lista = volonterService.findAllBy();
        List<Volonter> lista5 = new ArrayList<Volonter>();
        List<Volonter> lista10 = new ArrayList<Volonter>();
        for (int i = 0; i <= 4; i++) {

            lista5.add(lista.get(i));
            lista10.add(lista.get(i + 5));
        }
        model.addAttribute("listaVolontera", lista5);
        model.addAttribute("listaVolontera2", lista10);

        return "aleksandra/listaVolonteraOgraniceno";
    }

    @GetMapping(value = "home/listaVolontera/Ograniceno/prvih5")
    public String publicHomeListaVolonteraOgraniceno5(final Model model) {
        List<Volonter> lista = volonterService.findAllBy();
        List<Volonter> lista5 = new ArrayList<Volonter>();
        List<Volonter> lista10 = new ArrayList<Volonter>();
        for (int i = 0; i <= 4; i++) {

            lista5.add(lista.get(i));
            lista10.add(lista.get(i + 5));
        }

        model.addAttribute("listaVolontera", lista5);

        return "aleksandra/listaVolonteraOgraniceno";
    }

    @GetMapping(value = "home/listaVolontera/Ograniceno/drugih5")
    public String publicHomeListaVolonteraOgraniceno10(final Model model) {
        List<Volonter> lista = volonterService.findAllBy();
        List<Volonter> lista5 = new ArrayList<Volonter>();
        List<Volonter> lista10 = new ArrayList<Volonter>();
        for (int i = 0; i <= 4; i++) {

            lista5.add(lista.get(i));
            lista10.add(lista.get(i + 5));
        }

        model.addAttribute("listaVolontera2", lista10);
        return "aleksandra/listaVolonteraOgraniceno";
    }

    @GetMapping(value = "/home/listaKorisnika")
    public String publicHomeListaKorisnika(
            final Model model
    ) {
        List<User> lista = userService.findAll();
        //userService.findAll();
        model.addAttribute("listaKorisnika", lista);
        return "aleksandra/listaKorisnika";
    }

    @PostMapping(value = "/home/pozdrav")
    public String publicHomePozdrav(
            @RequestParam(name = "name") String ime,
            @RequestParam(name = "LASTname") String prezime,
            final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");
        String pozdrav = "Dobar dan " + ime + " " + prezime;
        model.addAttribute("testPozdrav", pozdrav);

        model.addAttribute("testIme", ime);

        model.addAttribute("testPrezime", prezime);

        return "aleksandra/home";
    }

    @PostMapping(value = "/home/suma")
    public String publicHomeSuma(
            @RequestParam(name = "broj1") int broj1,
            @RequestParam(name = "broj2") int broj2,
            final Model model) {
        model.addAttribute("test", "tekst koji prosledjujemo modelu");
        model.addAttribute("test2", "tekst2 koji prosledjujemo modelu");

        String suma = "Suma je: " + (broj1 + broj2);
        model.addAttribute("testSuma", suma);

        model.addAttribute("testBroj1", broj1);

        model.addAttribute("testBroj2", broj2);

        return "aleksandra/home";
    }

}
