package slobodan.siuvs2.controller.admin.clients;


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Calendar;

import slobodan.siuvs2.service.CalendarService;


@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin")
public class ClientsCalendarController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CalendarService calendarService;

///admin/clients/{clientID}/calendar (clientID=${client.id})
    @GetMapping(value = "/clients/{clientId}/calendar")
    public String list(@PathVariable final ClientId clientId,
            final Model model) {
        Client client = clientService.findOne(clientId);
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

        return "admin/clients/calendar";
    }

    @GetMapping(value = "/clients/{clientId}/calendar/edit")
    public String edit(@PathVariable final ClientId clientId,
            final Model model) {
        Client client = clientService.findOne(clientId);
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

        return "admin/clients/calendarEdit";
    }

    @PostMapping(value = "/clients/{clientId}/calendar/edit")
    public String saveEdit(
            @PathVariable final ClientId clientId,
            @RequestParam(name = "brojresenja1", defaultValue = " ") String brojresenja1,
            @RequestParam(name = "datumdonosenja1", defaultValue = "2020-01-01") String datumdonosenja1,
            @RequestParam(name = "vazido1", defaultValue = "2020-01-01") String vazido1,
            @RequestParam(name = "brojresenja2", defaultValue = " ") String brojresenja2,
            @RequestParam(name = "datumdonosenja2", defaultValue = "2020-01-01") String datumdonosenja2,
            @RequestParam(name = "vazido2", defaultValue = "2020-01-01") String vazido2,
            @RequestParam(name = "brojresenja3", defaultValue = " ") String brojresenja3,
            @RequestParam(name = "datumdonosenja3", defaultValue = "2020-01-01") String datumdonosenja3,
            @RequestParam(name = "vazido3", defaultValue = "2020-01-01") String vazido3,
            @RequestParam(name = "brojresenja4", defaultValue = " ") String brojresenja4,
            @RequestParam(name = "datumdonosenja4", defaultValue = "2020-01-01") String datumdonosenja4,
            @RequestParam(name = "vazido4", defaultValue = "2020-01-01") String vazido4,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
       
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();
        Calendar calendar3 = new Calendar();
        Calendar calendar4 = new Calendar();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-dd");
  
        
        calendar1.setClient(client);
        calendar1.setDokument("Процена ризика");
        calendar1.setResenje(brojresenja1);
        calendar1.setDoneto(LocalDate.parse(datumdonosenja1, dateFormat));
        calendar1.setVazido(LocalDate.parse(vazido1, dateFormat));

        calendar2.setClient(client);
        calendar2.setDokument("План заштите и спасавања");
        calendar2.setResenje(brojresenja2);
        calendar2.setDoneto(LocalDate.parse(datumdonosenja2, dateFormat));
        calendar2.setVazido(LocalDate.parse(vazido2, dateFormat));

        calendar3.setClient(client);
        calendar3.setDokument("План смањења ризика");
        calendar3.setResenje(brojresenja3);
        calendar3.setDoneto(LocalDate.parse(datumdonosenja3, dateFormat));
        calendar3.setVazido(LocalDate.parse(vazido3, dateFormat));

        calendar4.setClient(client);
        calendar4.setDokument("Оперативни план за одбрану од поплава");
        calendar4.setResenje(brojresenja4);
        calendar4.setDoneto(LocalDate.parse(datumdonosenja4, dateFormat));
        calendar4.setVazido(LocalDate.parse(vazido4, dateFormat));
List<Calendar> calendarList = new ArrayList<Calendar>();

calendarList.add(calendar1);
calendarList.add(calendar2);
calendarList.add(calendar3);
calendarList.add(calendar4);
        try {

            calendarService.save(calendar1);
           calendarService.save(calendar2);
           calendarService.save(calendar3);
           calendarService.save(calendar4);
          setFirstExpireingCalendar( client,calendarList);  
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/clients/" + clientId + "/calendar/edit";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Измене су успешно сачуване");

        return "redirect:/admin/clients/" + clientId + "/calendar";
    }
    
    
private void setFirstExpireingCalendar(Client client, List<Calendar>calendarList) {
       
        if (!calendarList.isEmpty()) {
            Calendar calendarTemp = calendarList.get(0);
            for (Calendar calendar : calendarList) {
                if (calendarTemp.getVazido().compareTo(calendar.getVazido()) > 0) {
                    calendarTemp = calendar;
                }
            }
            client.setCalendar(calendarTemp);
            clientService.save(client);
        }}
}
