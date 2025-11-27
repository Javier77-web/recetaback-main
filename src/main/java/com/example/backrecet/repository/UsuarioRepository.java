package com.example.backrecet.repository;

import com.example.backrecet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.recetas WHERE u.id = ?1")
    Optional<Usuario> findUsuarioWithRecetas(Integer id);
}
