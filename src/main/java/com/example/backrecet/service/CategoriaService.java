package com.example.backrecet.service;

import com.example.backrecet.model.Categoria;
import com.example.backrecet.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public Optional<Categoria> actualizarParcial(Integer id, Categoria cambios) {
        return categoriaRepository.findById(id).map(categoria -> {
            if (cambios.getNombre() != null) categoria.setNombre(cambios.getNombre());
            return categoriaRepository.save(categoria);
        });
    }
}
