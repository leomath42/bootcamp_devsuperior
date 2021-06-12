package com.devsuperior.crud.services;

import java.util.function.Supplier;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.entities.Client;
import com.devsuperior.crud.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public Page<ClientDTO> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable).map(entity -> new ClientDTO(entity));
	}
	
	public ClientDTO findById(Long id) {
		Client entity = clientRepository.findById(id).get();
		return new ClientDTO(entity);
	}

	public ClientDTO insert(ClientDTO dto) {
		
		Client entity = new Client();
		this.copyDTOtoEntity(dto, entity);
		
		clientRepository.save(entity);
		return new ClientDTO(entity);
	}

	public ClientDTO update(Long id, ClientDTO clientDTO) {
		
		Client entity = clientRepository.getOne(id);
		
		this.copyDTOtoEntity(clientDTO, entity);
		
		entity = clientRepository.save(entity);
		
		return new ClientDTO(entity);
	}

	public void delete(Long id) {
		clientRepository.deleteById(id);
	}
	
	private void copyDTOtoEntity(ClientDTO dto, Client entity) {
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setName(dto.getName());
		entity.setInCome(dto.getInCome());
		
	}
	
}
