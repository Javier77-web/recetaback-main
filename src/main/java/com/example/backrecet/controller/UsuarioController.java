package com.example.backrecet.controller;

import com.example.backrecet.model.Usuario;
import com.example.backrecet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id).orElse(null);
    }

    @GetMapping("/email/{email}")
    public Usuario obtenerPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.guardar(usuario));
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("EMAIL_DUPLICADO")) {
                return ResponseEntity.badRequest().body("El correo ya está registrado");
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            usuario.setId(id);
            return ResponseEntity.ok(usuarioService.guardar(usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcial(@PathVariable Integer id, @RequestBody Usuario cambios) {
        try {
            return ResponseEntity.ok(usuarioService.actualizarParcial(id, cambios));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }
}
