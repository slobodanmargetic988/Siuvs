package slobodan.siuvs2.service;


import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.IstorijaNotifikacija;

public interface IstorijaNotifikacijaService {

 IstorijaNotifikacija findById(Integer istorijaNotifikacijaId);
     void save(IstorijaNotifikacija in);
        List<IstorijaNotifikacija> findAllBy();
    List<IstorijaNotifikacija> findAllByClient(Client client);
}
