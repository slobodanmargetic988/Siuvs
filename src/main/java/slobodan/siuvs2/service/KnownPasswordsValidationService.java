package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class KnownPasswordsValidationService {

    private static List<String> db;

    public KnownPasswordsValidationService(
            @Value("${siuvs.invalidPasswordDbFilePath}") String passwordDbFilePath
    ) throws Exception {
        if (passwordDbFilePath == null) {
            throw new Exception("Password DB file location not configured siuvs.invalidPasswordDbFilePath");
        }
        db = new ArrayList<>();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(passwordDbFilePath));
        } catch (FileNotFoundException e) {
            throw new Exception("Password DB file is missing siuvs.invalidPasswordDbFilePath");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                db.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new Exception("Failed to read Password DB file siuvs.invalidPasswordDbFilePath");
        }
    }

    public boolean contains(String password) {
        for (String line : db) {
            if (line.contains(password)) {
                return true;
            }
        }
        return false;
    }

}
