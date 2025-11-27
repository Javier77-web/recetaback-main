package com.example.backrecet.controller;

import com.example.backrecet.model.Receta;
import com.example.backrecet.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public List<Receta> listar() {
        return recetaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Receta obtenerPorId(@PathVariable Integer id) {
        return recetaService.buscarPorId(id).orElse(null);
    }

    @GetMapping("/titulo/{titulo}")
    public List<Receta> buscarPorTitulo(@PathVariable String titulo) {
        return recetaService.buscarPorTitulo(titulo);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Receta> buscarPorUsuario(@PathVariable Integer usuarioId) {
        return recetaService.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Receta> buscarPorCategoria(@PathVariable Integer categoriaId) {
        return recetaService.buscarPorCategoria(categoriaId);
    }

    @GetMapping("/subcategoria/{subcategoriaId}")
    public List<Receta> buscarPorSubcategoria(@PathVariable Integer subcategoriaId) {
        return recetaService.buscarPorSubcategoria(subcategoriaId);
    }

    @PostMapping
    public Receta crear(@RequestBody Receta receta) {
        return recetaService.guardar(receta);
    }

    @PutMapping("/{id}")
    public Receta actualizar(@PathVariable Integer id, @RequestBody Receta receta) {
        receta.setId(id);
        return recetaService.guardar(receta);
    }

    @PatchMapping("/{id}")
    public Receta actualizarParcial(@PathVariable Integer id, @RequestBody Receta cambios) {
        return recetaService.actualizarParcial(id, cambios).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        recetaService.eliminar(id);
    }
}
