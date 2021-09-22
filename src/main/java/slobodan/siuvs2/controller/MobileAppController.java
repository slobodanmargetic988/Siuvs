package slobodan.siuvs2.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import org.apache.http.util.EntityUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
import slobodan.siuvs2.model.NotifikacijeIos;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.VolonterIos;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.IstorijaNotifikacijaService;
import slobodan.siuvs2.service.MobileAppUniqIosService;
import slobodan.siuvs2.service.MobileAppUniqService;
import slobodan.siuvs2.service.MobileappdataFactory;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.service.NotifikacijeIosService;
import slobodan.siuvs2.service.NotifikacijeService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.VolonterIosService;
import slobodan.siuvs2.service.VolonterService;
import slobodan.siuvs2.valueObject.ClientId;

@Controller
public class MobileAppController {

    private String sviServisi = "Svi servisi";
    private Integer howManyTimes = 2;// how many times notifications are resent to ensure delivery resend obavestenja obaveštenja
    @Autowired
    private UserService userService;
    @Autowired
    private IstorijaNotifikacijaService istorijaNotifikacijaService;

    @Autowired
    private StorageService storageService;
    @Autowired
    private PhotoService photoService;

     
    /*public String mobileappPrijavljeniZaNotifikacijeClient(final Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        model.addAttribute("BrojPrijavljenihZaServis", notifikacijeService.countByOpstina(client.getOpstina().getNamelatinica()) + notifikacijeIosService.countByOpstina(client.getOpstina().getNamelatinica()));
        model.addAttribute("BrojPrijavljenihZaSveServise", notifikacijeService.countByOpstina(sviServisi));
        return "client/mobileapp/prijavljeniZaNotifikacije";
    }*/
    @GetMapping("/clearbadtokens")
     public String DryRun(){
        List<String> primaoci = new ArrayList();
        List<String> primaociIos = new ArrayList();
        primaoci = notifikacijeService.findDistinctByToken(); ///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing
        primaociIos = notifikacijeIosService.findDistinctByToken();
        List<String> primaociSvi = primaoci;
        primaociSvi.addAll(primaociIos);
        List<String> primaociLosi = new ArrayList();

        while (!primaociSvi.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociSvi.size()) {
                chunksize = primaociSvi.size();

            }
            List<String> primaociDeo = primaociSvi.subList(0, chunksize);

            try {
                JSONArray jsonRegistrationIdsArray = new JSONArray();

                Iterator<String> iterator = primaociDeo.iterator();
                while (iterator.hasNext()) {
                    jsonRegistrationIdsArray.put(iterator.next());
                }
                primaociSvi.removeAll(primaociDeo);

                JSONObject jsonPoruka = new JSONObject();
                jsonPoruka.put("dry_run", true);
                jsonPoruka.put("registration_ids", jsonRegistrationIdsArray);

                HttpClient httpclient = HttpClients.createDefault();
                StringEntity requestEntity = new StringEntity(jsonPoruka.toString(), ContentType.APPLICATION_JSON);

                String HOST = "https://fcm.googleapis.com/fcm/send";
                HttpPost post = new HttpPost(HOST);
                post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
                //  post.setHeader("Content-Length","0");
                post.setEntity(requestEntity);

                HttpResponse rawResponse = httpclient.execute(post);

                String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
               // System.out.println();
               // System.out.println("odgovor od googla za test je      " + message);
               // System.out.println("za idjeve: " + jsonRegistrationIdsArray.toString());
 //System.out.println("losi tokeni");
                JSONObject jsonOdgovor = new JSONObject(message);
                JSONArray jsonOdgovoriZaDeo = jsonOdgovor.getJSONArray("results");

                for (int i = 0; i < jsonOdgovoriZaDeo.length(); i++) {
                    if (jsonOdgovoriZaDeo.getJSONObject(i).has("error")) {
                        if (jsonOdgovoriZaDeo.getJSONObject(i).getString("error").equals("NotRegistered")) {
                           // System.out.println("\""+ jsonRegistrationIdsArray.get(i)+"\",");
                            primaociLosi.add(jsonRegistrationIdsArray.get(i).toString());
                        }
                    }
                }

            } catch (Exception e) {

            }

        }
             System.out.println(primaociLosi.toString());
             notifikacijeService.deleteByTokenIn(primaociLosi);
              notifikacijeIosService.deleteByTokenIn(primaociLosi);
             System.out.println("primaociLosiobrisani komada: "+primaociLosi.size());
             
     return "redirect:/admin";
     }
    
    
    
    private String buildJSONBody(IstorijaNotifikacija istorijaNotifikacija, String title, String body, String image, String message, String link, String linkText, List<String> primaoci, String posiljalac) {
        JSONObject jsonPoruka = new JSONObject();
        JSONObject jsonData = new JSONObject();
        JSONArray jsonRegistrationIdsArray = new JSONArray();
        try {//build data json
            jsonData.put("title", title);
            jsonData.put("body", body);
            jsonData.put("image", image);
            jsonData.put("message", message);
            jsonData.put("link", link);
            jsonData.put("linkText", linkText);
            jsonData.put("serverNotificationId", istorijaNotifikacija.getId());
            jsonData.put("posiljalac", posiljalac);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            jsonData.put("datum", LocalDate.now().format(dateFormat));
//build registration ids json
            Iterator<String> iterator = primaoci.iterator();
            while (iterator.hasNext()) {
                jsonRegistrationIdsArray.put(iterator.next());
            }
//build payload json
            jsonPoruka.put("data", jsonData);
            jsonPoruka.put("registration_ids", jsonRegistrationIdsArray);
        } catch (Exception e) {
            System.out.println(e);
        }
        String payload = jsonPoruka.toString();
        return payload;
    }

    private String buildJSONBodyIOS(IstorijaNotifikacija istorijaNotifikacija, String title, String body, String image, String message, String link, String linkText, List<String> primaoci, String posiljalac) {
        JSONObject jsonPoruka2 = new JSONObject();
        JSONObject jsonNotification2 = new JSONObject();
        JSONObject jsonData2 = new JSONObject();
        JSONArray jsonRegistrationIdsArray2 = new JSONArray();
        try {//build data json
            jsonNotification2.put("title", title);
            jsonNotification2.put("body", body);
            jsonNotification2.put("badge", 0);
            jsonNotification2.put("sound", "default");
            jsonNotification2.put("click_action", "READABLE");
            jsonNotification2.put("content_available", false);
            jsonNotification2.put("priority", "high");

            jsonData2.put("title", title);
            jsonData2.put("body", body);
            jsonData2.put("image", image);
            jsonData2.put("message", message);
            jsonData2.put("link", link);
            jsonData2.put("linkText", linkText);
            jsonData2.put("serverNotificationId", istorijaNotifikacija.getId());
            jsonData2.put("posiljalac", posiljalac);
            DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            jsonData2.put("datum", LocalDate.now().format(dateFormat2));
//build registration ids json
            Iterator<String> iterator = primaoci.iterator();
            while (iterator.hasNext()) {
                jsonRegistrationIdsArray2.put(iterator.next());
            }
//build payload json
            jsonPoruka2.put("notification", jsonNotification2);
            jsonPoruka2.put("data", jsonData2);
            jsonPoruka2.put("registration_ids", jsonRegistrationIdsArray2);

            /*     System.out.println();
              System.out.println(jsonPoruka2.toString());
              System.out.println();
             */
        } catch (Exception e) {
            System.out.println(e);
        }
        String payload2 = jsonPoruka2.toString();
        return payload2;
    }

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private void repeatNotificationPostList(Integer howManyTimes, List<HttpPost> sviPostovi) {

        final Runnable beeper = new Runnable() {
            public void run() {
                //System.out.println("sending notification on timer test");

                HttpClient httpclient = HttpClients.createDefault();
                try {
                    for (HttpPost post : sviPostovi) {
                        HttpResponse rawResponse = httpclient.execute(post);
                    }
                    // System.out.println(rawResponse);
                } catch (Exception e) {
                }
            }
        };

        Integer initialDelay = 15;//60 seconds*20 =minutes initial delay
        Integer periodBetweenSending = 18;//60 seconds*20 =minutes period Between Sending
        Integer runForHowLong = 15 * howManyTimes;//60 seconds*20*howManyTimes =how long will the thread run, also howManyTimes= basicaly equals how many times will sending execute
          final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, initialDelay, periodBetweenSending, SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, runForHowLong, SECONDS);
           //ako ponavljamo vise puta

      //  scheduler.schedule(beeper, runForHowLong, SECONDS);//ako ponavljamo samo jednom
    }
    
    
    private void repeatNotificationSingle(Integer howManyTimes, HttpPost post) {

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

        Integer initialDelay = (int) (Math.round(150*Math.random()));//60 seconds*20 =minutes initial delay
        Integer periodBetweenSending = 18;//60 seconds*20 =minutes period Between Sending
        Integer runForHowLong = initialDelay+periodBetweenSending * howManyTimes+2;//60 seconds*20*howManyTimes =how long will the thread run, also howManyTimes= basicaly equals how many times will sending execute
          final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, initialDelay, periodBetweenSending, SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, runForHowLong, SECONDS);
           //ako ponavljamo vise puta

      //  scheduler.schedule(beeper, runForHowLong, SECONDS);//ako ponavljamo samo jednom
    }
    /*
    private static String getAccessToken() {
        try{
         File initialFile = new File("service-account.json");
         FileInputStream fio = new FileInputStream(initialFile);
          int i; 
             System.out.println("sadrzaj fajla je ");
            while ((i = fio.read()) != -1) {
                System.out.print((char) i);
            }
 
  GoogleCredentials googleCredentials = GoogleCredentials
          
          .fromStream(fio)
          .createScoped(Arrays.asList(
"https://www.googleapis.com/auth/firebase.messaging"));
  googleCredentials.refreshAccessToken();
        
  return googleCredentials.getAccessToken().getTokenValue();}
        catch(Exception e){  System.out.println("error message   "+e.getMessage());}
         return "";
}*/

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

        List<HttpPost> sviPostovi = new ArrayList();

        List<String> primaoci = new ArrayList();
        List<String> primaociIos = new ArrayList();;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        if (opstinanamelatinica.equals(sviServisi)) {
            primaoci = notifikacijeService.findDistinctByToken(); ///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing
            primaociIos = notifikacijeIosService.findDistinctByToken();
        } else {
            primaoci = notifikacijeService.findAllByOpstina(opstinanamelatinica);  ///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing///////////////disabled for testing
            primaociIos = notifikacijeIosService.findAllByOpstina(opstinanamelatinica);
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

        List<String> primaociPravi = new ArrayList();
        primaociPravi.addAll(primaoci);

        //  System.out.println("broj primaoca je :" + primaociPravi.size());
        //System.out.println("send android notifications in batches");
        //send android notifications in batches
        while (!primaociPravi.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPravi.size()) {
                chunksize = primaociPravi.size();
                //   System.out.println("chunksize je :" + chunksize);
            }
            List<String> primaociDeo = primaociPravi.subList(0, chunksize);
            // primaociPravi.removeAll(primaociDeo);

            String JSON_Body = buildJSONBody(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeo, opstinanamelatinica);
            primaociPravi.removeAll(primaociDeo);

            //    System.out.println("body :" + JSON_Body);
            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            //  post.setHeader("Content-Length","0");
            post.setEntity(requestEntity);
            try {

                HttpResponse rawResponse = httpclient.execute(post);
                sviPostovi.add(post);
               repeatNotificationSingle( howManyTimes,  post);
              
               
               /*String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
                System.out.println();
                System.out.println("odgovor od googla za ANDROID je      " + message);    
                System.out.println();*/
                
                redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!" + e);
            }
            //repeat notification 5 times every 20 minutes

            //  repeatNotification(howManyTimes, post);
        }
        //System.out.println("send ios notifications in batches");

        //send ios notifications in batches
        List<String> primaociPraviIOS = new ArrayList();
        primaociPraviIOS.addAll(primaociIos);
        //  System.out.println("broj primaoca je :" + primaociPraviIOS.size());
        while (!primaociPraviIOS.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPraviIOS.size()) {
                chunksize = primaociPraviIOS.size();
                //  System.out.println("chunksize je :" + chunksize);
            }
            List<String> primaociDeoIOS = primaociPraviIOS.subList(0, chunksize);

            //  primaociPraviIOS.removeAll(primaociDeoIOS);
            String JSON_Body1 = buildJSONBodyIOS(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeoIOS, opstinanamelatinica);
            System.out.println("body za ios :" + JSON_Body1);
            primaociPraviIOS.removeAll(primaociDeoIOS);

            HttpClient httpclient1 = HttpClients.createDefault();  
          //  HttpClient httpclient2 = HttpClients.createDefault();
            
            StringEntity requestEntity1 = new StringEntity(JSON_Body1, ContentType.APPLICATION_JSON);
            String HOST1 = "https://fcm.googleapis.com/fcm/send";
          //  String HOST2 = "https://fcm.googleapis.com//v1/projects/siuvs-mobile-app/messages:send";
            HttpPost post1 = new HttpPost(HOST1);
          //  HttpPost post2 = new HttpPost(HOST1);
            post1.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
           
            
            //      post1.setHeader("Content-Length","0");
            post1.setEntity(requestEntity1);
      //           post2.setEntity(requestEntity1);
            try {
               //  System.out.println("pokusaj dobijanja tokena");
              //  String authtoken=getAccessToken();
          //   System.out.println("pokusaj dobijanja tokena token je   "+authtoken);
         //  post2.setHeader("Authorization", "Bearer "+authtoken);
           
           
                HttpResponse rawResponse = httpclient1.execute(post1);
                sviPostovi.add(post1);
                repeatNotificationSingle( 2,  post1);
 //HttpResponse rawResponse2 = httpclient2.execute(post1);
     //           sviPostovi.add(post2);
    //            System.out.println();
                String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
        //          String message2 = EntityUtils.toString(rawResponse2.getEntity(), "UTF-8");
                  
                  
           //     System.out.println("odgovor od googla za IOS je      " + message);
   //             System.out.println();
     //           System.out.println();
     //           System.out.println();
                
//System.out.println("odgovor od googla za IOS v2 je      " + message2);
                System.out.println();
                redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!" + e.getMessage());
            }
            //repeat notification 5 times every 20 minutes

        }
       // repeatNotificationPostList(howManyTimes, sviPostovi);
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
        List<HttpPost> sviPostovi = new ArrayList();

        List<String> primaoci;
        List<String> primaociIos;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        primaoci = notifikacijeService.findAllByOpstina(client.getOpstina().getNamelatinica());
        primaociIos = notifikacijeIosService.findAllByOpstina(client.getOpstina().getNamelatinica());
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

        List<String> primaociPravi = new ArrayList();
        primaociPravi.addAll(primaoci);
        //  primaociPravi.addAll(primaociIos);

//send android notifications
        while (!primaociPravi.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPravi.size()) {
                chunksize = primaociPravi.size();
            }
            List<String> primaociDeo = primaociPravi.subList(0, chunksize);
            //   primaociPravi.removeAll(primaociDeo);

            String JSON_Body = buildJSONBody(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeo, client.getOpstina().getNamelatinica());
            //java.util.concurrentmodificationexception ako se ovo obrise pre nego sto se primaoci deo iskoristi u buildjsonbody
            primaociPravi.removeAll(primaociDeo);

            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post.setEntity(requestEntity);
            try {
                HttpResponse rawResponse = httpclient.execute(post);
                sviPostovi.add(post);
 repeatNotificationSingle( howManyTimes,  post);
                  String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
                System.out.println();
                System.out.println("odgovor od googla za ANDROID je      " + message);    
                System.out.println();
                redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
            }
            //repeat notification 5 times every 20 minutes

            //   repeatNotification(howManyTimes, post);
        }

        //send ios notifications
        List<String> primaociPraviIos = new ArrayList();
        primaociPravi.addAll(primaociIos);
        while (!primaociPraviIos.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPraviIos.size()) {
                chunksize = primaociPraviIos.size();
            }
            List<String> primaociDeo = primaociPraviIos.subList(0, chunksize);

            String JSON_Body = buildJSONBodyIOS(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeo, client.getOpstina().getNamelatinica());
            primaociPraviIos.removeAll(primaociDeo);

            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post1 = new HttpPost(HOST);
            post1.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post1.setEntity(requestEntity);
            try {
                HttpResponse rawResponse = httpclient.execute(post1);
                sviPostovi.add(post1);
  repeatNotificationSingle( 2,  post1);
                   String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
                System.out.println();
                System.out.println("odgovor od googla za IOS je      " + message);    
                System.out.println();
                redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
            }
            //repeat notification 5 times every 20 minutes

            //    repeatNotification(howManyTimes, post1);
        }
     //   repeatNotificationPostList(howManyTimes, sviPostovi);
        return "redirect:/client/mobileapp/slanje";

    }

    @PostMapping("/mobileonly/slanje/posalji")
    public String mobilonlySlanjeNotifikacije(
            @RequestParam(name = "titleText", defaultValue = " ") String titleText,
            @RequestParam(name = "bodyText", defaultValue = " ") String bodyText,
            @RequestParam(name = "messageText", defaultValue = " ") String messageText,
            @RequestParam(name = "linkText", defaultValue = " ") String linkText,
            @RequestParam(name = "linkTextText", defaultValue = " ") String linkTextText,
            @RequestParam(name = "opstinanamelatinica", defaultValue = " ") String opstinanamelatinica,
            @RequestParam(name = "file", required = false) MultipartFile file,
            final Model model,
            final RedirectAttributes redirectAttributes) {
        List<HttpPost> sviPostovi = new ArrayList();

        List<String> primaoci;
        List<String> primaociIos;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        primaoci = notifikacijeService.findAllByOpstina(opstinanamelatinica);
        primaociIos = notifikacijeIosService.findAllByOpstina(opstinanamelatinica);

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

        List<String> primaociPravi = new ArrayList();
        primaociPravi.addAll(primaoci);
        
 List<String> primaociPraviIOS = new ArrayList();
        primaociPraviIOS.addAll(primaociIos);
        
        //  System.out.println("broj primaoca je :" + primaociPraviIOS.size());
        while (!primaociPraviIOS.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPraviIOS.size()) {
                chunksize = primaociPraviIOS.size();
                //  System.out.println("chunksize je :" + chunksize);
            }
            List<String> primaociDeoIOS = primaociPraviIOS.subList(0, chunksize);

            //  primaociPraviIOS.removeAll(primaociDeoIOS);
            String JSON_Body1 = buildJSONBodyIOS(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeoIOS, opstinanamelatinica);

            primaociPraviIOS.removeAll(primaociDeoIOS);

            HttpClient httpclient1 = HttpClients.createDefault();
            StringEntity requestEntity1 = new StringEntity(JSON_Body1, ContentType.APPLICATION_JSON);
            String HOST1 = "https://fcm.googleapis.com/fcm/send";
            HttpPost post1 = new HttpPost(HOST1);
            post1.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            //      post1.setHeader("Content-Length","0");
            post1.setEntity(requestEntity1);
            try {
                HttpResponse rawResponse = httpclient1.execute(post1);
                sviPostovi.add(post1);
  repeatNotificationSingle( 2,  post1);
                   String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
            
                   /*System.out.println();
                System.out.println("odgovor od googla za IOS je      " + message);    
                System.out.println();*/
                //System.out.println("odgovor od googla je      "+rawResponse);
//String odgovorContent=rawResponse.getEntity().getContent().toString();
                //   System.out.println("odgovor od googla je      "+odgovorContent);
                //    System.out.println("sta on ovde u odgovoru cita");
                redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!" + e.getMessage());
            }
            //repeat notification 5 times every 20 minutes

            // repeatNotification(howManyTimes, post1);
        }
        //  System.out.println("broj primaoca je :" + primaociPravi.size());
        //System.out.println("send android notifications in batches");
        //send android notifications in batches
        while (!primaociPravi.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPravi.size()) {
                chunksize = primaociPravi.size();
                //   System.out.println("chunksize je :" + chunksize);
            }
            List<String> primaociDeo = primaociPravi.subList(0, chunksize);
            // primaociPravi.removeAll(primaociDeo);

            String JSON_Body = buildJSONBody(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeo, opstinanamelatinica);
            primaociPravi.removeAll(primaociDeo);

            System.out.println("body :" + JSON_Body);

            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            //  post.setHeader("Content-Length","0");
            post.setEntity(requestEntity);
            try {

                HttpResponse rawResponse = httpclient.execute(post);
                sviPostovi.add(post);
 repeatNotificationSingle( howManyTimes,  post);
                String message = EntityUtils.toString(rawResponse.getEntity(), "UTF-8");
                System.out.println();
                System.out.println("odgovor od googla za ANDROID je      " + message);    
                System.out.println();

                redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!" + e);
            }
            //repeat notification 5 times every 20 minutes

            //  repeatNotification(howManyTimes, post);
        }
        //System.out.println("send ios notifications in batches");

        //send ios notifications in batches
       
       // repeatNotificationPostList(howManyTimes, sviPostovi);
        return "redirect:/mobileonly/slanje";

    }
     /*
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

        List<HttpPost> sviPostovi = new ArrayList();
        List<String> primaociIos;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        primaociIos = notifikacijeIosService.findDistinctByToken();

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

        //send ios notifications in batches
        List<String> primaociPraviIOS = new ArrayList();
        primaociPraviIOS.addAll(primaociIos);
        //  System.out.println("broj primaoca je :" + primaociPraviIOS.size());
        while (!primaociPraviIOS.isEmpty()) {
            Integer chunksize = 800;
            if (chunksize > primaociPraviIOS.size()) {
                chunksize = primaociPraviIOS.size();
                //  System.out.println("chunksize je :" + chunksize);
            }
            List<String> primaociDeoIOS = primaociPraviIOS.subList(0, chunksize);

            //  primaociPraviIOS.removeAll(primaociDeoIOS);
            String JSON_Body1 = buildJSONBodyIOS(istorijaNotifikacija, titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, primaociDeoIOS, "Ada");

            primaociPraviIOS.removeAll(primaociDeoIOS);

            HttpClient httpclient1 = HttpClients.createDefault();
            StringEntity requestEntity1 = new StringEntity(JSON_Body1, ContentType.APPLICATION_JSON);
            String HOST1 = "https://fcm.googleapis.com/fcm/send";
            HttpPost post1 = new HttpPost(HOST1);
            post1.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            //      post1.setHeader("Content-Length","0");
            post1.setEntity(requestEntity1);
            try {
                HttpResponse rawResponse = httpclient1.execute(post1);
                sviPostovi.add(post1);
                //System.out.println("odgovor od googla je      "+rawResponse);
                String odgovorContent = rawResponse.getEntity().getContent().toString();
                System.out.println("odgovor od googla je      " + odgovorContent);
                //    System.out.println("sta on ovde u odgovoru cita");
         */ 
    
    //    redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата! \n " /*+ rawResponse*/);
/*
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!" + e.getMessage());
            }
            //repeat notification 5 times every 20 minutes

            //  
        }
        repeatNotificationPostList(howManyTimes, sviPostovi);
        return "redirect:/admin/mobileapp/slanje";

    }
*/
    
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

    @GetMapping("/mobileonly/slanje")
    public String mobileappOnlySlanje(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        model.addAttribute("servis", user.getMobileonlyservis());
        return "mobileonly/slanje";
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
    @Autowired
    private VolonterIosService volonterIosService;

    @GetMapping("/admin/mobileapp/pregledVolontera")
    public String mobileappPregledVolontera(final Model model) {
        List<Volonter> volonteri = volonterService.findAllBy();
        List<VolonterIos> volonteriIos = volonterIosService.findAllBy();

        model.addAttribute("volonteri", volonteri);
        model.addAttribute("volonteriIOS", volonteriIos);
        return "admin/mobileapp/pregledVolontera";
    }

    @GetMapping("/client/mobileapp/pregledVolontera")
    public String mobileappPregledVolonteraClient(final Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        List<Volonter> volonteri = volonterService.findAllByOpstina(client.getOpstina().getNamelatinica());

        List<VolonterIos> volonteriIos = volonterIosService.findAllByOpstina(client.getOpstina().getNamelatinica());

        model.addAttribute("volonteri", volonteri);
        model.addAttribute("volonteriIOS", volonteriIos);
        return "client/mobileapp/pregledVolontera";
    }

    @Autowired
    private NotifikacijeService notifikacijeService;
    @Autowired
    private NotifikacijeIosService notifikacijeIosService;
    @Autowired
    private MobileAppUniqService mobileAppUniqService;
    @Autowired
    private MobileAppUniqIosService mobileAppUniqIosService;

    @GetMapping("/admin/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacije(final Model model) {
        List<Notifikacije> notifikacije = notifikacijeService.findAllByOrderByOpstinaAsc();
        int UkupnoBrojKorisnika = 0;

        LinkedHashMap< String, Integer> hm2 = new LinkedHashMap<String, Integer>();
        //  hm2.put(notifikacije.get(0).getOpstina(), 1);

        for (Notifikacije notifikacija : notifikacije) {
            UkupnoBrojKorisnika++;

            hm2.merge(notifikacija.getOpstina(), 1, Integer::sum);

            /*  if (hm2.containsKey(notifikacija.getOpstina())){

        hm2.replace(notifikacija.getOpstina(), hm2.get(notifikacija.getOpstina())+1);

        }
        {
          hm2.put(notifikacija.getOpstina(), 1);
        }*/
        }
        List<NotifikacijeIos> notifikacijeIOS = notifikacijeIosService.findAllByOrderByOpstinaAsc();
        for (NotifikacijeIos notifikacija : notifikacijeIOS) {
            UkupnoBrojKorisnika++;
            hm2.merge(notifikacija.getOpstina(), 1, Integer::sum);
        }

        System.out.println(hm2.toString());
        model.addAttribute("SviServisiBrojKorisnika", hm2.get(sviServisi));

        model.addAttribute("UkupnoBrojKorisnika", UkupnoBrojKorisnika);
        hm2.remove(sviServisi);//sklanjamo ga iz liste jer ga prikazujemo odvojeno
        model.addAttribute("notifikacije", hm2);
        model.addAttribute("UkupnoBrojKorisnikaSistemski", mobileAppUniqService.count());
        return "admin/mobileapp/prijavljeniZaNotifikacije";
    }

    //MojObjekat mojobjekat
    @GetMapping("/client/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacijeClient(final Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        model.addAttribute("BrojPrijavljenihZaServis", notifikacijeService.countByOpstina(client.getOpstina().getNamelatinica()) + notifikacijeIosService.countByOpstina(client.getOpstina().getNamelatinica()));
        model.addAttribute("BrojPrijavljenihZaSveServise", notifikacijeService.countByOpstina(sviServisi));
        return "client/mobileapp/prijavljeniZaNotifikacije";
    }

    @GetMapping("/mobileonly/prijavljeniZaNotifikacije")
    public String mobileOnlyPrijavljeniZaNotifikacije(final Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();

        model.addAttribute("BrojPrijavljenihZaServis", notifikacijeService.countByOpstina(user.getMobileonlyservis()));
        model.addAttribute("BrojPrijavljenihZaSveServise", notifikacijeService.countByOpstina(sviServisi));

        return "mobileonly/prijavljeniZaNotifikacije";
    }

    

    @GetMapping("/admin/mobileapp/istorijaNotifikacija")
    public String mobileappIstorijaNotifikacija(final Model model) {
        model.addAttribute("allclients", clientService.findAllByOrderByNameAsc());
        List<IstorijaNotifikacija> istorijaNotifikacija = istorijaNotifikacijaService.findAllBy();
        model.addAttribute("notifikacije", istorijaNotifikacija);
        return "admin/mobileapp/istorijaNotifikacija";
    }

    @GetMapping("/admin/mobileapp/istorijaNotifikacijaZaOpstinu/{clientId}")
    public String mobileappIstorijaNotifikacijaZaOpstinu(final Model model,
            @PathVariable final ClientId clientId) {
        Client client = clientService.findOne(clientId);
        List<IstorijaNotifikacija> istorijaNotifikacija = istorijaNotifikacijaService.findAllByClient(client);;
        model.addAttribute("notifikacije", istorijaNotifikacija);
        model.addAttribute("poslatoUprethodnomMesecu", istorijaNotifikacijaService.countLastMonthPoslateForClientID(client.getId()));

        return "admin/mobileapp/istorijaNotifikacijaZaOpstinu";
    }

    @GetMapping("/client/mobileapp/istorijaNotifikacija")
    public String mobileappIstorijaNotifikacijaClient(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        List<IstorijaNotifikacija> istorijaNotifikacija = istorijaNotifikacijaService.findAllByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("notifikacije", istorijaNotifikacija);
        model.addAttribute("poslatoUprethodnomMesecu", istorijaNotifikacijaService.countLastMonthPoslateForClientID(client.getId()));
        return "client/mobileapp/istorijaNotifikacija";
    }

    @GetMapping("/mobileonly/istorijaNotifikacija")
    public String mobileonlyIstorijaNotifikacija(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();

        List<User> useriistogservisa = userService.findAllByMobileonlyservis(user.getMobileonlyservis());

        List<IstorijaNotifikacija> istorijaNotifikacija = istorijaNotifikacijaService.findAllByCreatedByIn(useriistogservisa);

        model.addAttribute("notifikacije", istorijaNotifikacija);
        model.addAttribute("user", user);
        // model.addAttribute("poslatoUprethodnomMesecu", istorijaNotifikacijaService.countLastMonthPoslateForClientID(client.getId()));

        return "mobileonly/istorijaNotifikacija";
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

    @GetMapping("/mobileonly/pregledNotifikacije/{id}")
    public String mobileonlyPregledNotifikacije(
            @PathVariable final Integer id,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        try {
            IstorijaNotifikacija notifikacija = istorijaNotifikacijaService.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("notifikacija", notifikacija);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "mobileonly/pregledNotifikacije";
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
   

}
