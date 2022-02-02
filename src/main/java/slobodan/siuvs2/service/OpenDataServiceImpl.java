package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slobodan.siuvs2.model.OpenData;
import slobodan.siuvs2.repository.OpenDataRepository;

@Service
public class OpenDataServiceImpl implements OpenDataService {

    @Autowired
    private OpenDataRepository openDataRepository;

    @Override
    public OpenData findFirstByToken(String token) {
        return openDataRepository.findFirstByToken(token);
    }

    @Override
    public void save(OpenData data) {
        openDataRepository.save(data);
    }


}
