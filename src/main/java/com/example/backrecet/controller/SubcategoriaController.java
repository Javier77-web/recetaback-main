package com.example.backrecet.controller;

import com.example.backrecet.model.Subcategoria;
import com.example.backrecet.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaService subcategoriaService;

    @GetMapping
    public List<Subcategoria> listar() {
        return subcategoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Subcategoria obtenerPorId(@PathVariable Integer id) {
        return subcategoriaService.buscarPorId(id).orElse(null);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Subcategoria> obtenerPorCategoria(@PathVariable Integer categoriaId) {
        return subcategoriaService.buscarPorCategoria(categoriaId);
    }

    @PostMapping
    public Subcategoria crear(@RequestBody Subcategoria subcategoria) {
        return subcategoriaService.guardar(subcategoria);
    }

    @PutMapping("/{id}")
    public Subcategoria actualizar(@PathVariable Integer id, @RequestBody Subcategoria subcategoria) {
        subcategoria.setId(id);
        return subcategoriaService.guardar(subcategoria);
    }

    @PatchMapping("/{id}")
    public Subcategoria actualizarParcial(@PathVariable Integer id, @RequestBody Subcategoria cambios) {
        return subcategoriaService.actualizarParcial(id, cambios).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        subcategoriaService.eliminar(id);
    }
}
