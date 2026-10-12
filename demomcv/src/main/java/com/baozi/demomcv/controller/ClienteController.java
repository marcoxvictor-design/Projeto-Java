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

import com.baozi.demomcv.model.Cliente;
import com.baozi.demomcv.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente novoCliente) {
        Cliente clienteSalvo = clienteRepo.save(novoCliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> consultarClientes() {
        List<Cliente> clientesCadastrados = clienteRepo.findAll();
        return ResponseEntity.ok(clientesCadastrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable Long id) {
        Cliente encontrado = clienteRepo.findById(id).orElse(null);

        if (encontrado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(encontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        if (!clienteRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}