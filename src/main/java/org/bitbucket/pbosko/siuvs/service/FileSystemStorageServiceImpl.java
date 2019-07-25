package org.bitbucket.pbosko.siuvs.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bitbucket.pbosko.siuvs.valueObject.ClientId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageServiceImpl implements StorageService {

    @Value("${siuvs.fileUploadLocation}")
    private String fileUploadLocation;

    private Path rootLocation;

    @Override
    public String store(MultipartFile file, ClientId clientId) throws StorageException {
        init(clientId);

        String filename = file.getOriginalFilename() + " " + clientId.toString() + " " + System.currentTimeMillis();
        InputStream stream = new ByteArrayInputStream(filename.getBytes(StandardCharsets.UTF_8));
        try {
            filename = DigestUtils.md5DigestAsHex(stream) + ".jpg";
        } catch (IOException e) {
            throw new StorageException("Неуспешно процесирање имена фајла");
        }
        try {
            if (file.isEmpty()) {
                throw new StorageException("Фајл је празан");
            }
            if (!file.getContentType().equals("image/jpeg")) {
                throw new StorageException("Невалидан тип фајла");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Неуспешно снимање фајла", e);
        }
        return filename;
    }

    @Override
    public Path load(ClientId clientId, String filename) {
        init(clientId);
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(ClientId clientId, String filename) {
        try {
            Path file = load(clientId, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(ClientId clientId, String filename) {
        Path filePath = Paths.get(fileUploadLocation + "/" + clientId + "/" + filename);
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new StorageException("Не постоји такав фајл.", e);
        }
    }

    private void init(ClientId clientId) {
        rootLocation = Paths.get(fileUploadLocation + "/" + clientId);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Креирање директоријума неуспешно. Молимо Вас, контактирајте администратора.", e);
        }
    }
}
