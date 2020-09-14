/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slobodan.siuvs2.facade.TableFacade;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DynamicDataService;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.service.NotifikacijeService;
import slobodan.siuvs2.service.VolonterService;
import slobodan.siuvs2.valueObject.OpstinaID;

/**
 *
 * @author Slobodan
 */
@RestController
public class MobileAppController {


    @Autowired
    private MobileappdataService mobileappdataService;
    @Autowired
    private VolonterService volonterService;
     @Autowired
    private NotifikacijeService notifikacijeService;
   //unused  
      @GetMapping("/php/getallopstine")
  List<Mobileappdata> all() {
    return mobileappdataService.findAllBy();
  }
  //unused
        @GetMapping("/php/getallopstine/{opstina}")
  List<Mobileappdata> opstinaAll(@PathVariable final String opstina) {
    return mobileappdataService.findAllByOpstina(opstina);
  }
  
   @GetMapping("/php/getallopstine/{opstina}/{opasnost}")
 Mobileappdata opstinaFirst(@PathVariable final String opstina, @PathVariable final String opasnost) {
      
    return mobileappdataService.findFirstByOpstinaAndOpasnost(opstina,opasnost);
  }
  
  
     @GetMapping("/php/volonterprijava/{opstina}/{ime}/{prezime}/{email}/{telefon}/{token}")
 String volonter(@PathVariable final String opstina, @PathVariable final String ime, @PathVariable final String prezime, @PathVariable final String email, @PathVariable final String telefon, @PathVariable final String token) {
     Volonter exists=volonterService.findFirstByEmail(email);
    if(exists!=null){return "Volonter sa unetim emailom je već prijavljen";}
     
     Volonter volonter= new Volonter();
     volonter.setIme(ime);
     volonter.setPrezime(prezime);
     volonter.setEmail(email);
     volonter.setOpstina(opstina);
     volonter.setTelefon(telefon);
     
     volonterService.save(volonter);
    return "Uspešno ste se prijavili za volontiranje u slučaju vanredne situacije";
  }

           @GetMapping("/php/notifikacije/clear/{token}")
 Long notifikacijeClearByToken(@PathVariable final String token) {     
    return   notifikacijeService.deleteByToken(token);     
  } 
            @GetMapping("/php/notifikacije/clearone/{token}/{opstina}")
 Long notifikacijeClearByTokenAndOpstina(@PathVariable final String token,@PathVariable final String opstina) {    
    return   notifikacijeService.deleteByTokenAndOpstina(token,opstina);  
  }
 

          @GetMapping("/php/notifikacije/addSve/{token}")
 String notifikacijeAddSveByToken(@PathVariable final String token) {
     Notifikacije notifikacije= new Notifikacije();
     notifikacije.setOpstina("Sve opštine");
     notifikacije.setToken(token);
     notifikacijeService.save(notifikacije);
    return "Uspešno ste se prijavili za sve notifikacije";     
  } 
 

          @GetMapping("/php/notifikacije/addOne/{token}/{opstina}")
 String notifikacijeAddOneByToken(@PathVariable final String token,@PathVariable final String opstina) {
     Notifikacije notifikacije= new Notifikacije();
     notifikacije.setOpstina(opstina);
     notifikacije.setToken(token);
     notifikacijeService.save(notifikacije);
    return "Uspešno ste se prijavili za notifikacije za "+opstina;     
  } 
   // maybeused
        @GetMapping("/php/notifikacije/getList/{token}")
  List<Notifikacije> notifikacijeAll(@PathVariable final String token) {
    return notifikacijeService.findAllByToken(token) ;
  }
 
  
}
