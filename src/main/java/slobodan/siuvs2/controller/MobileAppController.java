package slobodan.siuvs2.controller;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import slobodan.siuvs2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.IstorijaNotifikacija;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.IstorijaNotifikacijaService;
import slobodan.siuvs2.service.MobileAppUniqService;
import slobodan.siuvs2.service.MobileappdataFactory;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.service.NotifikacijeService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.VolonterService;
import slobodan.siuvs2.valueObject.ClientId;

@Controller
public class MobileAppController {
 private Integer howManyTimes = 5;// how many times notifications are resent to ensure delivery resend obavestenja obaveštenja
    @Autowired
    private UserService userService;
    @Autowired
    private IstorijaNotifikacijaService istorijaNotifikacijaService;

    @Autowired
    private StorageService storageService;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/admin/mobileapp")
    public String mobileappHome(final Model model) {

        return "admin/mobileapp/homeMobile";
    }

    @GetMapping("/client/mobileapp")
    public String mobileappHomeClient(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        return "client/mobileapp/homeMobile";
    }

    @GetMapping("/admin/mobileapp/slanje")
    public String mobileappSlanje(final Model model) {
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        return "admin/mobileapp/slanje";
    }

    @GetMapping("/client/mobileapp/slanje")
    public String mobileappSlanjeClient(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        return "client/mobileapp/slanje";
    }

    @Autowired
    private OpstinaService opstinaService;

    @GetMapping("/admin/mobileapp/izmenaProcena")
    public String mobileappIzmenaProcena(final Model model, final RedirectAttributes redirectAttributes) {
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        return "admin/mobileapp/izmenaProcena";
    }

    @GetMapping("/client/mobileapp/izmenaProcene")
    public String mobileappIzmenaProcenaClient(final Model model, final RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        String opstinanamelatinica = client.getOpstina().getNamelatinica();
        model.addAttribute("opstinanamelatinica", opstinanamelatinica);
        //    enum('zemljotres','pozar','poplava','kliziste','oluja')
        Mobileappdata podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "oluja");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "oluja");
        }
        model.addAttribute("olujatekst", podaci.getTekst());
        model.addAttribute("olujaklasifikacija", podaci.getKlasifikacija());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "kliziste");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "kliziste");
        }
        model.addAttribute("klizistetekst", podaci.getTekst());
        model.addAttribute("klizisteklasifikacija", podaci.getKlasifikacija());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "pozar");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "pozar");
        }
        model.addAttribute("pozarklasifikacija", podaci.getKlasifikacija());
        model.addAttribute("pozartekst", podaci.getTekst());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "poplava");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "poplava");
        }
        model.addAttribute("poplavatekst", podaci.getTekst());
        model.addAttribute("poplavaklasifikacija", podaci.getKlasifikacija());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "zemljotres");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "zemljotres");
        }
        model.addAttribute("zemljotrestekst", podaci.getTekst());
        model.addAttribute("zemljotresklasifikacija", podaci.getKlasifikacija());

        return "client/mobileapp/izmenaProcene";
    }

    @Autowired
    private MobileappdataService mobileappdataService;
    @Autowired
    private MobileappdataFactory mobileappdataFactory;

    @PostMapping("/admin/mobileapp/izmenaProcena/izmeni")
    public String mobileappIzmenaProceneStrana(
            @RequestParam(name = "opstinanamelatinica", defaultValue = " ") String opstinanamelatinica,
            final Model model,
            final RedirectAttributes redirectAttributes) {
        model.addAttribute("opstinanamelatinica", opstinanamelatinica);
        //    enum('zemljotres','pozar','poplava','kliziste','oluja')
        Mobileappdata podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "oluja");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "oluja");
        }
        model.addAttribute("olujatekst", podaci.getTekst());
        model.addAttribute("olujaklasifikacija", podaci.getKlasifikacija());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "kliziste");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "kliziste");
        }
        model.addAttribute("klizistetekst", podaci.getTekst());
        model.addAttribute("klizisteklasifikacija", podaci.getKlasifikacija());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "pozar");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "pozar");
        }
        model.addAttribute("pozarklasifikacija", podaci.getKlasifikacija());
        model.addAttribute("pozartekst", podaci.getTekst());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "poplava");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "poplava");
        }
        model.addAttribute("poplavatekst", podaci.getTekst());
        model.addAttribute("poplavaklasifikacija", podaci.getKlasifikacija());
        podaci = mobileappdataService.findFirstByOpstinaAndOpasnost(opstinanamelatinica, "zemljotres");
        if (podaci == null) {
            podaci = mobileappdataFactory.empty(opstinanamelatinica, "zemljotres");
        }
        model.addAttribute("zemljotrestekst", podaci.getTekst());
        model.addAttribute("zemljotresklasifikacija", podaci.getKlasifikacija());

        return "admin/mobileapp/izmenaProcene";
    }
    @Autowired
    private ClientService clientService;

    @PostMapping("/admin/mobileapp/izmenaProcene/izmeni/{opstina}")
    public String mobileappIzmenaProceneIzvrsi(
            @PathVariable final String opstina,
            @RequestParam(name = "olujatekst", defaultValue = " ") String olujatekst,
            @RequestParam(name = "olujaklasifikacija", defaultValue = " ") String olujaklasifikacija,
            @RequestParam(name = "klizistetekst", defaultValue = " ") String klizistetekst,
            @RequestParam(name = "klizisteklasifikacija", defaultValue = " ") String klizisteklasifikacija,
            @RequestParam(name = "pozarklasifikacija", defaultValue = " ") String pozarklasifikacija,
            @RequestParam(name = "pozartekst", defaultValue = " ") String pozartekst,
            @RequestParam(name = "poplavatekst", defaultValue = " ") String poplavatekst,
            @RequestParam(name = "poplavaklasifikacija", defaultValue = " ") String poplavaklasifikacija,
            @RequestParam(name = "zemljotrestekst", defaultValue = " ") String zemljotrestekst,
            @RequestParam(name = "zemljotresklasifikacija", defaultValue = " ") String zemljotresklasifikacija,
            final Model model,
            final RedirectAttributes redirectAttributes) {

        //    enum('zemljotres','pozar','poplava','kliziste','oluja')
        try {
            Mobileappdata mad = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "zemljotres");
            if (mad == null) {
                mad = mobileappdataFactory.empty(opstina, "zemljotres");
            }
            mad.setKlasifikacija(zemljotresklasifikacija);
            mad.setTekst(zemljotrestekst);
            Opstina opstinalink = opstinaService.findFirstByNamelatinica(opstina);
            int clientid = clientService.findFirstByOpstina(opstinalink).getId();
            String link = "https://siuvs.rs/publicaccess/clients/" + clientid;
            mad.setLink(link);
            mobileappdataService.save(mad);

            Mobileappdata mad2 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "poplava");
            if (mad2 == null) {
                mad2 = mobileappdataFactory.empty(opstina, "poplava");
            }
            mad2.setKlasifikacija(poplavaklasifikacija);
            mad2.setTekst(poplavatekst);
            mad2.setLink(link);
            mobileappdataService.save(mad2);

            Mobileappdata mad3 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "pozar");
            if (mad3 == null) {
                mad3 = mobileappdataFactory.empty(opstina, "pozar");
            }
            mad3.setKlasifikacija(pozarklasifikacija);
            mad3.setTekst(pozartekst);
            mad3.setLink(link);
            mobileappdataService.save(mad3);

            Mobileappdata mad4 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "kliziste");
            if (mad4 == null) {
                mad4 = mobileappdataFactory.empty(opstina, "kliziste");
            }
            mad4.setKlasifikacija(klizisteklasifikacija);
            mad4.setTekst(klizistetekst);
            mad4.setLink(link);
            mobileappdataService.save(mad4);

            Mobileappdata mad5 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "oluja");
            if (mad5 == null) {
                mad5 = mobileappdataFactory.empty(opstina, "oluja");
            }
            mad5.setKlasifikacija(olujaklasifikacija);
            mad5.setTekst(olujatekst);
            mad5.setLink(link);
            mobileappdataService.save(mad5);

            redirectAttributes.addFlashAttribute("successMessage", "Измене су успешно сачуване!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања измена!" + e.toString());
        }
        return "redirect:/admin/mobileapp/izmenaProcena";
    }

    @PostMapping("/client/mobileapp/izmenaProcene/izmeni")
    public String mobileappIzmenaProceneIzvrsiClient(
            @RequestParam(name = "olujatekst", defaultValue = " ") String olujatekst,
            @RequestParam(name = "olujaklasifikacija", defaultValue = " ") String olujaklasifikacija,
            @RequestParam(name = "klizistetekst", defaultValue = " ") String klizistetekst,
            @RequestParam(name = "klizisteklasifikacija", defaultValue = " ") String klizisteklasifikacija,
            @RequestParam(name = "pozarklasifikacija", defaultValue = " ") String pozarklasifikacija,
            @RequestParam(name = "pozartekst", defaultValue = " ") String pozartekst,
            @RequestParam(name = "poplavatekst", defaultValue = " ") String poplavatekst,
            @RequestParam(name = "poplavaklasifikacija", defaultValue = " ") String poplavaklasifikacija,
            @RequestParam(name = "zemljotrestekst", defaultValue = " ") String zemljotrestekst,
            @RequestParam(name = "zemljotresklasifikacija", defaultValue = " ") String zemljotresklasifikacija,
            final Model model,
            final RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        String opstina = client.getOpstina().getNamelatinica();
        //    enum('zemljotres','pozar','poplava','kliziste','oluja')
        try {
            Mobileappdata mad = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "zemljotres");
            if (mad == null) {
                mad = mobileappdataFactory.empty(opstina, "zemljotres");
            }
            mad.setKlasifikacija(zemljotresklasifikacija);
            mad.setTekst(zemljotrestekst);
            Opstina opstinalink = opstinaService.findFirstByNamelatinica(opstina);
            int clientid = clientService.findFirstByOpstina(opstinalink).getId();
            String link = "https://siuvs.rs/publicaccess/clients/" + clientid;
            mad.setLink(link);
            mobileappdataService.save(mad);

            Mobileappdata mad2 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "poplava");
            if (mad2 == null) {
                mad2 = mobileappdataFactory.empty(opstina, "poplava");
            }
            mad2.setKlasifikacija(poplavaklasifikacija);
            mad2.setTekst(poplavatekst);
            mad2.setLink(link);
            mobileappdataService.save(mad2);

            Mobileappdata mad3 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "pozar");
            if (mad3 == null) {
                mad3 = mobileappdataFactory.empty(opstina, "pozar");
            }
            mad3.setKlasifikacija(pozarklasifikacija);
            mad3.setTekst(pozartekst);
            mad3.setLink(link);
            mobileappdataService.save(mad3);

            Mobileappdata mad4 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "kliziste");
            if (mad4 == null) {
                mad4 = mobileappdataFactory.empty(opstina, "kliziste");
            }
            mad4.setKlasifikacija(klizisteklasifikacija);
            mad4.setTekst(klizistetekst);
            mad4.setLink(link);
            mobileappdataService.save(mad4);

            Mobileappdata mad5 = mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "oluja");
            if (mad5 == null) {
                mad5 = mobileappdataFactory.empty(opstina, "oluja");
            }
            mad5.setKlasifikacija(olujaklasifikacija);
            mad5.setTekst(olujatekst);
            mad5.setLink(link);
            mobileappdataService.save(mad5);

            redirectAttributes.addFlashAttribute("successMessage", "Измене су успешно сачуване!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања измена!" + e.toString());
        }
        return "redirect:/client/mobileapp";
    }

    @Autowired
    private VolonterService volonterService;

    @GetMapping("/admin/mobileapp/pregledVolontera")
    public String mobileappPregledVolontera(final Model model) {
        List<Volonter> volonteri = volonterService.findAllBy();
        model.addAttribute("volonteri", volonteri);
        return "admin/mobileapp/pregledVolontera";
    }

    @GetMapping("/client/mobileapp/pregledVolontera")
    public String mobileappPregledVolonteraClient(final Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        List<Volonter> volonteri = volonterService.findAllByOpstina(client.getOpstina().getNamelatinica());
        model.addAttribute("volonteri", volonteri);
        return "client/mobileapp/pregledVolontera";
    }

    @Autowired
    private NotifikacijeService notifikacijeService;

    @GetMapping("/admin/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacije(final Model model) {
        List<Notifikacije> notifikacije = notifikacijeService.findAllByOrderByOpstinaAsc();
        model.addAttribute("notifikacije", notifikacije);
        return "admin/mobileapp/prijavljeniZaNotifikacije";
    }

    @GetMapping("/client/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacijeClient(final Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        List<Notifikacije> notifikacije = notifikacijeService.findAllByOpstina(client.getOpstina());
        model.addAttribute("notifikacije", notifikacije);
        return "client/mobileapp/prijavljeniZaNotifikacije";
    }

    @PostMapping("/admin/mobileapp/slanje/posalji")
    public String mobileappSlanjeNotifikacije(
            @RequestParam(name = "titleText", defaultValue = " ") String titleText,
            @RequestParam(name = "bodyText", defaultValue = " ") String bodyText,
            @RequestParam(name = "messageText", defaultValue = " ") String messageText,
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,
            @RequestParam(name = "opstinanamelatinica", defaultValue = " ") String opstinanamelatinica,
            @RequestParam(name = "file", required = false) MultipartFile file,
            final Model model,
            final RedirectAttributes redirectAttributes) {

        List<String> primaoci;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        if (opstinanamelatinica.equals("Sve Opštine")) {
            primaoci = notifikacijeService.findDistinctByToken();
        } else {
            primaoci = notifikacijeService.findAllByOpstina(opstinanamelatinica);
        }
       // String registration_ids = buildRegistrationIds(primaoci);

        String titleTextV = " ";
        String bodyTextV = " ";
        String imageTextV = " ";
        String messageTextV = " ";
        String linkTextV = " ";
        String linkTextTextV = " ";

        if (!titleText.equals("") && !titleText.equals(" ")) {
            titleTextV = titleText;
        }

        if (!bodyText.equals("") && !bodyText.equals(" ")) {
            bodyTextV = bodyText;
        }

        if (!messageText.equals("") && !messageText.equals(" ")) {
            messageTextV = messageText;
        }
        if (!linkText.equals("") && !linkText.equals(" ") && linkText.startsWith("http")) {
            linkTextV = linkText;
            if (!linkTextText.equals("") && !linkTextText.equals(" ")) {
                linkTextTextV = linkTextText;
            }
        }

        ClientId id;
        if (client == null) {
            id = new ClientId(0);
        } else {
            id = client.getClientId();
        }
        String filename = "";
        if (file.isEmpty()) {
            imageTextV = " ";

        } else {

            try {

                filename = storageService.store(file, id);
                imageTextV = "https://www.siuvs.rs/php/getimg/" + id + "/" + filename + ".";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања слике!");
            }
        }
        IstorijaNotifikacija istorijaNotifikacija = new IstorijaNotifikacija();
        istorijaNotifikacija.setTitle(titleTextV);
        istorijaNotifikacija.setBody(bodyTextV);
     
        istorijaNotifikacija.setMessage(messageTextV);
        istorijaNotifikacija.setLink(linkTextV);
        istorijaNotifikacija.setLink_text(linkTextV);
        istorijaNotifikacija.setImg_file_name(filename);
        istorijaNotifikacija.setClient(client);
        istorijaNotifikacija.setCreatedBy(user);
        istorijaNotifikacija.setImg_link(imageTextV);
        istorijaNotifikacijaService.save(istorijaNotifikacija);

        String JSON_Body = buildJSONBody(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaoci);

        HttpClient httpclient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
        String HOST = "https://fcm.googleapis.com/fcm/send";
        HttpPost post = new HttpPost(HOST);
        post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
        post.setEntity(requestEntity);
        try {
            HttpResponse rawResponse = httpclient.execute(post);
            
// System.out.println("odgovor od googla je      "+rawResponse);

            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }
        //repeat notification 5 times every 20 minutes

        repeatNotification(howManyTimes, post);
        return "redirect:/admin/mobileapp/slanje";

    }

    @PostMapping("/client/mobileapp/slanje/posalji")
    public String mobileappSlanjeNotifikacijeClient(
            @RequestParam(name = "titleText", defaultValue = " ") String titleText,
            @RequestParam(name = "bodyText", defaultValue = " ") String bodyText,
            @RequestParam(name = "messageText", defaultValue = " ") String messageText,
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,
            @RequestParam(name = "file", required = false) MultipartFile file,
            final Model model,
            final RedirectAttributes redirectAttributes) {

        List<String> primaoci;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        primaoci = notifikacijeService.findAllByOpstina(client.getOpstina().getNamelatinica());

        //String registration_ids = buildRegistrationIds(primaoci);

        String titleTextV = " ";
        String bodyTextV = " ";
        String imageTextV = " ";
        String messageTextV = " ";
        String linkTextV = " ";
        String linkTextTextV = " ";

        if (!titleText.equals("") && !titleText.equals(" ")) {
            titleTextV = titleText;
        }

        if (!bodyText.equals("") && !bodyText.equals(" ")) {
            bodyTextV = bodyText;
        }

        if (!messageText.equals("") && !messageText.equals(" ")) {
            messageTextV = messageText;
        }
        if (!linkText.equals("") && !linkText.equals(" ") && linkText.startsWith("http")) {
            linkTextV = linkText;
            if (!linkTextText.equals("") && !linkTextText.equals(" ")) {
                linkTextTextV = linkTextText;
            }
        }
        String filename = "";
        if (file.isEmpty()) {
            imageTextV = " ";

        } else {

            //this part goes in else
            try {

                filename = storageService.store(file, client.getClientId());
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања слике!");
            }
            imageTextV = "https://www.siuvs.rs/php/getimg/" + client.getClientId() + "/" + filename + ".";
        }
        IstorijaNotifikacija istorijaNotifikacija = new IstorijaNotifikacija();
        istorijaNotifikacija.setTitle(titleTextV);
        istorijaNotifikacija.setBody(bodyTextV);
        istorijaNotifikacija.setMessage(messageTextV);
        istorijaNotifikacija.setLink(linkTextV);
        istorijaNotifikacija.setLink_text(linkTextV);
        istorijaNotifikacija.setImg_file_name(filename);
        istorijaNotifikacija.setClient(client);
        istorijaNotifikacija.setCreatedBy(user);
        istorijaNotifikacija.setImg_link(imageTextV);
        istorijaNotifikacijaService.save(istorijaNotifikacija);

        String JSON_Body = buildJSONBody(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaoci);

        HttpClient httpclient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
        String HOST = "https://fcm.googleapis.com/fcm/send";
        HttpPost post = new HttpPost(HOST);
        post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
        post.setEntity(requestEntity);
        try {
            HttpResponse rawResponse = httpclient.execute(post);

            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }
        //repeat notification 5 times every 20 minutes
   
        repeatNotification(howManyTimes, post);
        return "redirect:/client/mobileapp/slanje";

    }

    private String buildJSONBody(IstorijaNotifikacija istorijaNotifikacija, String title, String body, String image, String message, String link, String linkText, List<String> primaoci) {

JSONObject jsonPoruka = new JSONObject();
JSONObject jsonData = new JSONObject();
JSONArray jsonRegistrationIdsArray = new JSONArray();

try{
    //build data json
jsonData.put("title", title);
jsonData.put("body", body);
jsonData.put("image", image);
jsonData.put("message", message);
jsonData.put("link", link);
jsonData.put("linkText", linkText);
jsonData.put("serverNotificationId", istorijaNotifikacija.getId());

//build registration ids json
Iterator<String> iterator = primaoci.iterator();
        while (iterator.hasNext()) {
jsonRegistrationIdsArray.put(iterator.next());
        }

//build payload json
jsonPoruka.put("data",jsonData);
jsonPoruka.put("registration_ids",jsonRegistrationIdsArray);
}catch(Exception e){}




String payload = jsonPoruka.toString();

        System.out.println("jsonPoruka je evo je :   "+jsonPoruka+ "  ");
        System.out.println("payload je evo je :   "+payload+ "  ");
        
       return payload;
    }
    
   

    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private void repeatNotification(Integer howManyTimes, HttpPost post) {

        final Runnable beeper = new Runnable() {
            public void run() {
                //System.out.println("sending notification on timer test");

                HttpClient httpclient = HttpClients.createDefault();
                try {
                    HttpResponse rawResponse = httpclient.execute(post);
                   // System.out.println(rawResponse);
                } catch (Exception e) {
                }
            }
        };

        Integer initialDelay = 60 * 20;//60 seconds*20 =minutes initial delay
        Integer periodBetweenSending = 60 * 20;//60 seconds*20 =minutes period Between Sending
        Integer runForHowLong = 60 * 20 * howManyTimes;//60 seconds*20*howManyTimes =how long will the thread run, also howManyTimes= basicaly equals how many times will sending execute
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, initialDelay, periodBetweenSending, SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, runForHowLong, SECONDS);

    }

    @GetMapping("/admin/mobileapp/istorijaNotifikacija")
    public String mobileappIstorijaNotifikacija(final Model model) {
        List<IstorijaNotifikacija> istorijaNotifikacija = istorijaNotifikacijaService.findAllBy();
        model.addAttribute("notifikacije", istorijaNotifikacija);
        return "admin/mobileapp/istorijaNotifikacija";
    }

    @GetMapping("/client/mobileapp/istorijaNotifikacija")
    public String mobileappIstorijaNotifikacijaClient(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        List<IstorijaNotifikacija> istorijaNotifikacija = istorijaNotifikacijaService.findAllByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("notifikacije", istorijaNotifikacija);
        return "client/mobileapp/istorijaNotifikacija";
    }

    @GetMapping("/admin/mobileapp/pregledNotifikacije/{id}")
    public String mobileappPregledNotifikacije(
            @PathVariable final Integer id,
            final Model model) {

        IstorijaNotifikacija notifikacija = istorijaNotifikacijaService.findById(id);
        model.addAttribute("notifikacija", notifikacija);
        return "admin/mobileapp/pregledNotifikacije";
    }

    @GetMapping("/client/mobileapp/pregledNotifikacije/{id}")
    public String mobileappPregledNotifikacijeClient(
            @PathVariable final Integer id,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        IstorijaNotifikacija notifikacija = istorijaNotifikacijaService.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("notifikacija", notifikacija);
        return "client/mobileapp/pregledNotifikacije";
    }
    /*
     @GetMapping(value = "/{id}/notificationPhoto/{filename}")
    public ResponseEntity<Resource> servePhoto(
            @PathVariable final Integer id,
            @PathVariable final String filename
    ) {
       ClientId clientId =new ClientId(id);
        Resource file = storageService.loadAsResource(clientId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
     */
    @Autowired
    MobileAppUniqService mobileAppUniqService;
    
    @PostMapping("/admin/mobileapp/slanje/posalji/all")
    public String mobileappSlanjeNotifikacijeSvima(
            @RequestParam(name = "titleText", defaultValue = " ") String titleText,
            @RequestParam(name = "bodyText", defaultValue = " ") String bodyText,
            @RequestParam(name = "messageText", defaultValue = " ") String messageText,
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,
            
            @RequestParam(name = "file", required = false) MultipartFile file,
            final Model model,
            final RedirectAttributes redirectAttributes) {

        List<String> primaoci;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
/*
        if (opstinanamelatinica.equals("Sve Opštine")) {
            primaoci = notifikacijeService.findDistinctByToken();
        } else {
            primaoci = notifikacijeService.findAllByOpstina(opstinanamelatinica);
        }*/
primaoci= mobileAppUniqService.findDistinctToken();
        
       // String registration_ids = buildRegistrationIds(primaoci);

        String titleTextV = " ";
        String bodyTextV = " ";
        String imageTextV = " ";
        String messageTextV = " ";
        String linkTextV = " ";
        String linkTextTextV = " ";

        if (!titleText.equals("") && !titleText.equals(" ")) {
            titleTextV = titleText;
        }

        if (!bodyText.equals("") && !bodyText.equals(" ")) {
            bodyTextV = bodyText;
        }

        if (!messageText.equals("") && !messageText.equals(" ")) {
            messageTextV = messageText;
        }
        if (!linkText.equals("") && !linkText.equals(" ") && linkText.startsWith("http")) {
            linkTextV = linkText;
            if (!linkTextText.equals("") && !linkTextText.equals(" ")) {
                linkTextTextV = linkTextText;
            }
        }

        ClientId id;
        if (client == null) {
            id = new ClientId(0);
        } else {
            id = client.getClientId();
        }
        String filename = "";
        if (file.isEmpty()) {
            imageTextV = " ";

        } else {

            try {

                filename = storageService.store(file, id);
                imageTextV = "https://www.siuvs.rs/php/getimg/" + id + "/" + filename + ".";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања слике!");
            }
        }
        IstorijaNotifikacija istorijaNotifikacija = new IstorijaNotifikacija();
        istorijaNotifikacija.setTitle(titleTextV);
        istorijaNotifikacija.setBody(bodyTextV);
        istorijaNotifikacija.setMessage(messageTextV);
        istorijaNotifikacija.setLink(linkTextV);
        istorijaNotifikacija.setLink_text(linkTextV);
        istorijaNotifikacija.setImg_file_name(filename);
        istorijaNotifikacija.setClient(client);
        istorijaNotifikacija.setCreatedBy(user);
        istorijaNotifikacija.setImg_link(imageTextV);
        istorijaNotifikacijaService.save(istorijaNotifikacija);

        String JSON_Body = buildJSONBody(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaoci);

        HttpClient httpclient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
        String HOST = "https://fcm.googleapis.com/fcm/send";
        HttpPost post = new HttpPost(HOST);
        post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
        post.setEntity(requestEntity);
        try {
            HttpResponse rawResponse = httpclient.execute(post);

            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }
        //repeat notification 5 times every 20 minutes
 
       repeatNotification(howManyTimes, post);
        return "redirect:/admin/mobileapp/slanje";

    }
}
