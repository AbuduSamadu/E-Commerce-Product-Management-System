package abudu.product.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil{

    public static String saveFile(String uploadDir, String fileName, MultipartFile file) throws IOException {
        File uploadPath = new File(uploadDir);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String filePath = uploadDir + File.separator + fileName;
        file.transferTo(new File(filePath));
        return filePath;
    }
}
