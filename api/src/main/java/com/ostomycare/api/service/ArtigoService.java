package com.ostomycare.api.service;

import com.ostomycare.api.infraestructure.entity.ArtigoEntity;
import com.ostomycare.api.infraestructure.entity.ImagemEntity;
import com.ostomycare.api.infraestructure.reposity.ArtigoRepository;
import com.ostomycare.api.infraestructure.reposity.ImagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService {
    private final ArtigoRepository artigoRepository;
    private final ImagemRepository imagemRepository;

    public  ArtigoService(ArtigoRepository artigoRepository, ImagemRepository imagemRepository) {
        this.artigoRepository = artigoRepository;
        this.imagemRepository= imagemRepository;
    }

    public ArtigoEntity salvar(ArtigoEntity artigo) {

        ImagemEntity imagem = imagemRepository.findById(artigo.getImagem().getId())
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));
        artigo.setImagem(imagem);
        return artigoRepository.save(artigo);
    }

    public List<ArtigoEntity> listar() {
        return artigoRepository.findAll();
    }

}
