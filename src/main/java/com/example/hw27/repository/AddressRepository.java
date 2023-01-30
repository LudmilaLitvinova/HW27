package com.example.hw27.repository;

import com.example.hw27.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>, AddressCustomRepository {

}

