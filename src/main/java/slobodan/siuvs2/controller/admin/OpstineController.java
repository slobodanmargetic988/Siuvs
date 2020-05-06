/*
 * 
 */
package slobodan.siuvs2.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.service.OpstinaService;
import slobodan.siuvs2.service.ProvincijaService;
import slobodan.siuvs2.valueObject.DistriktID;
import slobodan.siuvs2.valueObject.OpstinaID;
import slobodan.siuvs2.valueObject.ProvincijaID;

/**
 *
 * @author deca
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class OpstineController {
    @Autowired
    private OpstinaService opstinaService;
     @Autowired
    private ProvincijaService provincijaService;
    @Autowired
    private DistriktService distriktService;
    
    @GetMapping(value = "/opstine")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        model.addAttribute("opstine", opstinaService.findAllOrderByNameAsc(pageable));
        return "admin/opstine/opstine";
            }
    
        @GetMapping(value = "/opstine/{opstinaId}")
    public String viewOne(final Model model,@PathVariable final OpstinaID opstinaId) {
        model.addAttribute("opstina", opstinaService.findOne(opstinaId));
        return "admin/opstine/view";
}
            @GetMapping(value = "/opstine/{opstinaId}/edit")
    public String editOpstina(final Model model,@PathVariable final OpstinaID opstinaId) {
        model.addAttribute("opstina", opstinaService.findOne(opstinaId));
        model.addAttribute("provincije", provincijaService.findAllOrderByNameAsc());
        model.addAttribute("distrikti", distriktService.findAllOrderByNameAsc());
        return "admin/opstine/edit";
}
                  @PostMapping(value = "/opstine/{opstinaId}/edit")
    public String updateOpstina(
            final Model model,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "name", defaultValue = "opstina") String name,
             @RequestParam(name = "supervisingprovince", defaultValue = "0") String provincija,
            @RequestParam(name = "supervisingdistrikt", defaultValue = "0") String distrikt,
            @PathVariable final OpstinaID opstinaId
    ) {
                      if (name.isEmpty() || name.equals("") || name.equals(" ")) {
                          redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате унети валидан назив");
                          redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                          return "redirect:/admin/opstine/" + opstinaId + "/edit";
                      } else {
                          if (provincija.equals(0)) {
                              redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати аутономну покрајину");
                              redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                               return "redirect:/admin/opstine/" + opstinaId + "/edit";
                          } else {
                              if (distrikt.equals(0)) {
                                  redirectAttributes.addFlashAttribute("roleErrorMessage", "Морате одабрати управни округ");
                                  redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                                   return "redirect:/admin/opstine/" + opstinaId + "/edit";
                              }else {
                 if (opstinaService.isNameUsed(opstinaId,name)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Јединица локалне самоуправе са истим називом већ постоји!");
               return "redirect:/admin/opstine/" + opstinaId + "/edit";
            }
            }
                          }
                      }
                      //all test passed
                        try {
                      Opstina opstina=opstinaService.findOne(opstinaId);
                      opstina.setName(name);
                       DistriktID distriktID= new DistriktID(distrikt);
                ProvincijaID provincijaID= new ProvincijaID(provincija);
                      opstina.setDistrikt(distriktService.findOne(distriktID));
                      opstina.setProvincija(provincijaService.findOne(provincijaID));
                     opstinaService.save(opstina);
                   
            
            redirectAttributes.addFlashAttribute("successMessage", "Успешна измена!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/opstine/" + opstinaId + "/edit";
        } 
                      
       return "redirect:/admin/opstine/" + opstinaId;
       
}
    
}
