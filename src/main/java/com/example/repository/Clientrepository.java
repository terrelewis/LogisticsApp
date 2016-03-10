package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Client;

import java.util.Optional;

public interface Clientrepository extends JpaRepository<Client, Long>
{
	Optional<Client> findByUsername(String username);
	//select a from Client a where a.username = :usernam
}
