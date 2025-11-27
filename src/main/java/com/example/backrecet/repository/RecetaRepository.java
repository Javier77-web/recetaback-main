package com.example.backrecet.repository;

import com.example.backrecet.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    List<Receta> findByTituloContainingIgnoreCase(String titulo);
    List<Receta> findByUsuarioId(Integer usuarioId);
    List<Receta> findByCategoriaId(Integer categoriaId);
    List<Receta> findBySubcategoriaId(Integer subcategoriaId);
}
