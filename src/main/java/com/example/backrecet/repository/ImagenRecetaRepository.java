package com.example.backrecet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backrecet.model.ImagenReceta;

@Repository
public interface ImagenRecetaRepository extends JpaRepository<ImagenReceta, Integer>{
    
    Optional<ImagenReceta> findByRecetaId(Integer recetaId);
}
