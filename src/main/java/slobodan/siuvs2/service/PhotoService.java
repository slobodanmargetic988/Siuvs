package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Photo;
import java.util.List;
import slobodan.siuvs2.valueObject.PhotoId;

public interface PhotoService {

    void save(Client client, Page page, String title, String filename);

    List<Photo> findByClientAndPage(Client client, Page page);

    String findFileNameById(PhotoId photoId);

    void delete(Client client, PhotoId photoId);

}
