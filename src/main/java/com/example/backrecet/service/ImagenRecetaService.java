package com.example.backrecet.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.backrecet.model.ImagenReceta;
import com.example.backrecet.model.Receta;
import com.example.backrecet.repository.ImagenRecetaRepository;
import com.example.backrecet.repository.RecetaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ImagenRecetaService {

    @Autowired
    private ImagenRecetaRepository imagenRecetaRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private Cloudinary cloudinary;

    public List<ImagenReceta> listarTodas() {
        return imagenRecetaRepository.findAll();
    }

    public Optional<ImagenReceta> buscarPorId(Integer id) {
        return imagenRecetaRepository.findById(id);
    }

    public Optional<ImagenReceta> buscarPorRecetaId(Integer recetaId) {
        return imagenRecetaRepository.findByRecetaId(recetaId);
    }

    public ImagenReceta guardarImagen(MultipartFile file, Integer recetaId) throws Exception {
        Optional<Receta> recetaOpt = recetaRepository.findById(recetaId);
        if (!recetaOpt.isPresent()) return null;
        Receta receta = recetaOpt.get();
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "recetas")
        );
        String url = uploadResult.get("secure_url").toString();
        ImagenReceta imagen = imagenRecetaRepository.findByRecetaId(recetaId)
                .orElse(new ImagenReceta());
        imagen.setUrl(url);
        imagen.setReceta(receta);
        return imagenRecetaRepository.save(imagen); 
    }

    public void eliminar(Integer id) {
        imagenRecetaRepository.deleteById(id);
    }

    public Optional<ImagenReceta> actualizarParcial(Integer id, ImagenReceta cambios) {
        return imagenRecetaRepository.findById(id).map(imagen -> {

            if (cambios.getUrl() != null) 
                imagen.setUrl(cambios.getUrl());

            if (cambios.getReceta() != null) 
                imagen.setReceta(cambios.getReceta());

            return imagenRecetaRepository.save(imagen);
        });
    }
}
