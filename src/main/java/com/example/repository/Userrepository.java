package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User;

import java.util.Collection;

public interface Userrepository extends JpaRepository<User, Long> {
	
	Collection<User> findByClientUsername(String username);
	//SELECT b from User b WHERE b.client.username = :username
	
	
	

}
