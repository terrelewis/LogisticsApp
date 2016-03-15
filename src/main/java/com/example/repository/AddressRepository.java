package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Address;


public interface AddressRepository extends JpaRepository<Address, Long>
{
	Optional<Address> findByAddressId(Long Id);
}
