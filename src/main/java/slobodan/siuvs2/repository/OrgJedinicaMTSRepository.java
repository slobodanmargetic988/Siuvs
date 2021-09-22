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
import slobodan.siuvs2.model.GrupaMTS;
import slobodan.siuvs2.model.OrgJedinicaMTS;
import slobodan.siuvs2.model.VlasnikMTS;


@Repository("orgJedinicaMTSRepository")
public interface OrgJedinicaMTSRepository extends JpaRepository<OrgJedinicaMTS, Integer> {



    List<OrgJedinicaMTS> findAllBy();
    
   
  OrgJedinicaMTS findOne(Integer orgJedinicaMTSId);
  
  List <OrgJedinicaMTS> findAllByClient(Client client);

  public    List <OrgJedinicaMTS> findAllByVlasnikMTS(VlasnikMTS vlasnikMTS);

    
          }