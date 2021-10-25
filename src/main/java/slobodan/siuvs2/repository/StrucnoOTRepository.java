/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.comba
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.StrucnoOT;

public interface StrucnoOTRepository extends JpaRepository<StrucnoOT, Integer> {

    List<StrucnoOT> findAllByClient(Client client);
      StrucnoOT findFirstByClient(Client client);
}
