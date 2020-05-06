/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author sloba
 */


import java.util.List;
import slobodan.siuvs2.model.Provincija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvincijaRepository extends JpaRepository<Provincija, Integer>{
    Provincija findFirstByName(String name);
    List<Provincija> findAllByOrderByNameAsc();
}
