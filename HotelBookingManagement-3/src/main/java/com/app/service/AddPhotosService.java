package com.app.service;

import com.app.entity.AddPhotos;
import com.app.repository.AddPhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddPhotosService {

    @Autowired
    private AddPhotosRepository addPhotosRepository;

    
    @Value("${file.upload-dir}")
    private String uploadDir;

    
    public AddPhotos saveAddPhotos(AddPhotos addPhotos) {
        return addPhotosRepository.save(addPhotos); 
    }

    
    public List<String> uploadPhotos(List<MultipartFile> files) throws IOException {
        List<String> uploadedFiles = new ArrayList<>();

        
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        
        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDir + fileName);
            file.transferTo(destinationFile);  
            uploadedFiles.add(uploadDir + fileName);  
        }

        return uploadedFiles; 
    }


	public AddPhotos getAddPhotosById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<AddPhotos> getAllAddPhotos() {
		// TODO Auto-generated method stub
		return null;
	}
}
