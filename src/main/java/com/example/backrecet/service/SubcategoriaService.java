package com.example.backrecet.service;

import com.example.backrecet.model.Subcategoria;
import com.example.backrecet.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubcategoriaService {

    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

    public List<Subcategoria> listarTodas() {
        return subcategoriaRepository.findAll();
    }

    public Optional<Subcategoria> buscarPorId(Integer id) {
        return subcategoriaRepository.findById(id);
    }

    public List<Subcategoria> buscarPorCategoria(Integer categoriaId) {
        return subcategoriaRepository.findByCategoriaId(categoriaId);
    }

    public Subcategoria guardar(Subcategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }

    public void eliminar(Integer id) {
        subcategoriaRepository.deleteById(id);
    }

    public Optional<Subcategoria> actualizarParcial(Integer id, Subcategoria cambios) {
        return subcategoriaRepository.findById(id).map(subcategoria -> {
            if (cambios.getNombre() != null) subcategoria.setNombre(cambios.getNombre());
            return subcategoriaRepository.save(subcategoria);
        });
    }
}
