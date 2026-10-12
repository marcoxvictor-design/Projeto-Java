package com.baozi.demomcv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baozi.demomcv.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}