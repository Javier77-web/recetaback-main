package com.example.backrecet.repository;

import com.example.backrecet.model.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {

    List<Subcategoria> findByCategoriaId(Integer categoriaId);
    Subcategoria findByNombre(String nombre);
}
