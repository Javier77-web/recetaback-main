package com.example.backrecet.service;

import com.example.backrecet.model.Receta;
import com.example.backrecet.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> listarTodas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> buscarPorId(Integer id) {
        return recetaRepository.findById(id);
    }

    public List<Receta> buscarPorTitulo(String titulo) {
        return recetaRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Receta> buscarPorUsuario(Integer usuarioId) {
        return recetaRepository.findByUsuarioId(usuarioId);
    }

    public List<Receta> buscarPorCategoria(Integer categoriaId) {
        return recetaRepository.findByCategoriaId(categoriaId);
    }

    public List<Receta> buscarPorSubcategoria(Integer subcategoriaId) {
        return recetaRepository.findBySubcategoriaId(subcategoriaId);
    }

    public Receta guardar(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminar(Integer id) {
        recetaRepository.deleteById(id);
    }

    public Optional<Receta> actualizarParcial(Integer id, Receta cambios) {
        return recetaRepository.findById(id).map(receta -> {
            if (cambios.getTitulo() != null) receta.setTitulo(cambios.getTitulo());
            if (cambios.getDescripcion() != null) receta.setDescripcion(cambios.getDescripcion());
            if (cambios.getIngredientes() != null) receta.setIngredientes(cambios.getIngredientes());
            if (cambios.getInstrucciones() != null) receta.setInstrucciones(cambios.getInstrucciones());
            return recetaRepository.save(receta);
        });
    }
}
