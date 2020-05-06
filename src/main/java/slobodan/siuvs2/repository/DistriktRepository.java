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
import slobodan.siuvs2.model.Distrikt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistriktRepository extends JpaRepository<Distrikt, Integer>{
    Distrikt findFirstByName(String name);
      List<Distrikt> findAllByOrderByNameAsc();
    
}
