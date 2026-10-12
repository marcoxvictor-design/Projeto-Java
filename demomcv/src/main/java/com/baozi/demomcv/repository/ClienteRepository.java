package com.baozi.demomcv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baozi.demomcv.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}