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

import com.baozi.demomcv.model.Pedido;
import com.baozi.demomcv.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepo;

    public PedidoController(PedidoRepository pedidoRepo) {
        this.pedidoRepo = pedidoRepo;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoRepo.save(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> consultarPedidos() {
        List<Pedido> registros = pedidoRepo.findAll();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> consultarPedidoPorId(@PathVariable Long id) {
        return pedidoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        if (!pedidoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}