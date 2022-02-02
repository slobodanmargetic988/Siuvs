package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import slobodan.siuvs2.model.Dokument;
import slobodan.siuvs2.model.InternationalAgreements;
import slobodan.siuvs2.model.PublicPolicyDocuments;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Repository("dokumentRepository")
public interface DokumentRepository extends JpaRepository<Dokument, Integer> {

    List<Dokument> findByClientOrderByIdAsc(Client client);
    Dokument findById(Integer dokumentId);
    Dokument findByIa(InternationalAgreements ia);
    Dokument findByPpd(PublicPolicyDocuments ppd);
    void deleteById(Integer id);

}
