package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Integer> {

    List<Page> findAllByParentIdNullOrderByOrderAsc();

}
