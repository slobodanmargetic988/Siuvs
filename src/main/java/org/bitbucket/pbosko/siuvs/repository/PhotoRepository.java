package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.bitbucket.pbosko.siuvs.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("photoRepository")
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findByClientAndPageOrderByIdAsc(Client client, Page page);

    void deleteById(Integer id);

}
