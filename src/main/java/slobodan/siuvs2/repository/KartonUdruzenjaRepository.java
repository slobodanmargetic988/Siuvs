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

import slobodan.siuvs2.model.KartonSubjekti;
import slobodan.siuvs2.model.KartonUdruzenja;

@Repository("kartonUdruzenjaRepository")
public interface KartonUdruzenjaRepository extends JpaRepository<KartonUdruzenja, Integer> {



    List<KartonUdruzenja> findAllBy();
    
  KartonUdruzenja findOne(Integer kartonUdruzenjaId);
  
    List<KartonUdruzenja> findAllByClientOrderByPunnazivAsc(Client client);
    
          }