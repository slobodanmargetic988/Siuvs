/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DynamicTable;
import slobodan.siuvs2.model.OpenData;
import slobodan.siuvs2.service.ClientService;
import slobodan.siuvs2.service.DynamicTableService;
import slobodan.siuvs2.service.OpenDataService;
import slobodan.siuvs2.valueObject.ClientId;
import slobodan.siuvs2.valueObject.TableDefinitionId;

/**
 *
 * @author Slobodan
 */
@RestController
public class OpenDataRestController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private DynamicTableService dynamicTableService;
    @Autowired
    private OpenDataService openDataService;

    @GetMapping("/openData/{openDataClientId}/{tableId}/[apiToken}")
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

}
