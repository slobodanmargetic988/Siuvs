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
        String opstinanamelatinica=client.getOpstina().getNamelatinica();
          model.addAttribute("opstinanamelatinica",opstinanamelatinica);
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
        String opstina=client.getOpstina().getNamelatinica();
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
        return "redirect:/client/mobileapp";
    }
    
     @Autowired
    private VolonterService volonterService;
    
    @GetMapping("/admin/mobileapp/pregledVolontera")
    public String mobileappPregledVolontera(final Model model) {
List<Volonter> volonteri=volonterService.findAllBy();
 model.addAttribute("volonteri", volonteri);
        return "admin/mobileapp/pregledVolontera";
    }
        @GetMapping("/client/mobileapp/pregledVolontera")
    public String mobileappPregledVolonteraClient(final Model model) {
        
 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
List<Volonter> volonteri=volonterService.findAllByOpstina(client.getOpstina().getNamelatinica());
 model.addAttribute("volonteri", volonteri);
        return "client/mobileapp/pregledVolontera";
    }
    
    
    
     @Autowired
    private NotifikacijeService notifikacijeService;
    @GetMapping("/admin/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacije(final Model model) {
List<Notifikacije> notifikacije=notifikacijeService.findAllByOrderByOpstinaAsc();
 model.addAttribute("notifikacije", notifikacije);
        return "admin/mobileapp/prijavljeniZaNotifikacije";
    }
    

    @GetMapping("/client/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacijeClient(final Model model) {
           
 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
List<Notifikacije> notifikacije=notifikacijeService.findAllByOpstina(client.getOpstina());
 model.addAttribute("notifikacije", notifikacije);
        return "client/mobileapp/prijavljeniZaNotifikacije";
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
    
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,
            @RequestParam(name = "opstinanamelatinica", defaultValue = " ") String opstinanamelatinica,
            @RequestParam(name = "file",  required=false) MultipartFile file,
            final Model model,
            final RedirectAttributes redirectAttributes) {
  
         List<String> primaoci;
      
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient(); 
        

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
       if (client==null) {id=new ClientId(0);}
       else{id=client.getClientId();}
               String filename="";
          if (file.isEmpty()){
               imageTextV=" ";
              
  }
  else{
       
         try {
       
               filename = storageService.store(file,id);      
  imageTextV="https://siuvs.rs/php/getimg/{"+id.getValue()+"}/{"+filename+".}";               
            } catch (Exception e) {
               redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања слике!");             
            }
          }
         IstorijaNotifikacija istorijaNotifikacija=new IstorijaNotifikacija();
         istorijaNotifikacija.setTitle(titleTextV);
         istorijaNotifikacija.setBody(bodyTextV);
         istorijaNotifikacija.setMessage(messageTextV);
istorijaNotifikacija.setLink(linkTextV);
istorijaNotifikacija.setLink_text(linkTextV);
istorijaNotifikacija.setImg_file_name(filename);
istorijaNotifikacija.setClient(client);
istorijaNotifikacija.setCreatedBy(user);
istorijaNotifikacijaService.save(istorijaNotifikacija);
      


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
    
     @PostMapping("/client/mobileapp/slanje/posalji")
    public String mobileappSlanjeNotifikacijeClient(
            @RequestParam(name = "titleText", defaultValue = " ") String titleText,
            @RequestParam(name = "bodyText", defaultValue = " ") String bodyText,
            @RequestParam(name = "messageText", defaultValue = " ") String messageText,
        
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,  
             @RequestParam(name = "file",  required=false) MultipartFile file,
            final Model model,
            final RedirectAttributes redirectAttributes) {

         List<String> primaoci;
    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient(); 
       

        primaoci=notifikacijeService.findAllByOpstina(client.getOpstina().getNamelatinica());
        
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


        if (!messageText.equals("") && !messageText.equals(" ")) {
            messageTextV = messageText;
        }
        if (!linkText.equals("") && !linkText.equals(" ") && linkText.startsWith("http")) {
            linkTextV = linkText;
            if (!linkTextText.equals("") && !linkTextText.equals(" ")) {
                linkTextTextV = linkTextText;
            }
        }
        String filename="";
          if (file.isEmpty()){
               imageTextV=" ";
              
  }
  else{
  
 //this part goes in else
         try {
       
               filename = storageService.store(file,client.getClientId());                     
            } catch (Exception e) {
               redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом чувања слике!");             
            }
          imageTextV="https://siuvs.rs/php/getimg/{"+client.getClientId()+"}/{"+filename+".}";
          }
         IstorijaNotifikacija istorijaNotifikacija=new IstorijaNotifikacija();
         istorijaNotifikacija.setTitle(titleTextV);
         istorijaNotifikacija.setBody(bodyTextV);
         istorijaNotifikacija.setMessage(messageTextV);
istorijaNotifikacija.setLink(linkTextV);
istorijaNotifikacija.setLink_text(linkTextV);
istorijaNotifikacija.setImg_file_name(filename);
istorijaNotifikacija.setClient(client);
istorijaNotifikacija.setCreatedBy(user);
istorijaNotifikacijaService.save(istorijaNotifikacija);
         

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

        return "redirect:/client/mobileapp/slanje";

    }

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
    
    
    //block used to demonstrate and test features across server app and mobileapp


//block used to demonstrate and test features across server app and mobileapp
}
