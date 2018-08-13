package com.example.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.domain.Cliente;
import com.example.model.repositories.IClientRepository;

@RestController
@RequestMapping(path = "/clients")
public class ClientResources {

	@Autowired
	private IClientRepository clientRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listAll(){
		return new ResponseEntity<List<Cliente>>(clientRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping (path = "/{name}")
	public ResponseEntity<Cliente> listByName(@PathVariable String name){
		return new ResponseEntity<Cliente>(clientRepository.findByName(name), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> add(@Valid @RequestBody Cliente client) {
		return new ResponseEntity<Cliente>(clientRepository.save(client), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente client) {
		Optional<Cliente> entity = clientRepository.findById(id);
		if (entity.isPresent()) {
			client.setId(id); // Pq?
			return new ResponseEntity<Cliente>(clientRepository.save(client), HttpStatus.OK);
		} else {
			return new ResponseEntity("Not Found,", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id) {
		clientRepository.deleteById(id);
		return new ResponseEntity("Deleted.", HttpStatus.OK);
	}
}