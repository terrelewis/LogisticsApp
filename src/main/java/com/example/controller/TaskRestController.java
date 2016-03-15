package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.entity.Task;
import com.example.entity.User;
import com.example.repository.Clientrepository;
import com.example.repository.TaskRepository;
import com.example.repository.Userrepository;

@RestController
@RequestMapping("/{ClientId}/tasks")
public class TaskRestController {

	private final Clientrepository clientRepository;
	
	private final TaskRepository taskRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable("ClientId") String clientId, @RequestBody Task input) {
		this.validateUser(clientId);
		return this.clientRepository
				.findByUsername(clientId)
				.map(client -> {
					Task result = taskRepository.save(new Task(client, client, input.toAddress, input.fromAddress, input.externalId));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getInternalId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();

	}
	
	@RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
	Task readTask(@PathVariable("ClientId") String clientId, @PathVariable("taskId") Long taskId) {
		this.validateUser(clientId);
		return this.taskRepository.findOne(taskId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Task> readTasks(@PathVariable("ClientId") String ClientId) {
		this.validateUser(ClientId);
		return this.taskRepository.findByownerUsername(ClientId);
	}
	
	//delete_single_task
		@RequestMapping(value = "/{taskId}", method = RequestMethod.DELETE)
		    public ResponseEntity<Task> deleteTask(@PathVariable("taskId") Long taskid) {
		        //System.out.println("Fetching & Deleting User with id " + id);
		 
		        Task task = taskRepository.findOne(taskid);
		        if (task == null) {
		            System.out.println("Unable to delete. User with id " + taskid + " not found");
		            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		        }
		 
		        taskRepository.delete(taskid);
		        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
		    }
		
		//delete_all_users
		@RequestMapping(method = RequestMethod.DELETE)
	    public ResponseEntity<Task> deleteAllTasks(@PathVariable("ClientId") String ClientId) {
	        //System.out.println("Deleting All Users");
			this.validateUser(ClientId);
	        taskRepository.deleteAll();
	        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	    }
		
	
	@Autowired
	TaskRestController(TaskRepository taskRepository,
			Clientrepository clientRepository) {
		this.taskRepository = taskRepository;
		this.clientRepository = clientRepository;
	}

	private void validateUser(String ClientId) {
		this.clientRepository.findByUsername(ClientId).orElseThrow(
				() -> new UserNotFoundException(ClientId));
	}
	
}
