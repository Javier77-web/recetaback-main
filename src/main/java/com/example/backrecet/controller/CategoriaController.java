package com.example.backrecet.controller;

import com.example.backrecet.model.Categoria;
import com.example.backrecet.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable Integer id) {
        return categoriaService.buscarPorId(id).orElse(null);
    }

    @GetMapping("/nombre/{nombre}")
    public Categoria obtenerPorNombre(@PathVariable String nombre) {
        return categoriaService.buscarPorNombre(nombre).orElse(null);
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        return categoriaService.guardar(categoria);
    }

    @PatchMapping("/{id}")
    public Categoria actualizarParcial(@PathVariable Integer id, @RequestBody Categoria cambios) {
        return categoriaService.actualizarParcial(id, cambios).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
    }
}
