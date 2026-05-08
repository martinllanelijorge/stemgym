package com.proyecto.stemgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.stemgym.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
