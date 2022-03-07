/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.AssessmentCID;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.DokumentCID;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.OpenData;
import slobodan.siuvs2.service.AssessmentService;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DokumentService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.OpenDataService;
import slobodan.siuvs2.service.PhotoService;
import slobodan.siuvs2.service.StorageService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.DokumentID;
import slobodan.siuvs2.valueObject.InternationalAgreementsID;
import slobodan.siuvs2.valueObject.PhotoId;
import slobodan.siuvs2.valueObject.TableDefinitionId;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@RestController
public class OpenDataRestController {
//za clijenta ciklicno ucitava usera pa clienta pa u krug
    
    //za dokumente proveriti da li su migrirani kako treba
    
    
    @Autowired
    private ClientService clientService;
    @Autowired
    private DynamicTableService dynamicTableService;
    @Autowired
    private OpenDataService openDataService;
    @Autowired
    private AssessmentService assessmentService;
     @Autowired
    private DokumentService dokumentService;
     @Autowired
    private StorageService storageService;
     @Autowired
    private PhotoService photoService;

    @GetMapping("/openData/{openDataClientId}/{tableId}/{apiToken}")
    DynamicTable serveTable(//serves entire dynamic table based on table definition id and client id if token sent with the request exists. 
            //token is given to entities who are allowed to call this service
            @PathVariable final Integer openDataClientId,
            @PathVariable final TableDefinitionId tableId,
            @PathVariable final String apiToken) {
        OpenData token = openDataService.findFirstByToken(apiToken);
        if (token != null) {
            Client client = clientService.findFirstByOpendataid(openDataClientId);
            DynamicTable tabela = dynamicTableService.findByTableDefinitionIdAndClient(tableId, client);
            return tabela;
            
        } else {
            return null;
            
            
        }
    }
    
        @GetMapping("/openData/allAssessments/{apiToken}")
    List<AssessmentCID>  serveAllAssessments(//serves entire dynamic table based on table definition id and client id if token sent with the request exists. 
            //token is given to entities who are allowed to call this service
            
            
            @PathVariable final String apiToken) {
        OpenData token = openDataService.findFirstByToken(apiToken);
        if (token != null) {
            List<Assessment> results= assessmentService.findAll();
             List<AssessmentCID>  resultsfinal= new ArrayList();
             
               for (Assessment rezultat: results){
             AssessmentCID temp = new AssessmentCID();
                   temp=  temp.kopiraj(rezultat);
                   resultsfinal.add(temp);
             }
            return resultsfinal;
        } else {
            return null;
        }
    }
    
            @GetMapping("/openData/allDocuments/{apiToken}")
    List<DokumentCID>  serveAllDokuments(//serves entire dynamic table based on table definition id and client id if token sent with the request exists. 
            //token is given to entities who are allowed to call this service
            @PathVariable final String apiToken) {
        OpenData token = openDataService.findFirstByToken(apiToken);
        if (token != null) {
            List<Dokument> results= dokumentService.findAll();
            
             List<DokumentCID> resultsfinal= new ArrayList();
             for (Dokument rezultat: results){
             DokumentCID temp = new DokumentCID();
                   temp=  temp.kopiraj(rezultat);
                   resultsfinal.add(temp);
             }
            return resultsfinal;
        } else {
            return null;
        }
    }
    
            @RequestMapping(value = "/openData/getDocument/{clientId}/{documentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
     StreamingResponseBody serveDocument(
            @PathVariable final ClientId clientId,
            @PathVariable final Integer documentId,
            HttpServletResponse response,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        try {
    
        Dokument dokument = dokumentService.findById(documentId);
       
       String filename= dokument.getFilename();
        response.setContentType("text/html;charset=UTF-8");
        /**/       response.setCharacterEncoding("UTF-8");
 /**/       String encode=dokument.getTitle() + filename.substring(filename.lastIndexOf("."));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(encode, "UTF-8")+ "\"");
   //     response.setHeader("Content-Disposition", "attachment; filename=\"" + dokument.getTitle() + filename.substring(filename.lastIndexOf(".")) + "\"");
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
    
    
    
        @GetMapping(value = "/openData/{clientId}/photo/{photoId}")
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
    // svi asesmenti api DONE
    // slika api po id done
    
    // lista svih dokumenta za sve klijenta api DONE
    // get 1 dokument po id koji bi trebalo da se wrapuje DONE
    
    // test okruzenje1
    

}
