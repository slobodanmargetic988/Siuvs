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
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.service.DistriktService;
import slobodan.siuvs2.valueObject.DistriktID;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class DistriktController {

    @Autowired
    private DistriktService distriktService;

    @GetMapping(value = "/distrikt")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        model.addAttribute("distrikti", distriktService.findAllOrderByNameAsc(pageable));
        return "admin/distrikti/distrikti";
    }

    @GetMapping(value = "/distrikt/new")
    public String list(final Model model) {
        return "admin/distrikti/new";
    }

    @PostMapping(value = "/distrikt/new")
    public String newDistrikt(final Model model,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "name", defaultValue = " ") String name
    ) {
        if (name.isEmpty() || name.equals("") || name.equals(" ")) {
            redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
            return "redirect:/admin/distrikt/new";
        } else {
            if (distriktService.isNameUsed(name)) {
                redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
                redirectAttributes.addFlashAttribute("errorMessage", "Управни округ са истим називом већ постоји!");
                return "redirect:/admin/distrikt/new";
            }
        }
        try {
            Distrikt distrikt = new Distrikt();
            distrikt.setName(name);
            distriktService.save(distrikt);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно додат управни округ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/distrikt/new";
        }
        return "redirect:/admin/distrikt";

    }

    @GetMapping(value = "/distrikt/{distriktID}")
    public String viewOne(final Model model,
            @PathVariable final DistriktID distriktID) {
        model.addAttribute("distrikt", distriktService.findOne(distriktID));
        return "admin/distrikti/view";
    }

    @GetMapping(value = "/distrikt/{distriktID}/edit")
    public String editOne(final Model model,
            @PathVariable final DistriktID distriktID) {
        model.addAttribute("distrikt", distriktService.findOne(distriktID));
        return "admin/distrikti/edit";
    }

    @PostMapping(value = "/distrikt/{distriktID}/edit")
    public String updateOne(final Model model,
            @PathVariable final DistriktID distriktID,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "name", defaultValue = " ") String name
    ) {
        if (name.isEmpty() || name.equals("") || name.equals(" ")) {
            redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
            return "redirect:/admin/distrikt/" + distriktID + "/edit";
        } else {
            if (distriktService.isNameUsed(distriktID, name)) {
                redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
                redirectAttributes.addFlashAttribute("errorMessage", "Управни округ са истим називом већ постоји!");
                return "redirect:/admin/distrikt/" + distriktID + "/edit";
            }
        }
        try {
            Distrikt distrikt = distriktService.findOne(distriktID);
            distrikt.setName(name);
            distriktService.save(distrikt);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно измењен управни округ!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/distrikt/" + distriktID + "/edit";
        }
        return "redirect:/admin/distrikt";
    }

}
