package com.baozi.demomcv.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baozi.demomcv.model.Produto;
import com.baozi.demomcv.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepo;

    public ProdutoController(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    @PostMapping
    public ResponseEntity<Produto> registrarProduto(@RequestBody Produto produto) {
        Produto registro = produtoRepo.save(produto);
        return ResponseEntity.ok(registro);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> obterProdutos() {
        return ResponseEntity.ok(produtoRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Long id) {
        return produtoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarProduto(@PathVariable Long id) {
        if (!produtoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}