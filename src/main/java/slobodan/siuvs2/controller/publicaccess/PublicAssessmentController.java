package slobodan.siuvs2.controller.publicaccess;

import slobodan.siuvs2.model.*;
import slobodan.siuvs2.service.*;
import slobodan.siuvs2.shared.AssesmentHelper1;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.PhotoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
//@RequestMapping(value = "/publicaccess/home")
public class PublicAssessmentController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PageService pageService;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private AssessmentFactory assessmentFactory;

    @Autowired
    private StorageService storageService;

    @Autowired
    private PhotoService photoService;

    @GetMapping(value = "/publicaccess/home/{clientId}/assessment/{pageId}")
    public String assessment(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Assessment assessment = assessmentService.findOne(client, page);
        if (assessment == null) {
            assessment = assessmentFactory.empty(client, page);
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("assessment", assessment);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        model.addAttribute("vrsta_opasnosti", AssesmentHelper1.getOpasnost(pageId.getValue()));
        return "publicaccess/assesmentview";
    }
 @GetMapping(value = "/publicaccess/privacypolicy")
    public String privacyPolicy() {

            return "publicaccess/privacypolicy";
        
    }
}
