/*
 * 
 */
package slobodan.siuvs2.controller.client;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.controller.admin.clients.*;
import slobodan.siuvs2.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.DynamicData;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.KartonClanovaStaba;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.TableColumn;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.CiljeviUdruzenjaService;
import slobodan.siuvs2.service.ClanoviUdruzenjaService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DetaljiMTSService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.KartonClanovaStabaService;
import slobodan.siuvs2.service.SpecijalnostService;
import slobodan.siuvs2.service.TableColumnService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.KartonClanovaStabaId;
import slobodan.siuvs2.valueObject.TableColumnId;
import slobodan.siuvs2.valueObject.TableDefinitionId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller

public class KartonClanaStabaController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private KartonClanovaStabaService kartonClanovaStabaService;
    @Autowired
    private SpecijalnostService specijalnostiService;
    @Autowired
    private ClanoviUdruzenjaService clanoviUdruzenjaService;
    @Autowired
    private CiljeviUdruzenjaService ciljeviUdruzenjaService;

    @GetMapping(value = "/client/kartonClanaStaba")
    public String clientkartonClanaStaba(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        model.addAttribute("client", client);
        List<KartonClanovaStaba> listaKartona = kartonClanovaStabaService.findAllByClientOrderByPunoimeAsc(client);
        model.addAttribute("listaKartona", listaKartona);

        return "/client/kartonClanovaStaba/kartonClanaStabaLista";
    }

    @GetMapping(value = "/client/kartonClanaStaba/{kartonId}")
    public String adminkartonUdruzenjaJedan(
            @PathVariable final KartonClanovaStabaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();

        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        model.addAttribute("karton", karton);
        return "/client/kartonClanovaStaba/kartonClanaStaba";
    }

    @GetMapping(value = "/client/kartonClanaStaba/newKarton")
    public String clientkartonClanaStabaNew(
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        return "/client/kartonClanovaStaba/newKartonClanaStaba";
    }

    @PostMapping(value = "/client/kartonClanaStaba/newKarton")
    public String clientkartonClanovaStabaNewSave(
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
            @RequestParam(value = "naziv_operativnog_staba", defaultValue = "/") String naziv_operativnog_staba,
            @RequestParam(value = "naziv_strucno_operativnog_tima", defaultValue = "/") String naziv_strucno_operativnog_tima,
            @RequestParam(value = "funkcija_u_stabu", defaultValue = "/") String funkcija_u_stabu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
            @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
            @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
            @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
            @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
            @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
            @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
            @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

        KartonClanovaStaba karton = new KartonClanovaStaba();

//        KartonUdruzenja karton = new KartonUdruzenja();
        karton.setClient(client);

        karton.setNaziv_staba_vanr_situacije(naziv_staba_vanr_situacije);
        karton.setAdresa_staba(adresa_staba);
        karton.setFiksni_telefon_staba(fiksni_telefon_staba);
        karton.setMobilni_telefon_staba(mobilni_telefon_staba);
        karton.setEmail_staba(email_staba);
        karton.setNaziv_operativnog_staba(naziv_operativnog_staba);
        karton.setNaziv_strucno_operativnog_tima(naziv_strucno_operativnog_tima);
        karton.setFunkcija_u_stabu(funkcija_u_stabu);
        karton.setPunoime(punoime);
        karton.setNaziv_organa_gde_radi(naziv_organa_gde_radi);
        karton.setAdresa_organa_gde_radi(adresa_organa_gde_radi);
        karton.setFunkcija_na_radu(funkcija_na_radu);
        karton.setFiksni_telefon_sluzbeni(fiksni_telefon_sluzbeni);
        karton.setMobilni_telefon_sluzbeni(mobilni_telefon_sluzbeni);
        karton.setEmail_sluzbeni(email_sluzbeni);
        karton.setEmail_licni(email_licni);
        karton.setAdresa_stanovanja(adresa_stanovanja);
        karton.setTelefon_u_stanu(telefon_u_stanu);
        karton.setUcesce_na_obukama(ucesce_na_obukama);

        try {
            kartonClanovaStabaService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nov karton nije uspešno sačuvan!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan!");
            return "redirect:/client/kartonClanaStaba";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Nov karton je uspešno sačuvan možete dodati kadrove!");
            return "redirect:/client/" + karton.getId() + "/dodajClana";
        }

    }

    @GetMapping(value = "/client/kartonClanaStaba/{kartonId}/izmeniKarton")
    public String clientkartonClanaStabaEdit(
            @PathVariable final KartonClanovaStabaId kartonId,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        model.addAttribute("karton", karton);

        return "/client/kartonClanovaStaba/editKartonClanaStaba";
    }

    @PostMapping(value = "/client/kartonClanaStaba/{kartonId}/izmeniKarton")
    public String clientkartonClanaStabaEditSave(
            @PathVariable final KartonClanovaStabaId kartonId,
            @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "adresa_staba", defaultValue = "/") String adresa_staba,
            @RequestParam(value = "naziv_staba_vanr_situacije", defaultValue = "/") String naziv_staba_vanr_situacije,
            @RequestParam(value = "fiksni_telefon_staba", defaultValue = "/") String fiksni_telefon_staba,
            @RequestParam(value = "mobilni_telefon_staba", defaultValue = "/") String mobilni_telefon_staba,
            @RequestParam(value = "email_staba", defaultValue = "/") String email_staba,
            @RequestParam(value = "naziv_operativnog_staba", defaultValue = "/") String naziv_operativnog_staba,
            @RequestParam(value = "naziv_strucno_operativnog_tima", defaultValue = "/") String naziv_strucno_operativnog_tima,
            @RequestParam(value = "funkcija_u_stabu", defaultValue = "/") String funkcija_u_stabu,
            @RequestParam(value = "punoime", defaultValue = "/") String punoime,
            @RequestParam(value = "naziv_organa_gde_radi", defaultValue = "/") String naziv_organa_gde_radi,
            @RequestParam(value = "adresa_organa_gde_radi", defaultValue = "/") String adresa_organa_gde_radi,
            @RequestParam(value = "funkcija_na_radu", defaultValue = "/") String funkcija_na_radu,
            @RequestParam(value = "fiksni_telefon_sluzbeni", defaultValue = "/") String fiksni_telefon_sluzbeni,
            @RequestParam(value = "mobilni_telefon_sluzbeni", defaultValue = "/") String mobilni_telefon_sluzbeni,
            @RequestParam(value = "email_sluzbeni", defaultValue = "/") String email_sluzbeni,
            @RequestParam(value = "email_licni", defaultValue = "/") String email_licni,
            @RequestParam(value = "adresa_stanovanja", defaultValue = "/") String adresa_stanovanja,
            @RequestParam(value = "telefon_u_stanu", defaultValue = "/") String telefon_u_stanu,
            @RequestParam(value = "ucesce_na_obukama", defaultValue = "/") String ucesce_na_obukama,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);
        KartonClanovaStaba karton = kartonClanovaStabaService.findOne(kartonId);
        karton.setNaziv_staba_vanr_situacije(naziv_staba_vanr_situacije);
        karton.setAdresa_staba(adresa_staba);
        karton.setFiksni_telefon_staba(fiksni_telefon_staba);
        karton.setMobilni_telefon_staba(mobilni_telefon_staba);
        karton.setEmail_staba(email_staba);
        karton.setNaziv_operativnog_staba(naziv_operativnog_staba);
        karton.setNaziv_strucno_operativnog_tima(naziv_strucno_operativnog_tima);
        karton.setFunkcija_u_stabu(funkcija_u_stabu);
        karton.setPunoime(punoime);
        karton.setNaziv_organa_gde_radi(naziv_organa_gde_radi);
        karton.setAdresa_organa_gde_radi(adresa_organa_gde_radi);
        karton.setFunkcija_na_radu(funkcija_na_radu);
        karton.setFiksni_telefon_sluzbeni(fiksni_telefon_sluzbeni);
        karton.setMobilni_telefon_sluzbeni(mobilni_telefon_sluzbeni);
        karton.setEmail_sluzbeni(email_sluzbeni);
        karton.setEmail_licni(email_licni);
        karton.setAdresa_stanovanja(adresa_stanovanja);
        karton.setTelefon_u_stanu(telefon_u_stanu);
        karton.setUcesce_na_obukama(ucesce_na_obukama);

        try {
            kartonClanovaStabaService.save(karton);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Karton nije uspešno izmenjen!");
        }

        if (action.equals("save")) {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen!");
            return "redirect:/client/kartonClanaStaba";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Karton je uspešno izmenjen možete dodati kadrove!");
            return "redirect:/client/kartonClanaStaba/" + karton.getId() + "/dodajClana";
        }

    }

    
    
    @GetMapping(value = "/client/zbirniObrasci")
 
    public String adminZbirniObrasciJedinstveniPregled(
        
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        model.addAttribute("client", client);

/////////////////////////////////////////////////////////////////SEGMENT ZA SIRENE
        TableDefinitionId tableDefinitionIdSirene = new TableDefinitionId(6002);
        DynamicTable tabelaSirene = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdSirene, client); //sirene 6002
        TableColumn vrstaSirene = new TableColumn();
        Integer elektronskeSirene = 0;
        Integer pneumatskeSirene = 0;
        Integer elektricneSirene = 0;
        for (TableColumn kolona : tabelaSirene.getTableDefinition().getColumns(client)) {//trazimo kolonu koja nas zanima i cuvamo je za dalju referencu
            if (kolona.getTitle().equalsIgnoreCase("Врста сирене")) {
                vrstaSirene = kolona;
            }
        }

        for (DynamicRow red : tabelaSirene.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {   //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                if (data.getColumn().equals(vrstaSirene)) {

                    if (data.getValue().equals("600022")) {
                        elektricneSirene++;
                    }// cuvamo broj razlicitih unosa

                    if (data.getValue().equals("600021")) {
                        elektronskeSirene++;
                    }

                    if (data.getValue().equals("600023")) {
                        pneumatskeSirene++;
                    }

                };
            }

        }
        model.addAttribute("elektronskeSirene", elektronskeSirene);
        model.addAttribute("pneumatskeSirene", pneumatskeSirene);
        model.addAttribute("elektricneSirene", elektricneSirene);
/////////////////////////////////////////////////////////////////SEGMENT ZA SIRENE

  
        /////////////////////////////////////////////////////////////////SEGMENT ZA POVERENIKE CIVILNE ZASTITE
        TableDefinitionId tableDefinitionIdPoverenici = new TableDefinitionId(6003);
        DynamicTable tabelaPoverenici = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdPoverenici, client); //poverenici 6003
        TableColumn povereniciKolona1 = tableColumnService.findOne(new TableColumnId(60032));
        TableColumn povereniciKolona2 = tableColumnService.findOne(new TableColumnId(60033));
        TableColumn povereniciKolona3 = tableColumnService.findOne(new TableColumnId(60034));
        TableColumn povereniciKolona4 = tableColumnService.findOne(new TableColumnId(60035));
        TableColumn povereniciKolona5 = tableColumnService.findOne(new TableColumnId(60036));
        TableColumn povereniciKolona6 = tableColumnService.findOne(new TableColumnId(60038));
        TableColumn povereniciKolona7 = tableColumnService.findOne(new TableColumnId(60039));
        Integer povereniciBrojac1 = 0;
        Integer povereniciBrojac2 = 0;
        Integer povereniciBrojac3 = 0;
        Integer povereniciBrojac4 = 0;
        Integer povereniciBrojac5 = 0;
        Integer povereniciBrojac6 = 0;
        Integer povereniciBrojac7 = 0;
        //   Integer        povereniciBrojacSuma=0;

        for (DynamicRow red : tabelaPoverenici.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().equals(povereniciKolona1)) {
                        povereniciBrojac1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona2)) {
                        povereniciBrojac2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona3)) {
                        povereniciBrojac3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona4)) {
                        povereniciBrojac4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona5)) {
                        povereniciBrojac5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona6)) {
                        povereniciBrojac6 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().equals(povereniciKolona7)) {
                        povereniciBrojac7 += Integer.parseInt(data.getValue());
                    };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }
        model.addAttribute("povereniciBrojac1", povereniciBrojac1);
        model.addAttribute("povereniciBrojac2", povereniciBrojac2);
        model.addAttribute("povereniciBrojac3", povereniciBrojac3);
        model.addAttribute("povereniciBrojac4", povereniciBrojac4);
        model.addAttribute("povereniciBrojac5", povereniciBrojac5);
        model.addAttribute("povereniciBrojac6", povereniciBrojac6);
        model.addAttribute("povereniciBrojac7", povereniciBrojac7);
        model.addAttribute("povereniciBrojacSuma", povereniciBrojac1 + povereniciBrojac2 + povereniciBrojac3 + povereniciBrojac4 + povereniciBrojac5 + povereniciBrojac6);

/////////////////////////////////////////////////////////////////SEGMENT ZA POVERENIKE CIVILNE ZASTITE  

/////////////////////////////////////////////////////////////////SEGMENT ZA JEDINICE CIVILNE ZASTITE  
        TableDefinitionId tableDefinitionIdJedinice = new TableDefinitionId(6004);
        DynamicTable tabelaJedinice = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdJedinice, client); //poverenici 6003
       TableColumn vrstaJedinice = tableColumnService.findOne(new TableColumnId(60042));
        TableColumn jediniceKolona2 = tableColumnService.findOne(new TableColumnId(60044));
        TableColumn jediniceKolona3 = tableColumnService.findOne(new TableColumnId(60045));
        TableColumn jediniceKolona4 = tableColumnService.findOne(new TableColumnId(60046));
        TableColumn jediniceKolona5 = tableColumnService.findOne(new TableColumnId(60047));
        
        

        Integer jediniceBrojac1 = 0;
        Integer jediniceBrojac2 = 0;
        Integer jediniceBrojac3 = 0;
        Integer jediniceBrojac4 = 0;
        Integer jediniceBrojac5 = 0;
        Integer jediniceBrojac6 = 0;
        Integer jediniceBrojac7 = 0;
        Integer jediniceBrojac8 = 0;
        Integer jediniceBrojac9 = 0;
        Integer jediniceBrojac10 = 0;
        
                for (DynamicRow red : tabelaJedinice.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().equals(vrstaJedinice)) {

                    if (data.getValue().equals("600041")) {
                        jediniceBrojac1++;
                          for (DynamicData data2prolaz : red.getData()) {//drugi put prolazimo kroz red kad znamo koji je tip jedinice da saberemo ostale kolone
                          if (data2prolaz.getColumn().equals(jediniceKolona2)) {
                             jediniceBrojac2+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                              if (data2prolaz.getColumn().equals(jediniceKolona3)) {
                             jediniceBrojac3+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                 if (data2prolaz.getColumn().equals(jediniceKolona4)) {
                             jediniceBrojac4+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                    if (data2prolaz.getColumn().equals(jediniceKolona5)) {
                             jediniceBrojac5+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                          }
                        
                    }// cuvamo broj razlicitih unosa

                    if (data.getValue().equals("600042")) {
                        jediniceBrojac6++;
                                   for (DynamicData data2prolaz : red.getData()) {//drugi put prolazimo kroz red kad znamo koji je tip jedinice da saberemo ostale kolone
                          if (data2prolaz.getColumn().equals(jediniceKolona2)) {
                             jediniceBrojac7+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                              if (data2prolaz.getColumn().equals(jediniceKolona3)) {
                             jediniceBrojac8+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                 if (data2prolaz.getColumn().equals(jediniceKolona4)) {
                             jediniceBrojac9+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                                    if (data2prolaz.getColumn().equals(jediniceKolona5)) {
                             jediniceBrojac10+=Integer.parseInt(data2prolaz.getValue()); 
                          }
                          }
                    }
                };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }
        model.addAttribute("jediniceBrojac1", jediniceBrojac1);
        model.addAttribute("jediniceBrojac2", jediniceBrojac2);
        model.addAttribute("jediniceBrojac3", jediniceBrojac3);
        model.addAttribute("jediniceBrojac4", jediniceBrojac4);
        model.addAttribute("jediniceBrojac5", jediniceBrojac5);
        model.addAttribute("jediniceBrojac6", jediniceBrojac6);
        model.addAttribute("jediniceBrojac7", jediniceBrojac7);
        model.addAttribute("jediniceBrojac8", jediniceBrojac8);
        model.addAttribute("jediniceBrojac9", jediniceBrojac9);
        model.addAttribute("jediniceBrojac10", jediniceBrojac10);
        
/////////////////////////////////////////////////////////////////SEGMENT ZA JEDINICE CIVILNE ZASTITE  

/////////////////////////////////////////////////////////////////SEGMENT ZA SITUACIONE CENTRE
  TableDefinitionId tableDefinitionIdSCentar = new TableDefinitionId(6001);
        DynamicTable tabelaSCentar = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdSCentar, client);
        model.addAttribute("scentarRedovi", tabelaSCentar.getRows());
        
        
        TableColumn csentarKolona1 = tableColumnService.findOne(new TableColumnId(60011));
        TableColumn csentarKolona2 = tableColumnService.findOne(new TableColumnId(60012));
        TableColumn csentarKolona3 = tableColumnService.findOne(new TableColumnId(60013));
        TableColumn csentarKolona4 = tableColumnService.findOne(new TableColumnId(60014));
         TableColumn csentarKolona5 = tableColumnService.findOne(new TableColumnId(60015));
     
       
        model.addAttribute("scentar1", csentarKolona1);
        model.addAttribute("scentar2", csentarKolona2);
        model.addAttribute("scentar3", csentarKolona3);
        model.addAttribute("scentar4", csentarKolona4);
        model.addAttribute("scentar5", csentarKolona5);
/////////////////////////////////////////////////////////////////SEGMENT ZA SITUACIONE CENTRE


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MERE CIVILNE ZASTITE
     Integer evakuacija1=0;
        Integer evakuacija2=0;
        Integer evakuacija3=0;
        Integer evakuacija4=0;
        Integer evakuacija5=0;
        Integer evakuacija6=0;
       
         Integer zbirnjavanje1=0;
         Integer zbirnjavanje2=0;
         Integer zbirnjavanje3=0;
         Integer zbirnjavanje4=0;
         Integer zbirnjavanje5=0;
         Integer zbirnjavanje6=0;
         Integer zbirnjavanje7=0;
         
         Integer prvaPomoc1=0;
         Integer prvaPomoc2=0;
         Integer prvaPomoc3=0;
         Integer prvaPomoc4=0;
         Integer prvaPomoc5=0;
         Integer prvaPomoc6=0;
         Integer prvaPomoc7=0;
         Integer prvaPomoc8=0;
         Integer prvaPomoc9=0;
         Integer prvaPomoc10=0;
         Integer prvaPomoc11=0;
         Integer prvaPomoc12=0;
         
         Integer asanacija1=0;
         Integer asanacija2=0;
         Integer asanacija3=0;
         Integer asanacija4=0;
         Integer asanacija5=0;

/////////////////////////////////////////////////////////////////SEGMENT ZA EVAKUACIJU
TableDefinitionId tableDefinitionIdEvakuacija = new TableDefinitionId(5001);
DynamicTable tabelaEvakuacija = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdEvakuacija, client);
        for (DynamicRow red : tabelaEvakuacija.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().getOrder()==14) {
                        evakuacija1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==15) {
                        evakuacija2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==16) {
                        evakuacija3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==17) {
                        evakuacija4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==18) {
                        evakuacija5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==19) {
                        evakuacija6 += Integer.parseInt(data.getValue());
                    };
                  
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("evakuacija1", evakuacija1);
model.addAttribute("evakuacija2", evakuacija2);
model.addAttribute("evakuacija3", evakuacija3);
model.addAttribute("evakuacija4", evakuacija4);
model.addAttribute("evakuacija5", evakuacija5);
model.addAttribute("evakuacija6", evakuacija6);


/////////////////////////////////////////////////////////////////SEGMENT ZA EVAKUACIJU
/////////////////////////////////////////////////////////////////SEGMENT ZA ZBRINJAVANJE
  TableDefinitionId tableDefinitionIdZbrinjavanje = new TableDefinitionId(5002);
        DynamicTable tabelaZbrinjavanje = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdZbrinjavanje, client);


for (DynamicRow red : tabelaZbrinjavanje.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
                    //prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
                    if (data.getColumn().getOrder()==14) {
                        zbirnjavanje1 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==15) {
                        zbirnjavanje2 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==16) {
                        zbirnjavanje3 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==17) {
                        zbirnjavanje4 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==18) {
                        zbirnjavanje5 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==19) {
                        zbirnjavanje6 += Integer.parseInt(data.getValue());
                    };
                    if (data.getColumn().getOrder()==20) {
                        zbirnjavanje7 += Integer.parseInt(data.getValue());
                    };
                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("zbirnjavanje1", zbirnjavanje1);
model.addAttribute("zbirnjavanje2", zbirnjavanje2);
model.addAttribute("zbirnjavanje3", zbirnjavanje3);
model.addAttribute("zbirnjavanje4", zbirnjavanje4);
model.addAttribute("zbirnjavanje5", zbirnjavanje5);
model.addAttribute("zbirnjavanje6", zbirnjavanje6);
model.addAttribute("zbirnjavanje7", zbirnjavanje7);
/////////////////////////////////////////////////////////////////SEGMENT ZA ZBRINJAVANJE
/////////////////////////////////////////////////////////////////SEGMENT ZA PRVU POMOC

  TableDefinitionId tableDefinitionIdPrvaPomoc = new TableDefinitionId(5003);
        DynamicTable tabelaPrvaPomoc = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdPrvaPomoc, client);


for (DynamicRow red : tabelaPrvaPomoc.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
//prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
if (data.getColumn().getOrder()==14) {
prvaPomoc1 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==15) {
prvaPomoc2 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==16) {
prvaPomoc3 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==17) {
prvaPomoc4 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==18) {
prvaPomoc5 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==19) {
prvaPomoc6 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==20) {
prvaPomoc7 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==21) {
prvaPomoc8 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==22) {
prvaPomoc9 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==23) {
prvaPomoc10 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==24) {
prvaPomoc11 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==25) {
prvaPomoc12 += Integer.parseInt(data.getValue());
};

                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("prvaPomoc1", prvaPomoc1);
model.addAttribute("prvaPomoc2", prvaPomoc2);
model.addAttribute("prvaPomoc3", prvaPomoc3);
model.addAttribute("prvaPomoc4", prvaPomoc4);
model.addAttribute("prvaPomoc5", prvaPomoc5);
model.addAttribute("prvaPomoc6", prvaPomoc6);
model.addAttribute("prvaPomoc7", prvaPomoc7);
model.addAttribute("prvaPomoc8", prvaPomoc8);
model.addAttribute("prvaPomoc9", prvaPomoc9);
model.addAttribute("prvaPomoc10", prvaPomoc10);
model.addAttribute("prvaPomoc11", prvaPomoc11);
model.addAttribute("prvaPomoc12", prvaPomoc12);


/////////////////////////////////////////////////////////////////SEGMENT ZA PRVU POMOC
/////////////////////////////////////////////////////////////////SEGMENT ZA ASANACIJU
 TableDefinitionId tableDefinitionIdAsanacija = new TableDefinitionId(5004);
        DynamicTable tabelaAsanacija = dynamicTableService.findByTableDefinitionIdAndClient(tableDefinitionIdAsanacija, client);


for (DynamicRow red : tabelaAsanacija.getRows()) {//prolazimo kroz sve redove tabele da bi sumirali vrednosti
            for (DynamicData data : red.getData()) {
                try {
//prolazimo kroz svaki unos u svakom redu da proverimo da li je to onaj unos koji nas zanima
if (data.getColumn().getOrder()==14) {
asanacija1 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==15) {
asanacija2 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==16) {
asanacija3 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==17) {
asanacija4 += Integer.parseInt(data.getValue());
};
if (data.getColumn().getOrder()==18) {
asanacija5 += Integer.parseInt(data.getValue());
};

                } catch (Exception e) {
                    System.out.println("neki input nije brojka");
                }
            }

        }

model.addAttribute("asanacija1", asanacija1);
model.addAttribute("asanacija2", asanacija2);
model.addAttribute("asanacija3", asanacija3);
model.addAttribute("asanacija4", asanacija4);
model.addAttribute("asanacija5", asanacija5);

/////////////////////////////////////////////////////////////////SEGMENT ZA ASANACIJU

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MERE CIVILNE ZASTITE
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MATERIJALNO TEHNICKA SREDSTVA

        List<DetaljiMTS> listaKartonaMTS = detaljiMTSService.findAllByClient(client);
        model.addAttribute("listaKartonaMTS", listaKartonaMTS);
       
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////SEGMENT ZA MATERIJALNO TEHNICKA SREDSTVA

        return "client/kartonSubjekta/jedinstveniPregledZbirnihObrazaca";
    }
    
    @Autowired
    private DetaljiMTSService detaljiMTSService;
    @Autowired
    private DynamicTableService dynamicTableService;
    @Autowired
    private TableColumnService tableColumnService;
}
