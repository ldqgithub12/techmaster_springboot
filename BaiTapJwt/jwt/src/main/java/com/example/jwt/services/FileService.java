package com.example.jwt.services;

import com.example.jwt.entity.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    private final Path rootPath = Paths.get("uploads");

    public FileService() {
        createFolder(rootPath.toString());
    }

    private void createFolder(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    public FileResponse uploadFile(MultipartFile file) {
        validateFile(file);

        String fileId = UUID.randomUUID().toString();
        Path filePath = rootPath.resolve(fileId);
        File fileUpload = new File(filePath.toString());

        try (OutputStream outStream = new FileOutputStream(fileUpload)) {
            outStream.write(file.getBytes());
            return new FileResponse("/api/v1/files/" + fileId);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi trong quá trình upload file");
        }
        // TODO : Kiểm tra lại sao lại không được
//        try {
//            file.transferTo(fileUpload);
//            return new FileResponse("/api/v1/files/" + fileId);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException("Lỗi trong quá trình upload file");
//        }
    }

    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // Tên file không được trống
        if(fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("Tên file không hợp lệ");
        }

        // Type file có nằm trong ds cho phép hay không
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new RuntimeException("Type file không hợp lệ");
        }

        // Kích thước size có trong phạm vi cho phép không
        double fileSize = (double) (file.getSize() / 1_048_576);
        if(fileSize > 2) {
            throw new RuntimeException("Size file không được vượt quá 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }

    public byte[] readFile(String id) {
        Path filePath = rootPath.resolve(id);
        File file = new File(filePath.toString());

        if(!file.exists()) {
            throw new RuntimeException("Not found file id = " + id);
        }

        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi trong quá trình đọc file");
        }
    }
}