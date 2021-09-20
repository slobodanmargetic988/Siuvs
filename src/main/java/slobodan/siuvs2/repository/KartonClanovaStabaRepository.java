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
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.KartonClanovaStaba;

import slobodan.siuvs2.model.KartonSubjekti;

@Repository("KartonClanovaStabaRepository")
public interface KartonClanovaStabaRepository extends JpaRepository<KartonClanovaStaba, Integer> {

    List<KartonClanovaStaba> findAllBy();

    @Override
    KartonClanovaStaba findOne(Integer kartonClanovaStabaId);

    List<KartonClanovaStaba> findAllByClientOrderByPunoimeAsc(Client client);

}
