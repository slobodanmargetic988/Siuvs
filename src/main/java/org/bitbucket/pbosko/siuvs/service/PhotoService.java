package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.model.Photo;
import org.bitbucket.pbosko.siuvs.valueObject.PhotoId;

import java.util.List;

public interface PhotoService {

    void save(Client client, Page page, String title, String filename);

    List<Photo> findByClientAndPage(Client client, Page page);

    String findFileNameById(PhotoId photoId);

    void delete(Client client, PhotoId photoId);

}
