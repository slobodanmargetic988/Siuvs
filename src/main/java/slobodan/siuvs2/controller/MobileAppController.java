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
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DynamicDataService;
import slobodan.siuvs2.service.MobileappdataService;
import slobodan.siuvs2.valueObject.OpstinaID;

/**
 *
 * @author Slobodan
 */
@RestController
public class MobileAppController {


    @Autowired
    private MobileappdataService mobileappdataService;
    
      @GetMapping("/php/getallopstine")
  List<Mobileappdata> all() {
    return mobileappdataService.findAllBy();
  }
        @GetMapping("/php/getallopstine/{opstina}")
  List<Mobileappdata> opstinaAll(@PathVariable final String opstina) {
    return mobileappdataService.findAllByOpstina(opstina);
  }
   @GetMapping("/php/getallopstine/{opstina}/{opasnost}")
 Mobileappdata opstinaFirst(@PathVariable final String opstina, @PathVariable final String opasnost) {
      
    return mobileappdataService.findFirstByOpstinaAndOpasnost(opstina,opasnost);
  }
  
  
  
  
}
