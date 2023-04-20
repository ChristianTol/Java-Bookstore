package workshop.chris.security.bookstore.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            File file = new File(uploadDir + fileName);
            FileCopyUtils.copy(multipartFile.getBytes(), file);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }
}
