package com.devsuperior.crud.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	ClientService clientService; 
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(
			@RequestParam(value = "page", defaultValue="0") int page, 
			@RequestParam(value = "linesPerPage", defaultValue="2") int linesPerPage,
			@RequestParam(value="direction", defaultValue="ASC") Direction direction,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		
		return ResponseEntity.ok().body(clientService.findAll(pageRequest));
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		ClientDTO dto = clientService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO){
		ClientDTO dto = clientService.insert(clientDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
//	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public ClientDTO insert(@RequestBody ClientDTO clientDTO){
//		ClientDTO dto = clientService.insert(clientDTO);
//		return dto;
//	}
	
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
		ClientDTO dto = clientService.update(id, clientDTO);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		clientService.delete(id);
//		return ResponseEntity;
	}
}
