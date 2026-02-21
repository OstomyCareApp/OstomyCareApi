package com.ostomycare.api.controller;

import com.ostomycare.api.infraestructure.entity.ImagemEntity;
import com.ostomycare.api.service.ImagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/imagem")
public class ImagemController {

    private final ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ImagemEntity> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(imagemService.salvar(file));
    }

    @GetMapping
    public ResponseEntity<List<ImagemEntity>> listar() {
        return ResponseEntity.ok(imagemService.listar());
    }
}