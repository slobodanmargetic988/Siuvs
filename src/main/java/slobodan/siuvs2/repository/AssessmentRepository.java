package slobodan.siuvs2.repository;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    Assessment findFirstByClientAndPage(Client client, Page page);
    Assessment findFirstByClient(Client client);
    
}
