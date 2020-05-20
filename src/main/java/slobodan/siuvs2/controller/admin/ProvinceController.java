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
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.service.ProvincijaService;
import slobodan.siuvs2.valueObject.ProvincijaID;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class ProvinceController {

    @Autowired
    private ProvincijaService provincijaService;

    @GetMapping(value = "/province")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        model.addAttribute("provincije", provincijaService.findAllByOrderByNameAsc(pageable));
        return "admin/province/province";
    }

    @GetMapping(value = "/province/new")
    public String list(final Model model) {
        return "admin/province/new";
    }

    @PostMapping(value = "/province/new")
    public String newProvince(final Model model,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "name", defaultValue = " ") String name
    ) {
        if (name.isEmpty() || name.equals("") || name.equals(" ")) {
            redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
            return "redirect:/admin/province/new";
        } else {
            if (provincijaService.isNameUsed(name)) {
                redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
                redirectAttributes.addFlashAttribute("errorMessage", "Аутономна покрајина  са истим називом већ постоји!");
                return "redirect:/admin/province/new";
            }
        }
        try {
            Provincija provincija = new Provincija();
            provincija.setName(name);
            provincijaService.save(provincija);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно додата аутономна покрајина!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/province/new";
        }
        return "redirect:/admin/province";

    }

    @GetMapping(value = "/province/{provincijaID}")
    public String viewOne(final Model model,
            @PathVariable final ProvincijaID provincijaID) {
        model.addAttribute("provincija", provincijaService.findOne(provincijaID));
        return "admin/province/view";
    }

    @GetMapping(value = "/province/{provincijaID}/edit")
    public String editOne(final Model model,
            @PathVariable final ProvincijaID provincijaID) {
        model.addAttribute("provincija", provincijaService.findOne(provincijaID));
        return "admin/province/edit";
    }

    @PostMapping(value = "/province/{provincijaID}/edit")
    public String updateOne(final Model model,
            @PathVariable final ProvincijaID provincijaID,
            final RedirectAttributes redirectAttributes,
            @RequestParam(name = "name", defaultValue = " ") String name
    ) {
        if (name.isEmpty() || name.equals("") || name.equals(" ")) {
            redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом измене!");
            return "redirect:/admin/province/" + provincijaID + "/edit";
        } else {
            if (provincijaService.isNameUsed(provincijaID, name)) {
                redirectAttributes.addFlashAttribute("NameErrorMessage", "Морате унети валидан назив");
                redirectAttributes.addFlashAttribute("errorMessage", "Аутономна покрајина са истим називом већ постоји!");
                return "redirect:/admin/province/" + provincijaID + "/edit";
            }
        }
        try {
            Provincija provincija = provincijaService.findOne(provincijaID);
            provincija.setName(name);
            provincijaService.save(provincija);
            redirectAttributes.addFlashAttribute("successMessage", "Успешно измењена аутономна покрајина!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/province/" + provincijaID + "/edit";
        }
        return "redirect:/admin/province";

    }

}
