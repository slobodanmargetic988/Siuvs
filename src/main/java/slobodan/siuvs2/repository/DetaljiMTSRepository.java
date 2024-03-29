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



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.DetaljiMTS;
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.VlasnikMTS;


@Repository("detaljikMTSRepository")
public interface DetaljiMTSRepository extends JpaRepository<DetaljiMTS, Integer> {



    List<DetaljiMTS> findAllBy();
    
   
  DetaljiMTS findOne(Integer detaljiMTSId);
  
    List <DetaljiMTS> findAllByClient(Client client);

   List < DetaljiMTS> findAllByVlasnikMTS(VlasnikMTS vlasnikMTS);

        List <DetaljiMTS> findAllByOrgJedinicaMTS(OrgJedinicaMTS orgJedinicaMTS);

        
    
          }