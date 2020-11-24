package slobodan.siuvs2.service;


import slobodan.siuvs2.model.IstorijaNotifikacija;

public interface IstorijaNotifikacijaService {

 IstorijaNotifikacija findById(Integer istorijaNotifikacijaId);
     void save(IstorijaNotifikacija in);
}
