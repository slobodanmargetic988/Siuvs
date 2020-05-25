/*
 * 
 */
package slobodan.siuvs2.controller.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DokumentService;
import slobodan.siuvs2.service.InternationalAgreementsFactory;
import slobodan.siuvs2.service.InternationalAgreementsService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.PublicPolicyDocumentsFactory;
import slobodan.siuvs2.service.PublicPolicyDocumentsService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.TasksFactory;
import slobodan.siuvs2.service.TasksService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DokumentID;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.PhotoId;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;
import slobodan.siuvs2.valueObject.TasksID;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class OverviewController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TasksService tasksService;
    @Autowired
    private PublicPolicyDocumentsService PPDService;
    @Autowired
    private InternationalAgreementsService IAService;
    @Autowired
    private TasksFactory tasksFactory;
    @Autowired
    private PublicPolicyDocumentsFactory PPDFactory;
    @Autowired
    private InternationalAgreementsFactory IAFactory;
        @Autowired
    private PageService pageService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private DokumentService dokumentService;

    @GetMapping(value = "/overview")
    public String list(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PageId pageId;
        pageId=new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        model.addAttribute("client", clientService.findOne(client.getClientId()));
                 List<Dokument> dokumentlist= new ArrayList();
         dokumentlist=dokumentService.findAllByClientId(client);
         List<PublicPolicyDocuments> PPDlist= new ArrayList();
         List<InternationalAgreements> IAlist= new ArrayList();
       
        for (Dokument i : dokumentlist) {
        if (i.getIa()!=null){IAlist.add(i.getIa());}
        else{PPDlist.add(i.getPpd());
        }
        }
          model.addAttribute("docPPDlist", PPDlist);
          model.addAttribute("docIAlist", IAlist);
        
        return "client/overview/view";
    }

    @GetMapping(value = "/overview/editPhoto")
    public String editPhoto(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PageId pageId;
        pageId=new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        model.addAttribute("client", clientService.findOne(client.getClientId()));
        return "client/overview/editPhoto";
    }

    @GetMapping(value = "/overview/newNadleznost")
    public String newNadleznost( final Model model) {
        return "/client/overview/newNadleznost";
    }
    @GetMapping(value = "/overview/newIA")
    public String newIA( final Model model) {
        
        return "/client/overview/newIA";
    }
    @GetMapping(value = "/overview/newPPD")
    public String newPPD( final Model model) {
       
        return "/client/overview/newPPD";
    }
    
    @PostMapping(value = "/overview/newNadleznost")
    public String savenewNadleznost(
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        Tasks task=tasksFactory.empty(name);
        try{
        tasksService.save(task);
        client.getOpstina().getTasks().add(task);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/newNadleznost";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/newIA")
    public String savenewIA(
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        
        InternationalAgreements ia=IAFactory.empty(name);
        try{
        IAService.save(ia);
        client.getOpstina().getInternationalAgreements().add(ia);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/newIA";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/newPPD")
    public String savenewPPD(
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PublicPolicyDocuments ppd=PPDFactory.empty(name);
        try{
        PPDService.save(ppd);
        client.getOpstina().getPublicPolicyDocuments().add(ppd);
        clientService.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "Jавни документ документ успешно додат!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/newPPD";
            }
        return "redirect:/client/overview";
    }
    @GetMapping(value = "/overview/deleteNadleznost/{Nadleznostid}")
    public String deleteNadleznost(
            @PathVariable final TasksID Nadleznostid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        Tasks task=tasksService.findOne(Nadleznostid);
        try{        
        client.getOpstina().getTasks().remove(task);
        clientService.save(client);
        tasksService.delete(Nadleznostid);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    @GetMapping(value = "/overview/deleteIA/{IAid}")
    public String deleteIA(
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        InternationalAgreements ia=IAService.findOne(IAid);
        try{        
        client.getOpstina().getInternationalAgreements().remove(ia);
        clientService.save(client);
        Dokument dokument=dokumentService.findByIa(ia);
            dokumentService.delete(client, dokument.getId());
        IAService.delete(IAid);
        redirectAttributes.addFlashAttribute("successMessage", "Mеђународни документ успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    
    @GetMapping(value = "/overview/deletePPD/{PPDid}")
    public String deletePPD(
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        try{        
        client.getOpstina().getPublicPolicyDocuments().remove(PPD);
        clientService.save(client);
        Dokument dokument=dokumentService.findByPpd(PPD);
            dokumentService.delete(client, dokument.getId());
        PPDService.delete(PPDid);
        redirectAttributes.addFlashAttribute("successMessage", "Jавни документ успешно уклоњен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    
    
      @GetMapping(value = "/overview/editNadleznost/{Nadleznostid}")
    public String editNadleznost(
            @PathVariable final TasksID Nadleznostid,           
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Tasks task=tasksService.findOne(Nadleznostid);
        model.addAttribute("task", task);
        return "/client/overview/editNadleznost";
    }
    
        @GetMapping(value = "/overview/editIA/{IAid}")
    public String editIA(
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        InternationalAgreements IA = IAService.findOne(IAid);
        model.addAttribute("IA", IA);
        return "/client/overview/editIA";
    }
    @GetMapping(value = "/overview/editPPD/{PPDid}")
    public String editPPD(
            @PathVariable final PublicPolicyDocumentsID PPDid,           
            final RedirectAttributes redirectAttributes,
            final Model model) {
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        model.addAttribute("PPD", PPD);
        return "/client/overview/editPPD";
    }
    @PostMapping(value = "/overview/editNadleznost/{Nadleznostid}")
    public String saveEditNadleznost(
            @PathVariable final TasksID Nadleznostid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        Tasks task=tasksService.findOne(Nadleznostid);
        try{        
        task.setName(name);       
        tasksService.save(task);
        redirectAttributes.addFlashAttribute("successMessage", "Надлежност и делокруг успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/editIA/{IAid}")
    public String saveEditIA(
            @PathVariable final InternationalAgreementsID IAid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        InternationalAgreements IA=IAService.findOne(IAid);
        try{        
        IA.setName(name);       
        IAService.save(IA);
        redirectAttributes.addFlashAttribute("successMessage", "Међународни документ успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    @PostMapping(value = "/overview/editPPD/{PPDid}")
    public String saveeditPPD(
            @PathVariable final PublicPolicyDocumentsID PPDid,
            @RequestParam(name = "name") String name,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        PublicPolicyDocuments PPD=PPDService.findOne(PPDid);
        try{        
        PPD.setName(name);       
        PPDService.save(PPD);
        redirectAttributes.addFlashAttribute("successMessage", "Јавни документ успешно измењен!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview";
            }
        return "redirect:/client/overview";
    }
    
    @PostMapping(value = "/overview/uploadPhoto")
    public String uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {
        PageId pageId;
        pageId=new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив слике");
            return "redirect:/client/overview"+ "/editPhoto";
        }  else {      
            try{
            String filename = storageService.store(file, client.getClientId());
            photoService.save(client, page, title, filename);
            redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно сачувана!");
            return "redirect:/client/overview" ;
            }catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/client/overview";
        }
        }
    }
    @PostMapping(value = "/overview/deletePhoto/{photoId}")
    public String deletePhoto(
            @PathVariable final PhotoId photoId,
            final RedirectAttributes redirectAttributes
    ) {
        PageId pageId;
        pageId=new PageId(1);//using pageid=1 to store photos for client page
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        String filename = photoService.findFileNameById(photoId);
        photoService.delete(client, photoId);
        storageService.delete(client.getClientId(), filename);
        redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно обрисана!");
        return "redirect:/client/overview";
    }
    @GetMapping(value = "/overview/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(
           
            @PathVariable final PhotoId photoId
    ) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        
        String filename = photoService.findFileNameById(photoId);
        Resource file = storageService.loadAsResource(client.getClientId(), filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
    @GetMapping(value = "/overview/uploadDokumentIA/{IAid}")
    public String uploadDokumentIA(
            
            @PathVariable final InternationalAgreementsID IAid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        model.addAttribute("client", client);
        InternationalAgreements IA = IAService.findOne(IAid);
        model.addAttribute("IA", IA);
        return "/client/overview/uploadDokumentIA";
    }

    @GetMapping(value = "/overview/uploadDokumentPPD/{PPDid}")
    public String uploadDokumentPPD(
            
            @PathVariable final PublicPolicyDocumentsID PPDid,
            final RedirectAttributes redirectAttributes,
            final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        model.addAttribute("client", client);
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        model.addAttribute("PPD", PPD);
        return "/client/overview/uploadDokumentPPD";
    }

    @PostMapping(value = "/overview/uploadDokumentIA/{IAid}")
    public String uploadDokumentIA(
           
            @PathVariable final InternationalAgreementsID IAid,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {

        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив документа");
            return "redirect:/client/overview/uploadDokumentIA/" + IAid;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
            InternationalAgreements ia = IAService.findOne(IAid);
            try {
                Dokument existing = dokumentService.findByIa(ia);
                if (existing != null) {
                    DokumentID dokumentId = new DokumentID(existing.getId());
                    String filename = dokumentService.findFileNameById(dokumentId);
                    dokumentService.delete(client, existing.getId());
                    storageService.delete(client.getClientId(), filename);
                }
                String filename = storageService.storeDOC(file, client.getClientId());
                dokumentService.save(client, ia, title, filename);

                redirectAttributes.addFlashAttribute("successMessage", "Документ је успешно сачуван!");
                return "redirect:/client/overview";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/edit";
            }
        }
    }

    @PostMapping(value = "/overview/uploadDokumentPPD/{PPDid}")
    public String uploadDokumentPPD(
          
            @PathVariable final PublicPolicyDocumentsID PPDid,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {

        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив документа");
            return "redirect:/client/overview/uploadDokumentPPD/" + PPDid;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
            PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
            try {
                Dokument existing = dokumentService.findByPpd(PPD);
                if (existing != null) {
                    DokumentID dokumentId = new DokumentID(existing.getId());
                    String filename = dokumentService.findFileNameById(dokumentId);
                    dokumentService.delete(client, existing.getId());
                    storageService.delete(client.getClientId(), filename);
                }
                String filename = storageService.storeDOC(file, client.getClientId());
                dokumentService.save(client, PPD, title, filename);

                redirectAttributes.addFlashAttribute("successMessage", "Документ је успешно сачуван!");
                return "redirect:/client/overview/";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/client/overview/edit";
            }
        }
    }

    @RequestMapping(value = "/overview/downloadPPD/{PPDid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody getSteamingFile(
          
            @PathVariable final PublicPolicyDocumentsID PPDid,
            HttpServletResponse response,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        try {
        PublicPolicyDocuments PPD = PPDService.findOne(PPDid);
        Dokument dokument = dokumentService.findByPpd(PPD);
       /* if(dokument==null){
        redirectAttributes.addFlashAttribute("errorMessage","Тражени документ није уплоадован");
                return null;
        }*/
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        DokumentID dokumentId = new DokumentID(dokument.getId());
        String filename = dokumentService.findFileNameById(dokumentId);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
        InputStream inputStream = new FileInputStream(new File(storageService.load(client.getClientId(), filename).toString()));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return null;
            }
    }
        @RequestMapping(value = "/overview/downloadIA/{IAid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody getSteamingFile(
          
            @PathVariable final InternationalAgreementsID IAid,
            HttpServletResponse response,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();       
        Client client=clientService.findOne(currentUser.getClient().getClientId());
        InternationalAgreements IA = IAService.findOne(IAid);
        Dokument dokument = dokumentService.findByIa(IA);
        DokumentID dokumentId = new DokumentID(dokument.getId());
        String filename = dokumentService.findFileNameById(dokumentId);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
        InputStream inputStream = new FileInputStream(new File(storageService.load(client.getClientId(), filename).toString()));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return null;
            }
    }
}
