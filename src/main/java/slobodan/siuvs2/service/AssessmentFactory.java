package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;

public interface AssessmentFactory {

    Assessment empty(Client client, Page page);

}
