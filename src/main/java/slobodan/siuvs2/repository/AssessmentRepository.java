package slobodan.siuvs2.repository;

import java.util.List;
import slobodan.siuvs2.model.Assessment;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import slobodan.siuvs2.model.AssessmentCID;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

    Assessment findFirstByClientAndPage(Client client, Page page);
    Assessment findFirstByClient(Client client);
    
    @Query(value = "SELECT assessment_id, client_id, page_id, consequences, probability, text_pojavljivanje, text_prostorna_dimenzija, text_intenzitet, text_vreme, text_tok, text_trajanje, text_rana_najava, text_pripremljenost, text_uticaj, text_generisanje_drugih_opasnosti, text_referentni_incidenti, text_informisanje_javnosti, text_buduce_informacije  from `assessment`")
    List<Object[]> vratisveskraceno();
    
    
}
