package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Repository("photoRepository")
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findByClientAndPageOrderByIdAsc(Client client, Page page);

    void deleteById(Integer id);

}
