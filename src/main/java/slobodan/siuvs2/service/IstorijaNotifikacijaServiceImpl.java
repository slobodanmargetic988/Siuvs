package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.IstorijaNotifikacija;
import slobodan.siuvs2.model.User;

import slobodan.siuvs2.repository.IstorijaNotifikacijaRepository;

@Service
public class IstorijaNotifikacijaServiceImpl implements IstorijaNotifikacijaService {

    @Autowired
    private IstorijaNotifikacijaRepository istorijaNotifikacijaRepository;

    @Override
    public IstorijaNotifikacija findById(Integer istorijaNotifikacijaId) {
        return istorijaNotifikacijaRepository.findById(istorijaNotifikacijaId);
    }
    
   @Override
    public List<IstorijaNotifikacija> findAllByCreatedBy(Integer id){
     return  istorijaNotifikacijaRepository.findAllByCreatedBy( id);
    };
    
       @Override
    public List<IstorijaNotifikacija> findAllByCreatedByIn( List<User> useriistogservisa){
     return istorijaNotifikacijaRepository.findAllByCreatedByIn(useriistogservisa);
    };
    
    
    @Override
    public void save(IstorijaNotifikacija istorijaNotifikacija) {
       istorijaNotifikacijaRepository.save(istorijaNotifikacija);
    }

    
     @Override
    public  List<IstorijaNotifikacija> findAllBy(){
    return istorijaNotifikacijaRepository.findAll();
    }
   @Override
    public List<IstorijaNotifikacija> findAllByClient(Client client){
        return istorijaNotifikacijaRepository.findAllByClient(client);
    }

  @Override
    public Integer countLastMonthPoslateForClientID(Integer client_id){
     return istorijaNotifikacijaRepository.countLastMonthPoslateForClientID(client_id);
    } 
      @Override
    public List<IstorijaNotifikacija> selectLastMonthPoslateForClientID(Integer client_id){
     return istorijaNotifikacijaRepository.selectLastMonthPoslateForClientID(client_id);
    }
}
