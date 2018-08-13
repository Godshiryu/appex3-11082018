package com.example.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.domain.Cliente;
@Repository
public interface IClientRepository extends JpaRepository <Cliente, Long> {
	Cliente findByName(String name);
}
