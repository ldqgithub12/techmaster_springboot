package com.example.demo_User_Management.controller;

import com.example.demo_User_Management.model.response.FileResponse;
import com.example.demo_User_Management.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    //1. Upload file
    @PostMapping("/files")
    public ResponseEntity<?> upLoadFile(@ModelAttribute("file")MultipartFile file){
        FileResponse fileResponse = fileService.uploadFile(file);
        return ResponseEntity.ok(fileResponse);
    }
    @GetMapping("/files/{id}")
    public ResponseEntity<?> readFile(@PathVariable String id){
        byte[] bytes = fileService.readFile(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}
