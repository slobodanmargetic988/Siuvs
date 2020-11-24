package slobodan.siuvs2.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.IstorijaNotifikacija;

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
    public void save(IstorijaNotifikacija istorijaNotifikacija) {
       istorijaNotifikacijaRepository.save(istorijaNotifikacija);
    }



}
