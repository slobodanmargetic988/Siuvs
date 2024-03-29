/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.Zanimanja;

public interface ZanimanjaRepository extends JpaRepository<Zanimanja, Integer> {

    Zanimanja findFirstByNaziv(String name);

    List<Zanimanja> findAllByOrderByNazivAsc();

    List<Zanimanja> findByNaziv(String name);

}
