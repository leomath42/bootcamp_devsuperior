package com.devsuperior.crud.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crud.dto.ClientDTO;
import com.devsuperior.crud.entities.Client;
import com.devsuperior.crud.repositories.ClientRepository;
import com.devsuperior.crud.services.exceptions.CrudEntityNotFoundException;
import com.devsuperior.crud.services.exceptions.DataBaseException;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable).map(entity -> new ClientDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client entity = clientRepository.findById(id).orElseThrow(() -> new CrudEntityNotFoundException(id));
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		
		Client entity = new Client();
		this.copyDTOtoEntity(dto, entity);
		
		clientRepository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client entity = clientRepository.getOne(id);
			
			this.copyDTOtoEntity(clientDTO, entity);
			
			entity = clientRepository.save(entity);
			
			return new ClientDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new CrudEntityNotFoundException(id);
		}
	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);			
		}
		catch (EmptyResultDataAccessException e) {
			throw new CrudEntityNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException(id);
		}
	}
	
	private void copyDTOtoEntity(ClientDTO dto, Client entity) {
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setName(dto.getName());
		entity.setInCome(dto.getInCome());
		
	}
	
}
