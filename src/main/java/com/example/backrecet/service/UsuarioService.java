package com.example.backrecet.service;

import com.example.backrecet.model.Usuario;
import com.example.backrecet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear o actualizar usuario con validación de email
    public Usuario guardar(Usuario usuario) {

        // Si es un usuario nuevo
        if (usuario.getId() == null) {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new IllegalArgumentException("EMAIL_DUPLICADO");
            }
        }

        // Si está actualizando
        else {
            Usuario existente = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new IllegalArgumentException("USUARIO_NO_ENCONTRADO"));

            if (!existente.getEmail().equals(usuario.getEmail()) &&
                    usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new IllegalArgumentException("EMAIL_DUPLICADO");
            }
        }

        return usuarioRepository.save(usuario);
    }

    // CRUD
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> actualizarParcial(Integer id, Usuario cambios) {
        return usuarioRepository.findById(id).map(usuario -> {

            if (cambios.getNombre() != null)
                usuario.setNombre(cambios.getNombre());

            if (cambios.getEmail() != null) {
                if (!usuario.getEmail().equals(cambios.getEmail()) &&
                        usuarioRepository.existsByEmail(cambios.getEmail())) {
                    throw new IllegalArgumentException("EMAIL_DUPLICADO");
                }
                usuario.setEmail(cambios.getEmail());
            }

            if (cambios.getPassword() != null)
                usuario.setPassword(cambios.getPassword());

            return usuarioRepository.save(usuario);
        });
    }
}
