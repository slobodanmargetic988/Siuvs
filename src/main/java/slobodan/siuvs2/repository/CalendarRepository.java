package slobodan.siuvs2.repository;

import slobodan.siuvs2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import slobodan.siuvs2.model.Calendar;

@Repository("calendarRepository")
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    List<Calendar> findAllBy();

    List<Calendar> findAllByClient(Client client);

    Calendar findById(Integer id);
Calendar findFirstByClientAndDokument(Client client, String dokument);
}
