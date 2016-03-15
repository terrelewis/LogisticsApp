package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.entity.Task;

import java.util.Collection;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long>
{
	Optional<Task> findByInternalId(Long Id);

	Collection<Task> findByownerUsername(String clientId);
	
}
