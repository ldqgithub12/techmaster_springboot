package com.example.demo_User_Management.service;

import com.example.demo_User_Management.exception.NotFoundException;
import com.example.demo_User_Management.model.response.FileResponse;
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

    private void createFolder(String path){
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
    }
    public FileResponse uploadFile(MultipartFile file){
        validateFile(file);
        String fileId = UUID.randomUUID().toString();
        Path filePath = rootPath.resolve(fileId);
        File fileUpload = new File(filePath.toString());
        try(OutputStream outputStream = new FileOutputStream(fileUpload)){
            outputStream.write(file.getBytes());
            return new FileResponse("/api/v1/files/"+fileId);
        }catch (IOException ex){
            throw new RuntimeException("Loi trong qua trinh upload file");
        }
    }

    private void validateFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        //Ten file ko dc de trong
        if (filename == null && filename.isEmpty()){
            throw new NotFoundException("Ten file khong hop le");

        }
        //Type file co nam trong danh sach cho phep hay khong
        String fileExtension = getFileExtension(filename);
        if (!checkFileExtension(fileExtension)){
            throw new NotFoundException("Type khong hop le");
        }
        //kich thuoc size trong pham vi cho phep
        double fileSize = (double) (file.getSize()/1048576);
    }
    public String getFileExtension(String filename){
        int lastIndex = filename.lastIndexOf(".");
        if (lastIndex == -1){
            return "";
        }
        return filename.substring(lastIndex+1);
    }
    public boolean checkFileExtension(String FileExtension){
        List<String> fileExtensions = List.of("png","jpg","jpeg");
        return fileExtensions.contains(FileExtension);
    }

    public byte[] readFile(String id) {
        Path filePath = rootPath.resolve(id);
        File file = new File(filePath.toString());
        if(!file.exists()){
            throw new NotFoundException("File khong ton tai");
        }
        try{
            return Files.readAllBytes(filePath);
        }
        catch (IOException ex){
            throw new RuntimeException("Loi trong qua trinh doc file");
        }
    }
}
