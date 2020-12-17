package slobodan.siuvs2.controller.client;



import java.sql.Date;

import java.util.List;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Calendar;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.User;

import slobodan.siuvs2.service.CalendarService;


@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class CalendarController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CalendarService calendarService;

///admin/clients/{clientID}/calendar (clientID=${client.id})
    @GetMapping(value = "/calendar")
    public String list(
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
       
        model.addAttribute("client", client);
        List<Calendar> calendarList = calendarService.findAllByClient(client);
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();
        Calendar calendar3 = new Calendar();
        Calendar calendar4 = new Calendar();

        if (!calendarList.isEmpty()) {
            for (Calendar calendar : calendarList) {
                if (calendar.getDokument().equals("Процена ризика")) {
                    calendar1 = calendar;
                } else {
                    if (calendar.getDokument().equals("План заштите и спасавања")) {
                        calendar2 = calendar;
                    } else {
                        if (calendar.getDokument().equals("План смањења ризика")) {
                            calendar3 = calendar;
                        } else {
                            if (calendar.getDokument().equals("Оперативни план за одбрану од поплава")) {
                                calendar4 = calendar;
                            }

                        }

                    }

                }
            }
            model.addAttribute("calendar1", calendar1);
            model.addAttribute("calendar2", calendar2);
            model.addAttribute("calendar3", calendar3);
            model.addAttribute("calendar4", calendar4);

        } else {
            model.addAttribute("calendarprazan", "Нису унети детаљи везани за период важења за ниједан документ.");

        }

        return "/client/calendar/calendar";
    }

    @GetMapping(value = "/calendar/edit")
    public String edit(
            final Model model) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        List<Calendar> calendarList = calendarService.findAllByClient(client);
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();
        Calendar calendar3 = new Calendar();
        Calendar calendar4 = new Calendar();

        model.addAttribute("calendarList", calendarList);

        for (Calendar calendar : calendarList) {
            if (calendar.getDokument().equals("Процена ризика")) {
                calendar1 = calendar;
            } else {
                if (calendar.getDokument().equals("План заштите и спасавања")) {
                    calendar2 = calendar;
                } else {
                    if (calendar.getDokument().equals("План смањења ризика")) {
                        calendar3 = calendar;
                    } else {
                        if (calendar.getDokument().equals("Оперативни план за одбрану од поплава")) {
                            calendar4 = calendar;
                        }

                    }

                }

            }
        }

        model.addAttribute("calendar1", calendar1);
        model.addAttribute("calendar2", calendar2);
        model.addAttribute("calendar3", calendar3);
        model.addAttribute("calendar4", calendar4);

        return "/client/calendar/calendarEdit";
    }

    @PostMapping(value = "/calendar/edit")
    public String saveEdit(
            
            @RequestParam(name = "brojresenja1", defaultValue = " ") String brojresenja1,
            @RequestParam(name = "datumdonosenja1", defaultValue = " ") Date datumdonosenja1,
            @RequestParam(name = "vazido1", defaultValue = " ") Date vazido1,
            @RequestParam(name = "brojresenja2", defaultValue = " ") String brojresenja2,
            @RequestParam(name = "datumdonosenja2", defaultValue = " ") Date datumdonosenja2,
            @RequestParam(name = "vazido2", defaultValue = " ") Date vazido2,
            @RequestParam(name = "brojresenja3", defaultValue = " ") String brojresenja3,
            @RequestParam(name = "datumdonosenja3", defaultValue = " ") Date datumdonosenja3,
            @RequestParam(name = "vazido3", defaultValue = " ") Date vazido3,
            @RequestParam(name = "brojresenja4", defaultValue = " ") String brojresenja4,
            @RequestParam(name = "datumdonosenja4", defaultValue = " ") Date datumdonosenja4,
            @RequestParam(name = "vazido4", defaultValue = " ") Date vazido4,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        List<Calendar> calendarList = calendarService.findAllByClient(client);
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();
        Calendar calendar3 = new Calendar();
        Calendar calendar4 = new Calendar();

        calendar1.setClient(client);
        calendar1.setDokument("Процена ризика");
        calendar1.setResenje(brojresenja1);
        calendar1.setDoneto(datumdonosenja1);
        calendar1.setVazido(vazido1);

        calendar2.setClient(client);
        calendar2.setDokument("План заштите и спасавања");
        calendar2.setResenje(brojresenja2);
        calendar2.setDoneto(datumdonosenja2);
        calendar2.setVazido(vazido2);

        calendar3.setClient(client);
        calendar3.setDokument("План смањења ризика");
        calendar3.setResenje(brojresenja3);
        calendar3.setDoneto(datumdonosenja3);
        calendar3.setVazido(vazido3);

        calendar4.setClient(client);
        calendar4.setDokument("Оперативни план за одбрану од поплава");
        calendar4.setResenje(brojresenja4);
        calendar4.setDoneto(datumdonosenja4);
        calendar4.setVazido(vazido4);

        try {

            calendarService.save(calendar1);
            calendarService.save(calendar2);
            calendarService.save(calendar3);
            calendarService.save(calendar4);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/client/calendar/edit";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Измене су успешно сачуване");

        return "redirect:/client/calendar";
    }

}
