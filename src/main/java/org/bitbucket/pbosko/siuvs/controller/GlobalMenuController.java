package org.bitbucket.pbosko.siuvs.controller;

import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.service.PageService;
import org.bitbucket.pbosko.siuvs.valueObject.PageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@ControllerAdvice
public class GlobalMenuController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ServletContext servletContext;

    @ModelAttribute
    public void generateMenu(Model model) {
        List<Page> globalMenuItems = pageService.getTopLevelItems();
        model.addAttribute("globalMenuItems", globalMenuItems);
    }

    @ModelAttribute
    public void determineSelectedMenuPages(
            HttpServletRequest request,
            Model model
    ) {
        List<Page> breadCrumbs = new ArrayList<>();
        List<Integer> selectedPages = new ArrayList<Integer>();
        PageId pageId = this.guessCurrentPageId(request);
        if (pageId != null) {
            Page page = pageService.findOne(pageId);
            while(page != null) {
                breadCrumbs.add(page);
                selectedPages.add(page.getId());
                page = page.getParent();
            }
        }
        Collections.reverse(breadCrumbs);
        model.addAttribute("globalMenuSelectedPages", selectedPages);
        model.addAttribute("breadCrumbsPages", breadCrumbs);
    }

    private PageId guessCurrentPageId(HttpServletRequest request) {
        PageId pageId = null;
        String requestAttributesParam = "org.springframework.web.servlet.HandlerMapping.uriTemplateVariables";
        HashMap<String,String> pathAttributes = (HashMap<String, String>)request.getAttribute(requestAttributesParam);
        for(Map.Entry<String,String> entry : pathAttributes.entrySet()) {
            if (entry.getKey().equals("pageId")) {
                pageId = new PageId(entry.getValue());
                break;
            }
        }
        return pageId;
    }

}
