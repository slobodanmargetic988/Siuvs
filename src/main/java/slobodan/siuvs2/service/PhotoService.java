package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Photo;
import slobodan.siuvs2.valueObject.OpstinaID;

import java.util.List;

public interface PhotoService {

    void save(Client client, Page page, String title, String filename);

    List<Photo> findByClientAndPage(Client client, Page page);

    String findFileNameById(OpstinaID photoId);

    void delete(Client client, OpstinaID photoId);

}
