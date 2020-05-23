package slobodan.siuvs2.controller.client;

import slobodan.siuvs2.service.UserService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.service.AssessmentFactory;
import slobodan.siuvs2.service.AssessmentService;
import slobodan.siuvs2.service.PageService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.model.SiuvsUserPrincipal;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Roles;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.valueObject.PageId;
import slobodan.siuvs2.valueObject.OpstinaID;
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
import slobodan.siuvs2.valueObject.PhotoId;

@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
@RequestMapping(value = "/client")
public class AssessmentController {

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
    @Autowired
    private UserService userService;

    @GetMapping(value = "/assessment/{pageId}")
    public String assessment(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        Assessment assessment = assessmentService.findOne(client, page);
        if (assessment == null) {
            assessment = assessmentFactory.empty(client, page);
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("assessment", assessment);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        return "client/assessment/view";
    }

    @GetMapping(value = "/assessment/{pageId}/edit")
    public String edit(
            @PathVariable final PageId pageId,
            final Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        Page page = pageService.findOne(pageId);
        Assessment newAssessment = assessmentService.findOne(client, page);
        if (newAssessment == null) {
            newAssessment = assessmentFactory.empty(client, page);
        }
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("newAssessment", newAssessment);
        model.addAttribute("photos", photoService.findByClientAndPage(client, page));
        return "client/assessment/edit";
    }

    @PostMapping(value = "/assessment/{pageId}/edit")
    public String save(
            @PathVariable final PageId pageId,
            @ModelAttribute("newAssessment") final Assessment assessment,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Грешка приликом снимања процене!");
            return "redirect:/client/assessment/" + pageId + "/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
            Client client = user.getClient();
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
            return "redirect:/client/assessment/" + pageId;
        }
    }

    @PostMapping(value = "/assessment/{pageId}/upload")
    public String upload(
            @PathVariable final PageId pageId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            final RedirectAttributes redirectAttributes
    ) {
        Page page = pageService.findOne(pageId);
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Молимо Вас да унесете назив слике");
            return "redirect:/client/assessment/" + pageId + "/edit";
        } else if (!page.isAttachablePhotos()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ова страна не подржава унос слика");
            return "redirect:/client/assessment/" + pageId + "/edit";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
            Client client = user.getClient();
            if (!userService.hasRole(user, Roles.CLIENT)) {
                redirectAttributes.addFlashAttribute("errorMessage", "Немате права за унос слике");
                return "redirect:/client/assessment/" + pageId;
            }
            String filename = storageService.store(file, client.getClientId());
            photoService.save(client, pageService.findOne(pageId), title, filename);
            redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно сачувана!");
            return "redirect:/client/assessment/" + pageId;
        }
    }

    @GetMapping(value = "/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(@PathVariable final PhotoId photoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        String filename = photoService.findFileNameById(photoId);
        Resource file = storageService.loadAsResource(client.getClientId(), filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @PostMapping(value = "/assessment/{pageId}/delete/{photoId}")
    public String deletePhoto(
            @PathVariable final PageId pageId,
            @PathVariable final PhotoId photoId,
            final RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((SiuvsUserPrincipal) authentication.getPrincipal()).getUser();
        Client client = user.getClient();
        String filename = photoService.findFileNameById(photoId);
        photoService.delete(client, photoId);
        storageService.delete(client.getClientId(), filename);
        redirectAttributes.addFlashAttribute("successMessage", "Слика је успешно обрисана!");
        return "redirect:/client/assessment/" + pageId;
    }

}
