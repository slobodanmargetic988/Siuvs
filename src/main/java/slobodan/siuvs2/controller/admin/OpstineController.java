/*
 * 
 */
package slobodan.siuvs2.controller.admin;

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
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
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
    public String viewOne(final Model model, @PathVariable final OpstinaID opstinaId) {
        model.addAttribute("opstina", opstinaService.findOne(opstinaId));
        return "admin/opstine/view";
    }

    @GetMapping(value = "/opstine/{opstinaId}/edit")
    public String editOpstina(final Model model, @PathVariable final OpstinaID opstinaId) {
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
            @RequestParam(name = "supervisingprovince", defaultValue = "0") ProvincijaID provincijaID,
            @RequestParam(name = "supervisingdistrikt", defaultValue = "0") DistriktID distriktID,
            @PathVariable final OpstinaID opstinaId
    ) {
        if (name.isEmpty() || name.equals("") || name.equals(" ")) {
            redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
            return "redirect:/admin/opstine/" + opstinaId + "/edit";
        } else {
            if (provincijaID.getValue() == 0) {
                redirectAttributes.addFlashAttribute("provinceErrorMessage", "Морате одабрати аутономну покрајину");
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                return "redirect:/admin/opstine/" + opstinaId + "/edit";
            } else {
                if (distriktID.getValue() == 0) {
                    redirectAttributes.addFlashAttribute("distriktErrorMessage", "Морате одабрати управни округ");
                    redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                    return "redirect:/admin/opstine/" + opstinaId + "/edit";
                } else {
                    if (opstinaService.isNameUsed(opstinaId, name)) {
                        redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
                        redirectAttributes.addFlashAttribute("errorMessage", "Јединица локалне самоуправе са истим називом већ постоји!");
                        return "redirect:/admin/opstine/" + opstinaId + "/edit";
                    }
                }
            }
        }
        //all test passed
        try {
            Opstina opstina = opstinaService.findOne(opstinaId);
            opstina.setName(name);
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

    @GetMapping(value = "/opstine/new")
    public String editOpstina(final Model model) {
        model.addAttribute("provincije", provincijaService.findAllOrderByNameAsc());
        model.addAttribute("distrikti", distriktService.findAllOrderByNameAsc());
        return "admin/opstine/new";
    }

    @PostMapping(value = "/opstine/new")
    public String addOpstina(final Model model,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "name", defaultValue = "opstina") String name,
            @RequestParam(name = "supervisingprovince", defaultValue = "0") ProvincijaID provincijaID,
            @RequestParam(name = "supervisingdistrikt", defaultValue = "0") DistriktID distriktID
    ) {
        if (name.isEmpty() || name.equals("") || name.equals(" ")) {
            redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
            return "redirect:/admin/opstine/new";
        } else {
            if (provincijaID.getValue() == 0) {
                redirectAttributes.addFlashAttribute("provinceErrorMessage", "Морате одабрати аутономну покрајину");
                redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                return "redirect:/admin/opstine/new";
            } else {
                if (distriktID.getValue() == 0) {
                    redirectAttributes.addFlashAttribute("distriktErrorMessage", "Морате одабрати управни округ");
                    redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
                    return "redirect:/admin/opstine/new";
                } else {
                    if (opstinaService.isNameUsed(name)) {
                        redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
                        redirectAttributes.addFlashAttribute("errorMessage", "Јединица локалне самоуправе са истим називом већ постоји!");
                        return "redirect:/admin/opstine/new";
                    }
                }
            }
        }
        try {
            Opstina opstina = new Opstina();
            opstina.setName(name);
            opstina.setDistrikt(distriktService.findOne(distriktID));
            opstina.setProvincija(provincijaService.findOne(provincijaID));
            opstinaService.save(opstina);
            redirectAttributes.addFlashAttribute("successMessage", "Локална самоуправа успешно додата!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/opstine/new";
        }
        return "redirect:/admin/opstine";
    }
}
