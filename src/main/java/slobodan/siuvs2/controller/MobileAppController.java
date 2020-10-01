package slobodan.siuvs2.controller;

import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "admin/mobileapp/slanje";
    }

    @GetMapping("/admin/mobileapp/izmenaProcena")
    public String mobileappIzmenaProcena(final Model model) {

        return "admin/mobileapp/izmenaProcena";
    }

    @GetMapping("/admin/mobileapp/pregledVolontera")
    public String mobileappPregledVolontera(final Model model) {

        return "admin/mobileapp/pregledVolontera";
    }

    @GetMapping("/admin/mobileapp/prijavljeniZaNotifikacije")
    public String mobileappPrijavljeniZaNotifikacije(final Model model) {

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
            final Model model,
            final RedirectAttributes redirectAttributes) {

        String registration_ids="\"fOa1rkFpQpuW782aUdYr7q:APA91bFhzOIZ3P5-Uj9pBygwY_PiZeBtlIAH8Qm1jQuCghuF2rCSgMzUEMeUHEI2yISLYjhHkLDnbMYkgM6QbDn8lQBWCichlcieq2qB693CZ2IN1zGvvcWJHh-W7qvrTpHc-BUWE0Vu\"";  
    /* 
        String registration_ids="\"fOa1rkFpQpuW782aUdYr7q:APA91bFhzOIZ3P5-Uj9pBygwY_PiZeBtlIAH8Qm1jQuCghuF2rCSgMzUEMeUHEI2yISLYjhHkLDnbMYkgM6QbDn8lQBWCichlcieq2qB693CZ2IN1zGvvcWJHh-W7qvrTpHc-BUWE0Vu\", "
                + "\"drjPO26gRBCV18CQXx7qvR:APA91bG82mDMwPY1dRIC9uxVfYT6-GmsRDPdR92weIWehbBY12pvI_zmLUISty_Tn8OtkKcxJeXY-TdXkcqvBlCnUKlpUek5xyEq0mbaEXJ5u1JB_3eUNczw-bFefZ8GPP-3ZF9eKpiS\",\n" +
"    \"cCYDaFsFSReDR9pw5zlBrj:APA91bG2FoekMgpcuZeYRTeBYXIbBfCRcAP5oPyhONmDBXXmIkm8P_oXftBMaA8B8AnZukX2RkvA6t5pdRMG82kfW_P2KPaoTo5M_HSeREApan6U4psQ8ZqUHtT2W4L-F586r2BaPWrS\",\n" +
"    \"fisUgnh5RW2vnN4MJCx0mH:APA91bHXlm33Xe349UPKuqnBuXKOEvcT7JzHsOgPe1wXugmOMJpSzBsfkjpiVtfJgQi8JWaoXB7PnmhnznN_iTyQ0JPQsQhfihw97QzVls87LraSdekCPDdde6ARf9RGQWAny_g38pyM\",\n" +
"    \"eAvyOtIYTU6PBWZhL51sxK:APA91bHirr_1-qqP3nej3NtG42r-72ihpPigKJlOQ464waoyzIxjObmEhrWj2FRGO9YZUyo6HcrmXEhB1ipBC-yuV1VJHzHNNQvNGyHko6w0CbYggZXv4DmDfHPCq1JTGG8mBVWhpsc2\"";
      */         
     String titleTextV=" ";
     String bodyTextV=" ";
     String imageTextV=" ";
     String messageTextV=" ";
     String linkTextV=" ";
     String linkTextTextV=" ";
     
     if (!titleText.equals("") || !titleText.equals(" ")){titleTextV=titleText;}

    if (!bodyText.equals("") || !bodyText.equals(" ")){bodyTextV=bodyText;}
    
    if (!imageText.equals("") || !imageText.equals(" ")||imageText.startsWith("http")){imageTextV=imageText;}
    if (!messageText.equals("") || !messageText.equals(" ")){messageTextV=messageText;}
    if (!linkText.equals("") | !linkText.equals(" ")||linkText.startsWith("http") ){linkTextV=linkText;}
  
      if (!linkTextText.equals("") || !linkTextText.equals(" ")){linkTextTextV=linkTextText;}
    
    
        String JSON_Body =buildJSONBody( titleTextV, bodyTextV, imageTextV, messageTextV, linkTextV, linkTextTextV, registration_ids);
        try {
            HttpClient httpclient = HttpClients.createDefault();
            StringEntity requestEntity = new StringEntity(JSON_Body, ContentType.APPLICATION_JSON);
            String HOST = "https://fcm.googleapis.com/fcm/send";
            HttpPost post = new HttpPost(HOST);
            post.setHeader("Authorization", "key=AAAAxbbCok8:APA91bGMZcat_HhLBi5lcx_k0NBLfNcEGDBj8HAyY6GNRaCIggaDqw-tqpn4yGeagxUojem408qkbkUbTZK6mt0TpFsGp56gGj-pvFGbpxtwkgjCuh8o2Y-2LFMjOFm203DDieSA1CI8");
            post.setEntity(requestEntity);
            HttpResponse rawResponse = httpclient.execute(post);

            redirectAttributes.addFlashAttribute("successMessage", "Нотификација успешно послата!"+rawResponse.toString());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом слања нотификације!");
        }



        return "redirect:/admin/mobileapp/slanje";

    }
//block used to demonstrate and test features across server app and mobileapp

    @PostMapping("/admin/mobileapp/slanje/posalji1")
    public String mobileappSlanjeNotifikacije1(final Model model,
            final RedirectAttributes redirectAttributes) {
        String title="Naslov 1 sa siuvs.rs";
        String body="Telo 1 sa siuvs.rs";
        String image="https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2020/02/Google-Image-Search.jpg";
        String message="Poruka 1 sa siuvs.rs";
        String link="https://siuvs.rs/";
        String linkText="Login strana na siuvs.rs";

        String registration_ids="\"fOa1rkFpQpuW782aUdYr7q:APA91bFhzOIZ3P5-Uj9pBygwY_PiZeBtlIAH8Qm1jQuCghuF2rCSgMzUEMeUHEI2yISLYjhHkLDnbMYkgM6QbDn8lQBWCichlcieq2qB693CZ2IN1zGvvcWJHh-W7qvrTpHc-BUWE0Vu\", "
                + "\"drjPO26gRBCV18CQXx7qvR:APA91bG82mDMwPY1dRIC9uxVfYT6-GmsRDPdR92weIWehbBY12pvI_zmLUISty_Tn8OtkKcxJeXY-TdXkcqvBlCnUKlpUek5xyEq0mbaEXJ5u1JB_3eUNczw-bFefZ8GPP-3ZF9eKpiS\",\n" +
"    \"cCYDaFsFSReDR9pw5zlBrj:APA91bG2FoekMgpcuZeYRTeBYXIbBfCRcAP5oPyhONmDBXXmIkm8P_oXftBMaA8B8AnZukX2RkvA6t5pdRMG82kfW_P2KPaoTo5M_HSeREApan6U4psQ8ZqUHtT2W4L-F586r2BaPWrS\",\n" +
"    \"fisUgnh5RW2vnN4MJCx0mH:APA91bHXlm33Xe349UPKuqnBuXKOEvcT7JzHsOgPe1wXugmOMJpSzBsfkjpiVtfJgQi8JWaoXB7PnmhnznN_iTyQ0JPQsQhfihw97QzVls87LraSdekCPDdde6ARf9RGQWAny_g38pyM\",\n" +
"    \"eAvyOtIYTU6PBWZhL51sxK:APA91bHirr_1-qqP3nej3NtG42r-72ihpPigKJlOQ464waoyzIxjObmEhrWj2FRGO9YZUyo6HcrmXEhB1ipBC-yuV1VJHzHNNQvNGyHko6w0CbYggZXv4DmDfHPCq1JTGG8mBVWhpsc2\"";
              
        String JSON_Body =buildJSONBody( title, body, image, message, link, linkText, registration_ids);
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
  String title="Naslov 1 sa siuvs.rs";
        String body="Telo 1 sa siuvs.rs";
        String image="https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2020/02/Google-Image-Search.jpg";
        String message="Poruka 1 sa siuvs.rs";
        String link="https://siuvs.rs/";
        String linkText="Login strana na siuvs.rs";
        String registration_ids="\"fOa1rkFpQpuW782aUdYr7q:APA91bFhzOIZ3P5-Uj9pBygwY_PiZeBtlIAH8Qm1jQuCghuF2rCSgMzUEMeUHEI2yISLYjhHkLDnbMYkgM6QbDn8lQBWCichlcieq2qB693CZ2IN1zGvvcWJHh-W7qvrTpHc-BUWE0Vu\", "
                + "\"drjPO26gRBCV18CQXx7qvR:APA91bG82mDMwPY1dRIC9uxVfYT6-GmsRDPdR92weIWehbBY12pvI_zmLUISty_Tn8OtkKcxJeXY-TdXkcqvBlCnUKlpUek5xyEq0mbaEXJ5u1JB_3eUNczw-bFefZ8GPP-3ZF9eKpiS\",\n" +
"    \"cCYDaFsFSReDR9pw5zlBrj:APA91bG2FoekMgpcuZeYRTeBYXIbBfCRcAP5oPyhONmDBXXmIkm8P_oXftBMaA8B8AnZukX2RkvA6t5pdRMG82kfW_P2KPaoTo5M_HSeREApan6U4psQ8ZqUHtT2W4L-F586r2BaPWrS\",\n" +
"    \"fisUgnh5RW2vnN4MJCx0mH:APA91bHXlm33Xe349UPKuqnBuXKOEvcT7JzHsOgPe1wXugmOMJpSzBsfkjpiVtfJgQi8JWaoXB7PnmhnznN_iTyQ0JPQsQhfihw97QzVls87LraSdekCPDdde6ARf9RGQWAny_g38pyM\",\n" +
"    \"eAvyOtIYTU6PBWZhL51sxK:APA91bHirr_1-qqP3nej3NtG42r-72ihpPigKJlOQ464waoyzIxjObmEhrWj2FRGO9YZUyo6HcrmXEhB1ipBC-yuV1VJHzHNNQvNGyHko6w0CbYggZXv4DmDfHPCq1JTGG8mBVWhpsc2\"";
               
        String JSON_Body =buildJSONBody( title, body, image, message, link, linkText, registration_ids);
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
 String title="Naslov 1 sa siuvs.rs";
        String body="Telo 1 sa siuvs.rs";
        String image="https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2020/02/Google-Image-Search.jpg";
        String message="Poruka 1 sa siuvs.rs";
        String link="https://siuvs.rs/";
        String linkText="Login strana na siuvs.rs";

        String registration_ids="\"fOa1rkFpQpuW782aUdYr7q:APA91bFhzOIZ3P5-Uj9pBygwY_PiZeBtlIAH8Qm1jQuCghuF2rCSgMzUEMeUHEI2yISLYjhHkLDnbMYkgM6QbDn8lQBWCichlcieq2qB693CZ2IN1zGvvcWJHh-W7qvrTpHc-BUWE0Vu\", "
                + "\"drjPO26gRBCV18CQXx7qvR:APA91bG82mDMwPY1dRIC9uxVfYT6-GmsRDPdR92weIWehbBY12pvI_zmLUISty_Tn8OtkKcxJeXY-TdXkcqvBlCnUKlpUek5xyEq0mbaEXJ5u1JB_3eUNczw-bFefZ8GPP-3ZF9eKpiS\",\n" +
"    \"cCYDaFsFSReDR9pw5zlBrj:APA91bG2FoekMgpcuZeYRTeBYXIbBfCRcAP5oPyhONmDBXXmIkm8P_oXftBMaA8B8AnZukX2RkvA6t5pdRMG82kfW_P2KPaoTo5M_HSeREApan6U4psQ8ZqUHtT2W4L-F586r2BaPWrS\",\n" +
"    \"fisUgnh5RW2vnN4MJCx0mH:APA91bHXlm33Xe349UPKuqnBuXKOEvcT7JzHsOgPe1wXugmOMJpSzBsfkjpiVtfJgQi8JWaoXB7PnmhnznN_iTyQ0JPQsQhfihw97QzVls87LraSdekCPDdde6ARf9RGQWAny_g38pyM\",\n" +
"    \"eAvyOtIYTU6PBWZhL51sxK:APA91bHirr_1-qqP3nej3NtG42r-72ihpPigKJlOQ464waoyzIxjObmEhrWj2FRGO9YZUyo6HcrmXEhB1ipBC-yuV1VJHzHNNQvNGyHko6w0CbYggZXv4DmDfHPCq1JTGG8mBVWhpsc2\"";
             
        String JSON_Body =buildJSONBody( title, body, image, message, link, linkText, registration_ids);
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
    private String buildJSONBody(String title,String body,String image,String message,String link,String linkText,String registration_ids) {

        String JSONBody = "{\n"
                + "    \"data\": {\n"
                + "        \"title\" : \""+title+"\", \n"
                + "        \"body\" : \""+body+"\", \n"
                + "        \"image\" : \""+image+"\",     \n"
                + "        \"message\": \""+message+"\",\n"
                + "        \"link\": \""+link+"\",\n"
                + "        \"linkText\": \""+linkText+"\"    \n" + " "
                + "    },\n"
                + "    \"registration_ids\": [\n"+registration_ids
                + "    ]\n"
                + "}";

        return JSONBody;
    }
}
