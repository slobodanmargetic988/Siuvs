/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import slobodan.siuvs2.model.MobileAppUniq;
import slobodan.siuvs2.model.MobileAppUniqIos;
import slobodan.siuvs2.model.Mobileappdata;
import slobodan.siuvs2.model.Notifikacije;
import slobodan.siuvs2.model.NotifikacijeIos;
import slobodan.siuvs2.model.Volonter;
import slobodan.siuvs2.model.VolonterIos;
import slobodan.siuvs2.service.MobileAppUniqIosService;
import slobodan.siuvs2.service.MobileAppUniqService;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.service.NotifikacijeIosService;
import slobodan.siuvs2.service.NotifikacijeService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.VolonterIosService;
import slobodan.siuvs2.service.VolonterService;
import slobodan.siuvs2.valueObject.ClientId;


/**
 *
 * @author Slobodan
 */
@RestController
public class MobileAppRestController {


    @Autowired
    private MobileappdataService mobileappdataService;
    @Autowired
    private StorageService storageService;

    
    
      @Autowired
    private MobileAppUniqService mobileAppUniqService;
      
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
     volonter.setToken(token);
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
     notifikacije.setOpstina("Svi servisi");
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
         // maybeused
   //  "https://siuvs.rs/php/registerNewUserToken/"  + token
  //  "https://siuvs.rs/php/updatetoken/" + stariToken + "/" + token
  
          @GetMapping("/php/registerNewUserToken/{token}")
  String registerNewUserToken(@PathVariable final String token) {
      MobileAppUniq mobileAppUniq= new MobileAppUniq();
      mobileAppUniq.setToken(token);
      mobileAppUniqService.save(mobileAppUniq);
      return "uspešno je registrovan novi korisnik";
  }
            @GetMapping("/php/updatetoken/{stariToken}/{token}")
  String updateToken(@PathVariable final String stariToken,
          @PathVariable final String token) {
      volonterService.updateToken(stariToken, token);
              notifikacijeService.updateToken(stariToken, token);
              mobileAppUniqService.updateToken(stariToken, token); 
    return "uspešno je registrovana promena tokena";
  }
  
 @Autowired 
 VolonterIosService volonterIosService;
 
      @GetMapping("/php/volonterprijavaIos/{opstina}/{ime}/{prezime}/{email}/{telefon}/{token}")
 String volonterIos(@PathVariable final String opstina, @PathVariable final String ime, @PathVariable final String prezime, @PathVariable final String email, @PathVariable final String telefon, @PathVariable final String token) {
     VolonterIos exists=volonterIosService.findFirstByEmail(email);
    if(exists!=null){return "Volonter sa unetim emailom je vec prijavljen";}
     
     VolonterIos volonter= new VolonterIos();
     volonter.setIme(ime);
     volonter.setPrezime(prezime);
     volonter.setEmail(email);
     volonter.setOpstina(opstina);
     volonter.setTelefon(telefon);
     volonter.setToken(token);
     volonterIosService.save(volonter);
    return "Uspešno ste se prijavili za volontiranje u slucaju vanredne situacije";
  }


 @Autowired
 NotifikacijeIosService  notifikacijeIosService;
    @GetMapping("/php/notifikacijeIos/clear/{token}")
 Long notifikacijeClearByTokenIos(@PathVariable final String token) {     
    return   notifikacijeIosService.deleteByToken(token);     
  } 
 

  @GetMapping("/php/notifikacijeIos/clearone/{token}/{opstina}")
 Long notifikacijeClearByTokenAndOpstinaIos(@PathVariable final String token,@PathVariable final String opstina) {    
    return   notifikacijeIosService.deleteByTokenAndOpstina(token,opstina);  
  }
 

  @GetMapping("/php/notifikacijeIos/addSve/{token}")
 String notifikacijeAddSveByTokenIos(@PathVariable final String token) {
     NotifikacijeIos notifikacije= new NotifikacijeIos();
     notifikacije.setOpstina("Svi servisi");
     notifikacije.setToken(token);
     notifikacijeIosService.save(notifikacije);
    return "Uspešno ste se prijavili za sve notifikacije";     
  } 


 @GetMapping("/php/notifikacijeIos/addOne/{token}/{opstina}")
 String notifikacijeAddOneByTokenIos(@PathVariable final String token,@PathVariable final String opstina) {
     NotifikacijeIos notifikacije= new NotifikacijeIos();
     notifikacije.setOpstina(opstina);
     notifikacije.setToken(token);
     notifikacijeIosService.save(notifikacije);
    return "Uspešno ste se prijavili za notifikacije za "+opstina;     
  } 

   // may be used
        @GetMapping("/php/notifikacijeIos/getList/{token}")
  List<NotifikacijeIos> notifikacijeAllIos(@PathVariable final String token) {
    return notifikacijeIosService.findAllByToken(token) ;
  }


 @Autowired
  MobileAppUniqIosService mobileAppUniqIosService;
  
        @GetMapping("/php/registerNewUserTokenIos/{token}")
  String registerNewUserTokenIos(@PathVariable final String token) {
      MobileAppUniqIos mobileAppUniq= new MobileAppUniqIos();
      mobileAppUniq.setToken(token);
      mobileAppUniqIosService.save(mobileAppUniq);
      return "uspešno je registrovan novi korisnik";
  }
  

  
     @GetMapping("/php/updatetokenIos/{stariToken}/{token}")
  String updateTokenIos(@PathVariable final String stariToken,
          @PathVariable final String token) {
      volonterIosService.updateToken(stariToken, token);
              notifikacijeIosService.updateToken(stariToken, token);
            mobileAppUniqIosService.updateToken(stariToken, token);   
              
    return "uspešno je registrovana promena tokena";
  }
  
  
  @GetMapping(value = "/php/getimg/{clientId}/{filename}")
    public ResponseEntity<Resource> servePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final String filename
    ) {
        Resource file = storageService.loadAsResource(clientId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
    

}
