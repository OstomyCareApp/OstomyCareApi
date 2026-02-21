package com.ostomycare.api.controller;

import com.ostomycare.api.infraestructure.entity.ArtigoEntity;
import com.ostomycare.api.service.ArtigoService;
import com.ostomycare.api.service.ImagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/artigo")
public class ArtigoController {

    private final ArtigoService artigoService;
    private  final ImagemService imagemService;

    public ArtigoController(ArtigoService artigoService, ImagemService imagemService) {
        this.artigoService = artigoService;
        this.imagemService = imagemService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ArtigoEntity> cadastrar(@RequestBody ArtigoEntity artigo) {
        return ResponseEntity.ok(artigoService.salvar(artigo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ArtigoEntity>> listar() {
        return ResponseEntity.ok(artigoService.listar());
    }
}
