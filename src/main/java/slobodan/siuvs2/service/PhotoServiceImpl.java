package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Photo;
import slobodan.siuvs2.repository.PhotoRepository;
import slobodan.siuvs2.valueObject.PhotoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public void save(Client client, Page page, String title, String filename) {
        Photo photo = new Photo();
        photo.setClient(client);
        photo.setPage(page);
        photo.setTitle(title);
        photo.setFilename(filename);
        photoRepository.save(photo);
    }

    @Override
    public List<Photo> findByClientAndPage(Client client, Page page) {
        return photoRepository.findByClientAndPageOrderByIdAsc(client, page);
    }

    @Override
    public String findFileNameById(PhotoId photoId) {
        Photo photo = photoRepository.findOne(photoId.getValue());
        return photo.getFilename();
    }

    @Override
    @Transactional
    public void delete(Client client, PhotoId photoId) {
        Photo photo = photoRepository.findOne(photoId.getValue());
        if (photo.getClient().getId() == client.getId()) {
            photoRepository.deleteById(photoId.getValue());
        }
    }
}