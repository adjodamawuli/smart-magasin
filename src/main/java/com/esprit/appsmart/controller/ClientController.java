package com.esprit.appsmart.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.appsmart.exception.ResourceNotFoundException;
import com.esprit.appsmart.model.Client;
import com.esprit.appsmart.repository.ClientRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/smart")
public class ClientController {
	 @Autowired
	    ClientRepository clientRepository;

	    @GetMapping("/clients")
	    public List<Client> getAllClients() {
	        return clientRepository.findAll();
	    }

	    @PostMapping("/clients")
	    public Client createClient(@Valid @RequestBody Client client) {
	    	client.setCreatedAt(new Date());
	    	client.setUpdatedAt(new Date());
	        return clientRepository.save(client);
	    }

	    @GetMapping("/clients/{id}")
	    public Client getClientById(@PathVariable(value = "id") Long clientId) {
	        return clientRepository.findById(clientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
	    }

	    @PutMapping("/clients/{id}")
	    public Client updateClient(@PathVariable(value = "id") Long clientId,
	                                           @Valid @RequestBody Client clientDetails) {

	        Client client = clientRepository.findById(clientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

	        client.setNom(clientDetails.getNom());
	        client.setPrenom(clientDetails.getPrenom());
	        client.setTel(clientDetails.getTel());
	        client.setUpdatedAt(new Date());

	        Client updatedClient = clientRepository.save(client);
	        return updatedClient;
	    }

	    @DeleteMapping("/clients/{id}")
	    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
	        Client client = clientRepository.findById(clientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

	        clientRepository.delete(client);

	        return ResponseEntity.ok().build();
	    }
}
