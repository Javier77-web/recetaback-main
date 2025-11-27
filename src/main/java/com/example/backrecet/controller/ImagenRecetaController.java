package com.example.backrecet.controller;

import com.example.backrecet.model.ImagenReceta;
import com.example.backrecet.service.ImagenRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/imagenesReceta")
public class ImagenRecetaController {

    @Autowired
    private ImagenRecetaService imagenRecetaService;

    @GetMapping
    public java.util.List<ImagenReceta> listar() {
        return imagenRecetaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ImagenReceta obtenerPorId(@PathVariable Integer id) {
        return imagenRecetaService.buscarPorId(id).orElse(null);
    }

    @GetMapping("/receta/{recetaId}")
    public ImagenReceta obtenerPorReceta(@PathVariable Integer recetaId) {
        return imagenRecetaService.buscarPorRecetaId(recetaId).orElse(null);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("recetaId") Integer recetaId) {
        try {
            ImagenReceta imagen = imagenRecetaService.guardarImagen(file, recetaId);
            if (imagen == null) {
                return ResponseEntity.badRequest().body("Error: la receta no existe o la imagen no se pudo subir.");
            }
            return ResponseEntity.status(201).body(imagen);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PatchMapping("/{id}")
    public ImagenReceta actualizarParcial(@PathVariable Integer id, @RequestBody ImagenReceta cambios) {
        return imagenRecetaService.actualizarParcial(id, cambios).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        imagenRecetaService.eliminar(id);
    }
}
