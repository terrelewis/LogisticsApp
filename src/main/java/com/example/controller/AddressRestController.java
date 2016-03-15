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

import com.example.entity.Address;

import com.example.repository.AddressRepository;


@RestController
@RequestMapping("/admin/address")
public class AddressRestController {
	
	private final AddressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.POST) 
	 public Address createAddress(@RequestBody Address address) { 
	  return this.addressRepository.save( 
	    new Address( 
	     
	     address.getLocality(),
	     address.getSubLocality(),
	     address.getPostalCode(),
	     address.getBuildingNo(),
	     address.getDoorNo(),
	     address.getHomebaseId(),
	     address.getLati(),
	     address.getLongi()
	     
	     
	      )); 
	 } 
	@RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
	Address readAddress(@PathVariable("addressId") Long addressId) {
		//this.validateUser(clientId);
		return this.addressRepository.findOne(addressId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Address> readAddress() {
		//this.validateUser(ClientId);
		return this.addressRepository.findAll();
	}
	
	@Autowired
	AddressRestController(
			AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
		
	}

	

}
