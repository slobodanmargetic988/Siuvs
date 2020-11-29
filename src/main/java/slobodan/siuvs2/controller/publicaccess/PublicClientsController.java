package slobodan.siuvs2.controller.publicaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.PublicPolicyDocuments;
import slobodan.siuvs2.service.DokumentService;
import slobodan.siuvs2.service.InternationalAgreementsFactory;
import slobodan.siuvs2.service.InternationalAgreementsService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.PublicPolicyDocumentsFactory;
import slobodan.siuvs2.service.PublicPolicyDocumentsService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.valueObject.DokumentID;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.PhotoId;
import slobodan.siuvs2.valueObject.PublicPolicyDocumentsID;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/publicaccess")
public class PublicClientsController {

    @Autowired
    private ClientService clientService;


    @Autowired
    private PageService pageService;
    @Autowired
    private PhotoService photoService;

    @Autowired
    private DokumentService dokumentService;

    @GetMapping(value = "/publicaccess")
    public String publicacess() {
        return "publicaccess/home";
    }

    @GetMapping(value = "/publicaccess/home")
    public String publicacess2() {
        return "publicaccess/home";
    }

    @GetMapping(value = "/clients")
    public String list(final Model model, @PageableDefault final Pageable pageable) {
        model.addAttribute("clients", clientService.findAllOrderByActiveDescNameAsc(pageable));
          model.addAttribute("allclients",clientService.findAllByOrderByNameAsc());
        return "publicaccess/clients";
    }

    @GetMapping(value = "/clients/{clientId}")
    public String view(@PathVariable final ClientId clientId, final Model model) {
        Client client = clientService.findOne(clientId);
        model.addAttribute("client", client);
        
        
        PageId pageId;
        pageId = new PageId(1);//using pageid=1 to store photos for client page
        Page page = pageService.findOne(pageId);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
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
        return "publicaccess/clientmenu";
    }

 @Autowired
    private StorageService storageService;

    @GetMapping(value = "clients/{clientId}/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final PhotoId photoId
    ) {
        String filename = photoService.findFileNameById(photoId);
        Resource file = storageService.loadAsResource(clientId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
      @Autowired
    private PublicPolicyDocumentsService PPDService;
    @Autowired
    private InternationalAgreementsService IAService;
    
    @RequestMapping(value = "/clients/{clientId}/downloadPPD/{PPDid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody getSteamingFile(
            @PathVariable final ClientId clientId,
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
        DokumentID dokumentId = new DokumentID(dokument.getId());
        String filename = dokumentService.findFileNameById(dokumentId);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
        InputStream inputStream = new FileInputStream(new File(storageService.load(clientId, filename).toString()));

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
        @RequestMapping(value = "/clients/{clientId}/downloadIA/{IAid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody getSteamingFile(
            @PathVariable final ClientId clientId,
            @PathVariable final InternationalAgreementsID IAid,
            HttpServletResponse response,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        try {
        InternationalAgreements IA = IAService.findOne(IAid);
        Dokument dokument = dokumentService.findByIa(IA);
        DokumentID dokumentId = new DokumentID(dokument.getId());
        String filename = dokumentService.findFileNameById(dokumentId);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
        InputStream inputStream = new FileInputStream(new File(storageService.load(clientId, filename).toString()));

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
