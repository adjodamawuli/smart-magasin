package com.esprit.appsmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.appsmart.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
