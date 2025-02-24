package com.app.controller;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.AddPhotos;
import com.app.service.AddPhotosService;

@RestController
@RequestMapping("/photos")
public class AddPhotosController {

    @Autowired
    private AddPhotosService addPhotosService;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhotos(
            @RequestParam("photos") List<MultipartFile> photos,
            @RequestParam("description") String description,
            @RequestParam("price") Double price) {
        
        
        if (photos.isEmpty()) {
            return ResponseEntity.badRequest().body("No photos were uploaded.");
        }
        
       
        if (photos.size() != 5) {
            return ResponseEntity.badRequest().body("You must upload exactly 5 photos.");
        }

        
        long maxSize = 5 * 1024 * 1024; 
        for (MultipartFile photo : photos) {
            if (photo.getSize() > maxSize) {
                return ResponseEntity.badRequest().body("One or more photos exceed the allowed file size.");
            }
        }

        String uploadDir = "C:/Users/91966/Desktop/MyPhotos/";

        List<String> uploadedPhotoPaths = new ArrayList<>();

        try {
           
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (MultipartFile photo : photos) {
                
                String filename = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
                File file = new File(uploadDir + filename);

                photo.transferTo(file);

                uploadedPhotoPaths.add(file.getAbsolutePath());
            }

            AddPhotos addPhotos = new AddPhotos();
            addPhotos.setPhotos(uploadedPhotoPaths);  
            addPhotos.setDescription(description);    
            addPhotos.setPrice(price);                
            
            addPhotosService.saveAddPhotos(addPhotos);

            return ResponseEntity.ok("Photos uploaded successfully!");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading photos: " + e.getMessage());
        }
    }

//    @GetMapping("/photos/{id}")
//    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
//        try {
//           
//            AddPhotos addPhotos = addPhotosService.getAddPhotosById(id);
//
//            if (addPhotos != null && !addPhotos.getPhotos().isEmpty()) {
//               
//                String photoPath = addPhotos.getPhotos().get(0);
//
//               
//                File photoFile = new File(photoPath);
//                if (!photoFile.exists()) {
//                    return ResponseEntity.status(404).body(null);  
//                }
//
//                byte[] photoBytes = Files.readAllBytes(photoFile.toPath());
//
//                
//                return ResponseEntity.ok()
//                        //.contentType(MediaType.IMAGE_JPEG)
//                        .body(photoBytes);
//            } else {
//                return ResponseEntity.status(404).body(null); 
//            }
//
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null); 
//        }
//    }
    
    @GetMapping("/photos")
    public ResponseEntity<List<String>> getUploadedPhotos() {
        try {
            
            List<AddPhotos> allPhotos = addPhotosService.getAllAddPhotos();
            List<String> photoPaths = new ArrayList<>();
            for (AddPhotos addPhotos : allPhotos) {
                photoPaths.addAll(addPhotos.getPhotos());  
            }

            return ResponseEntity.ok(photoPaths);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


}
