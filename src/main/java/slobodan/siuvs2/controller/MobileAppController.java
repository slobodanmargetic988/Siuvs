package slobodan.siuvs2.controller;

import java.util.Iterator;
import java.util.List;
import slobodan.siuvs2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.MobileappdataFactory;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.service.NotifikacijeService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.VolonterService;

@Controller
public class MobileAppController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/mobileapp")
    public String mobileappHome(final Model model) {

        return "admin/mobileapp/homeMobile";
    }

    @GetMapping("/admin/mobileapp/slanje")
    public String mobileappSlanje(final Model model) {
 model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        return "admin/mobileapp/slanje";
    }
    @Autowired
    private OpstinaService opstinaService;

    @GetMapping("/admin/mobileapp/izmenaProcena")
    public String mobileappIzmenaProcena(final Model model, final RedirectAttributes redirectAttributes) {
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc());
        return "admin/mobileapp/izmenaProcena";
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
        Mobileappdata podaci=mobileappdataService.findFirstByOpstinaAndOpasnost( opstinanamelatinica,"oluja");
        if (podaci==null){podaci=mobileappdataFactory.empty(opstinanamelatinica, "oluja");}
        model.addAttribute("olujatekst", podaci.getTekst());
        model.addAttribute("olujaklasifikacija", podaci.getKlasifikacija());
        podaci=mobileappdataService.findFirstByOpstinaAndOpasnost( opstinanamelatinica,"kliziste");
        if (podaci==null){podaci=mobileappdataFactory.empty(opstinanamelatinica, "kliziste");}
        model.addAttribute("klizistetekst",  podaci.getTekst());
        model.addAttribute("klizisteklasifikacija", podaci.getKlasifikacija());
          podaci=mobileappdataService.findFirstByOpstinaAndOpasnost( opstinanamelatinica,"pozar");
          if (podaci==null){podaci=mobileappdataFactory.empty(opstinanamelatinica, "pozar");}
        model.addAttribute("pozarklasifikacija", podaci.getKlasifikacija());
        model.addAttribute("pozartekst",  podaci.getTekst());
          podaci=mobileappdataService.findFirstByOpstinaAndOpasnost( opstinanamelatinica,"poplava");
          if (podaci==null){podaci=mobileappdataFactory.empty(opstinanamelatinica, "poplava");}
        model.addAttribute("poplavatekst",  podaci.getTekst());
        model.addAttribute("poplavaklasifikacija", podaci.getKlasifikacija());
          podaci=mobileappdataService.findFirstByOpstinaAndOpasnost( opstinanamelatinica,"zemljotres");
          if (podaci==null){podaci=mobileappdataFactory.empty(opstinanamelatinica, "zemljotres");}
        model.addAttribute("zemljotrestekst",  podaci.getTekst());
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
    try{
       Mobileappdata mad= mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "zemljotres");
        if (mad==null){mad=mobileappdataFactory.empty(opstina, "zemljotres");}
       mad.setKlasifikacija(zemljotresklasifikacija);
       mad.setTekst(zemljotrestekst);
       Opstina opstinalink=opstinaService.findFirstByNamelatinica(opstina);
       int clientid=clientService.findFirstByOpstina(opstinalink).getId();
       String link="https://siuvs.rs/publicaccess/clients/"+clientid;
       mad.setLink(link);
       mobileappdataService.save(mad);
       
       Mobileappdata mad2= mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "poplava");
         if (mad2==null){mad2=mobileappdataFactory.empty(opstina, "poplava");}
       mad2.setKlasifikacija(poplavaklasifikacija);
       mad2.setTekst(poplavatekst);
       mad2.setLink(link);
       mobileappdataService.save(mad2);
       
      Mobileappdata mad3= mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "pozar");
       if (mad3==null){mad3=mobileappdataFactory.empty(opstina, "pozar");}
       mad3.setKlasifikacija(pozarklasifikacija);
       mad3.setTekst(pozartekst);
       mad3.setLink(link);
       mobileappdataService.save(mad3);
       
       Mobileappdata mad4= mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "kliziste");
        if (mad4==null){mad4=mobileappdataFactory.empty(opstina, "kliziste");}
       mad4.setKlasifikacija(klizisteklasifikacija);
       mad4.setTekst(klizistetekst);
       mad4.setLink(link);
       mobileappdataService.save(mad4);
       
       Mobileappdata mad5= mobileappdataService.findFirstByOpstinaAndOpasnost(opstina, "oluja");
        if (mad5==null){mad5=mobileappdataFactory.empty(opstina, "oluja");}
       mad5.setKlasifikacija(olujaklasifikacija);
       mad5.setTekst(olujatekst);
       mad5.setLink(link);
       mobileappdataService.save(mad5);
       
       
 redirectAttributes.addFlashAttribute("successMessage", "Измене су успешно сачуване!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања измена!" +e.toString());
}
        return "redirect:/admin/mobileapp/izmenaProcena";
    }
    
     @Autowired
    private VolonterService volonterService;
    
    @GetMapping("/admin/mobileapp/pregledVolontera")
    public String mobileappPregledVolontera(final Model model) {
List<Volonter> volonteri=volonterService.findAllBy();
 model.addAttribute("volonteri", volonteri);
        return "admin/mobileapp/pregledVolontera";
    }
     @Autowired
    private NotifikacijeService notifikacijeService;
    @GetMapping("/admin/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacije(final Model model) {
List<Notifikacije> notifikacije=notifikacijeService.findAllByOrderByOpstinaAsc();
 model.addAttribute("notifikacije", notifikacije);
        return "admin/mobileapp/prijavljeniZaNotifikacije";
    }

    @GetMapping("/admin/mobileapp/istorijaNotifikacija")
    public String mobileappIstorijaNotifikacija(final Model model) {

        return "admin/mobileapp/istorijaNotifikacija";
    }

    @PostMapping("/admin/mobileapp/slanje/posalji")
    public String mobileappSlanjeNotifikacije(
            @RequestParam(name = "titleText", defaultValue = " ") String titleText,
            @RequestParam(name = "bodyText", defaultValue = " ") String bodyText,
            @RequestParam(name = "messageText", defaultValue = " ") String messageText,
            @RequestParam(name = "imageText", defaultValue = " ") String imageText,
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,
            @RequestParam(name = "opstinanamelatinica", defaultValue = " ") String opstinanamelatinica,
            final Model model,
            final RedirectAttributes redirectAttributes) {
  
         List<String> primaoci;
        primaoci =notifikacijeService.findDistinctByToken();
    
        if (opstinanamelatinica.equals("Sve Opštine")){
   primaoci =notifikacijeService.findDistinctByToken();
        }
        else{
        primaoci=notifikacijeService.findAllByOpstina(opstinanamelatinica);
        }
        String registration_ids = buildRegistrationIds(primaoci);

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

        if (!imageText.equals("") && !imageText.equals(" ") && imageText.startsWith("http")) {
            imageTextV = imageText;
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

        String JSON_Body = buildJSONBody(titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, registration_ids);
        try {
            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post.setEntity(requestEntity);
            HttpResponse rawResponse = httpclient.execute(post);

            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n "+registration_ids);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }

        return "redirect:/admin/mobileapp/slanje";

    }
//block used to demonstrate and test features across server app and mobileapp

    @PostMapping("/admin/mobileapp/slanje/posalji1")
    public String mobileappSlanjeNotifikacije1(final Model model,
            final RedirectAttributes redirectAttributes) {
        String title = "Naslov 1 sa siuvs.rs";
        String body = "Telo 1 sa siuvs.rs";
        String image = "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2020/02/Google-Image-Search.jpg";
        String message = "Poruka 1 sa siuvs.rs";
        String link = "https://siuvs.rs/";
        String linkText = "Login strana na siuvs.rs";
 List<String> primaoci;
        primaoci =notifikacijeService.findDistinctByToken();
    
        String registration_ids = buildRegistrationIds(primaoci);
        String JSON_Body = buildJSONBody(title, body, image, message, link, linkText, registration_ids);
        try {
            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post.setEntity(requestEntity);
            HttpResponse rawResponse = httpclient.execute(post);
            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }

        return "redirect:/admin/mobileapp/slanje";
    }

    @PostMapping("/admin/mobileapp/slanje/posalji2")
    public String mobileappSlanjeNotifikacije2(final Model model,
            final RedirectAttributes redirectAttributes) {
        String title = "Naslov 2 sa siuvs.rs";
        String body = "Telo 2 sa siuvs.rs";
        String image = "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2020/02/Google-Image-Search.jpg";
        String message = "Poruka 2 sa siuvs.rs";
        String link = "https://siuvs.rs/";
        String linkText = "Login strana na siuvs.rs";
         List<String> primaoci;
        primaoci =notifikacijeService.findDistinctByToken();
    
        String registration_ids = buildRegistrationIds(primaoci);
        String JSON_Body = buildJSONBody(title, body, image, message, link, linkText, registration_ids);
        try {
            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post.setEntity(requestEntity);
            HttpResponse rawResponse = httpclient.execute(post);
            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }

        return "redirect:/admin/mobileapp/slanje";
    }

    @PostMapping("/admin/mobileapp/slanje/posalji3")
    public String mobileappSlanjeNotifikacije3(final Model model,
            final RedirectAttributes redirectAttributes) {
        String title = "Naslov 3 sa siuvs.rs";
        String body = "Telo 3 sa siuvs.rs";
        String image = "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2020/02/Google-Image-Search.jpg";
        String message = "Poruka 3 sa siuvs.rs";
        String link = "https://siuvs.rs/";
        String linkText = "Login strana na siuvs.rs";
List<String> primaoci;
        primaoci =notifikacijeService.findDistinctByToken();
    
        String registration_ids = buildRegistrationIds(primaoci);
        String JSON_Body = buildJSONBody(title, body, image, message, link, linkText, registration_ids);
        try {
            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post.setEntity(requestEntity);
            HttpResponse rawResponse = httpclient.execute(post);
            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }

        return "redirect:/admin/mobileapp/slanje";
    }

//block used to demonstrate and test features across server app and mobileapp
    private String buildJSONBody(String title, String body, String image, String message, String link, String linkText, String registration_ids) {

        String JSONBody = "{\n"
                + "    \"data\": {\n"
                + "        \"title\" : \"" + title + "\", \n"
                + "        \"body\" : \"" + body + "\", \n"
                + "        \"image\" : \"" + image + "\",     \n"
                + "        \"message\": \"" + message + "\",\n"
                + "        \"link\": \"" + link + "\",\n"
                + "        \"linkText\": \"" + linkText + "\"    \n" + " "
                + "    },\n"
                + "    \"registration_ids\": [\n" + registration_ids
                + "    ]\n"
                + "}";

        return JSONBody;
    }

    private String buildRegistrationIds(List<String> primaoci) {

        String registration_ids = "\"";
        Iterator<String> iterator =primaoci.iterator();
          while (iterator.hasNext()) {
             String notif=iterator.next();
          //   registration_ids+=notif+"\",\n\"";
                 if (!iterator.hasNext()) {registration_ids+=notif+"\"";
            //last name 
        }   else {registration_ids+=notif+"\",\n\"";}    
        }
        return registration_ids;
    }
}
