package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import slobodan.siuvs2.valueObject.ClientId;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import org.springframework.core.io.FileSystemResource;

public interface StorageService {

    /**
     * @param file uploaded file
     * @param clientId so that file can be stored in it's subdirectory
     * @return name of the stored file
     */
    String store(MultipartFile file, ClientId clientId);
    String storeDOC(MultipartFile file, ClientId clientId);
    Path load(ClientId clientId, String filename);

    Resource loadAsResource(ClientId clientId, String filename);
FileSystemResource loadAsFSResource(ClientId clientId, String filename);
    void delete(ClientId clientId, String filename);
 
}
