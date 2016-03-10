package com.example;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LogisticsAppApplication{

	@Bean
	CommandLineRunner init(Clientrepository clientRepository,
			Userrepository userRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(
						a -> {
							Client client = clientRepository.save(new Client(a,"password"));
							userRepository.save(new User("A", "AL","A","776023152","ALS",client));
							userRepository.save(new User("B", "BL","B","776023151","BLS",client));
						});
	}

	
    public static void main(String args[]) {
        SpringApplication.run(LogisticsAppApplication.class, args);
    }
   
    
   
}
 
    

/*@RestController
@RequestMapping("admin/clients/")
class ClientRestController {

	private final Userrepository userRepository1;

	private final Clientrepository clientRepository1;

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Client input) {
					
				
			
					Client result = clientRepository1.save(new Client(input.ID, input.username, input.password));
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getID()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				

	}
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	User readUser(@PathVariable("ClientId") String clientId, @PathVariable("userId") Long userId) {
		this.validateUser(clientId);
		return this.userRepository.findOne(userId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<User> readUsers(@PathVariable("ClientId") String ClientId) {
		this.validateUser(ClientId);
		return this.userRepository.findByClientUsername(ClientId);
	}
	
	
	//patch
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
	  public ResponseEntity<User> patchupdateUser(@PathVariable("ClientId") String clientId, @PathVariable("userId") long id, @RequestBody User user) {
        //System.out.println("Updating User " + id);
         this.validateUser(clientId);
         return this.clientRepository
        		 .findByUsername(clientId)
        		 .map(client->{
        User currentUser = userRepository.findOne(id);
        
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        
        currentUser.setID(user.ID);
        if(user.first_name!=null)
        currentUser.setFirst_name(user.first_name);
       if(user.last_name!=null)
        currentUser.setLast_name(user.last_name);
       if(user.user_name!=null) 
       currentUser.setUser_name(user.user_name);
       if(user.mobile_num!=null) 
       currentUser.setMobile_num(user.mobile_num);
       if(user.default_location!=null) 
       currentUser.setDefault_location(user.default_location);
         
        userRepository.save(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }).get();}
	
	//updating_user
	   @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateUser(@PathVariable("ClientId") String clientId, @PathVariable("userId") long id, @RequestBody User user) {
	        //System.out.println("Updating User " + id);
	         this.validateUser(clientId);
	         return this.clientRepository
	        		 .findByUsername(clientId)
	        		 .map(client->{
	        User currentUser = userRepository.findOne(id);
	        
	         
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentUser.setID(user.ID);
	        currentUser.setFirst_name(user.first_name);
	        currentUser.setLast_name(user.last_name);
	        currentUser.setUser_name(user.user_name);
	        currentUser.setMobile_num(user.mobile_num);
	        currentUser.setDefault_location(user.default_location);
	         
	        userRepository.saveAndFlush(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }).get();}
	 
	//delete_single_user
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteUser(@PathVariable("userId") long userid) {
	        //System.out.println("Fetching & Deleting User with id " + id);
	 
	        User user = userRepository.findOne(userid);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + userid + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        userRepository.delete(userid);
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	
	//delete_all_users
	@RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers(@PathVariable("ClientId") String ClientId) {
        //System.out.println("Deleting All Users");
		this.validateUser(ClientId);
        userRepository.deleteAll();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	

	@Autowired
	BookmarkRestController(Userrepository userRepository,
			Clientrepository clientRepository) {
		this.userRepository = userRepository;
		this.clientRepository = clientRepository;
	}

	private void validateUser(String ClientId) {
		this.clientRepository.findByUsername(ClientId).orElseThrow(
				() -> new UserNotFoundException(ClientId));
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String ClientId) {
		super("could not find user '" + ClientId + "'.");
	}
}



	@Autowired
	ClientRestController(Userrepository userRepository,
			Clientrepository clientRepository) {
		this.userRepository1 = userRepository;
		this.clientRepository1= clientRepository;
	}
}}


*/