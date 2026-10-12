package com.baozi.demomcv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baozi.demomcv.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}