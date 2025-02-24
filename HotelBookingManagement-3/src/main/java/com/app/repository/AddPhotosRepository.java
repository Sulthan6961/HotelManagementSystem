package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.AddPhotos;

@Repository
public interface AddPhotosRepository extends JpaRepository<AddPhotos, Long> {
   
}
