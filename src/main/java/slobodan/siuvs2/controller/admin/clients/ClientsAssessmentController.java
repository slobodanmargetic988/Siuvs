package slobodan.siuvs2.controller.admin.clients;

import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.AssessmentFactory;
import slobodan.siuvs2.service.AssessmentService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.shared.AssesmentHelper1;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.OpstinaID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/admin/clients")
public class ClientsAssessmentController {

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

    @GetMapping(value = "/{clientId}/assessment/{pageId}")
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
        return "admin/clients/assessment/view";
    }

    @GetMapping(value = "/{clientId}/assessment/{pageId}/edit")
    public String edit(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Client client = clientService.findOne(clientId);
        Page page = pageService.findOne(pageId);
        Assessment newAssessment = assessmentService.findOne(client, page);
        if (newAssessment == null) {
            newAssessment = assessmentFactory.empty(client, page);
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("newAssessment", newAssessment);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        return "admin/clients/assessment/edit";
    }

    @PostMapping(value = "/{clientId}/assessment/{pageId}/edit")
    public String save(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @ModelAttribute("newAssessment") final Assessment assessment,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом снимања процене!");
            return "redirect:/admin/clients/" + clientId + "/assessment/" + pageId + "/edit";
        } else {
            Client client = clientService.findOne(clientId);
            Page page = pageService.findOne(pageId);
            Assessment currentAssessment = assessmentService.findOne(client, page);
            if (currentAssessment != null) {
                currentAssessment.copyDataFieldsFromAssessment(assessment);
            } else {
                assessment.setClient(client);
                assessment.setPage(page);
                currentAssessment = assessment;
            }
            assessmentService.save(currentAssessment);
            redirectAttributes.addFlashAttribute("successMessage", "Процена је успешно сачувана!");
            return "redirect:/admin/clients/" + clientId + "/assessment/" + pageId;
        }
    }

    @PostMapping(value = "/{clientId}/assessment/{pageId}/upload")
    public String upload(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {
        Page page = pageService.findOne(pageId);
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив слике");
            return "redirect:/admin/clients/" + clientId + "/assessment/" + pageId + "/edit";
        } else if (!page.isAttachablePhotos()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ова страна не подржава унос слика");
            return "redirect:/admin/clients/" + clientId + "/assessment/" + pageId + "/edit";
        } else {
            Client client = clientService.findOne(clientId);
            String filename = storageService.store(file, client.getClientId());
            photoService.save(client, pageService.findOne(pageId), title, filename);
            redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно сачувана!");
            return "redirect:/admin/clients/" + clientId + "/assessment/" + pageId;
        }
    }

    @GetMapping(value = "/{clientId}/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final OpstinaID photoId
    ) {
        String filename = photoService.findFileNameById(photoId);
        Resource file = storageService.loadAsResource(clientId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @PostMapping(value = "/{clientId}/assessment/{pageId}/delete/{photoId}")
    public String deletePhoto(
            @PathVariable final ClientId clientId,
            @PathVariable final PageId pageId,
            @PathVariable final OpstinaID photoId,
            final RedirectAttributes redirectAttributes
    ) {
        Client client = clientService.findOne(clientId);
        String filename = photoService.findFileNameById(photoId);
        photoService.delete(client, photoId);
        storageService.delete(clientId, filename);
        redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно обрисана!");
        return "redirect:/admin/clients/" + clientId + "/assessment/" + pageId;
    }

}
