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

import com.example.entity.Client;
import com.example.entity.User;
import com.example.repository.Clientrepository;
import com.example.repository.Userrepository;

@RestController
@RequestMapping("/admin/clients")
class ClientRestController {

	private final Userrepository userRepository1;

	private final Clientrepository clientRepository1;

	@RequestMapping(method = RequestMethod.POST) 
	 public Client createClient(@RequestBody Client client) { 
	  return this.clientRepository1.save( 
	    new Client( 
	     
	      client.getUsername(), 
	     client.getPassword()
	      )); 
	 } 
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	Client readClient(@PathVariable("clientId") Long clientId) {
		//this.validateUser(clientId);
		return this.clientRepository1.findOne(clientId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Client> readClient() {
		//this.validateUser(ClientId);
		return this.clientRepository1.findAll();
	}
	
	
	//patch
/*	@RequestMapping(value = "/{clientId}", method = RequestMethod.PATCH)
	  public ResponseEntity<Client> patchupdateClient(@PathVariable("ClientId") Long clientId, @RequestBody Client client) {
        //System.out.println("Updating User " + id);
         //this.validateUser(clientId);
         
        Client currentUser = clientRepository1.findOne(clientId);
        
         
        if (currentUser==null) {
            System.out.println("User with id " + clientId + " not found");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
 
        
        
        if(client.username!=null)
        currentUser.setUsername(client.username);
       if(client.password!=null)
        currentUser.setPassword(client.password);
      
         
        clientRepository1.save(currentUser);
        return new ResponseEntity<Client>(currentUser, HttpStatus.OK);
   }*/
	
	//updating_user
	   @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT)
	    public ResponseEntity<Client> updateClient(@PathVariable("ClientId") Long clientId, @RequestBody Client client) {
	        //System.out.println("Updating User " + id);
	       Client currentUser=clientRepository1.findOne(clientId);
	        
	         
	        if (currentUser==null) {
	            System.out.println("User with id " + clientId + " not found");
	            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
	        }
	 
	        //currentUser.setID(user.ID);
	        currentUser.setUsername(client.username);
	        currentUser.setPassword(client.password);
	       
	         
	        clientRepository1.saveAndFlush(currentUser);
	        return new ResponseEntity<Client>(currentUser, HttpStatus.OK);
	   }
	 
	//delete_single_user
	@RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
	    public ResponseEntity<Client> deleteUser(@PathVariable("clientId") Long clientid) {
	        System.out.println("Fetching & Deleting User with id " + clientid);
	 
	        Client client = clientRepository1.findOne(clientid);
	        if (client== null) {
	            System.out.println("Unable to delete. User with id " + clientid + " not found");
	            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
	        }
	
	       
	
			
		
		 
	 
	       clientRepository1.delete(client);
	        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	    }
	
	//delete_all_users
	@RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteAllUsers() {
        //System.out.println("Deleting All Users");
		//this.validateUser(ClientId);
        clientRepository1.deleteAll();
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }
	

	@Autowired
	ClientRestController(Userrepository userRepository,
			Clientrepository clientRepository) {
		this.userRepository1 = userRepository;
		this.clientRepository1= clientRepository;
	}

	private void validateUser(String ClientId) {
		this.clientRepository1.findByUsername(ClientId).orElseThrow(
				() -> new UserNotFoundException(ClientId));
	}
}

/*@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String ClientId) {
		super("could not find user '" + ClientId + "'.");
	}
}*/



	