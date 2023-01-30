package com.example.hw27.repository;

import com.example.hw27.model.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressCustomRepositoryImpl implements AddressCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void updateAddress(Address address) {
        Query query = entityManager.createQuery("UPDATE Address a " +
                "SET a.country = :country,  a.city = :city,  a.street = :street,  a.house = :house " +
                "WHERE a.id = :id");
        query.setParameter("country", address.getCountry());
        query.setParameter("city", address.getCity());
        query.setParameter("street", address.getStreet());
        query.setParameter("house", address.getHouse());
        query.setParameter("id", address.getId());
        query.executeUpdate();
    }
}
