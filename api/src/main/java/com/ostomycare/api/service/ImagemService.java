package com.ostomycare.api.service;

import com.ostomycare.api.infraestructure.entity.ImagemEntity;
import com.ostomycare.api.infraestructure.reposity.ImagemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImagemService {
    @Value("${app.upload.dir}")
    private String uploadDir;
    private final ImagemRepository imagemRepository;

    public ImagemService(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    public ImagemEntity salvar(MultipartFile file) throws IOException {
        String nomeArquivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path caminho = Paths.get(uploadDir, nomeArquivo);
        Files.createDirectories(caminho.getParent());
        Files.write(caminho, file.getBytes());

        ImagemEntity imagem = new ImagemEntity();
        imagem.setNomeArquivo(nomeArquivo);
        imagem.setCaminho(nomeArquivo);

        return imagemRepository.save(imagem);
    }

    public List<ImagemEntity> listar() {
        return imagemRepository.findAll();
    }
}