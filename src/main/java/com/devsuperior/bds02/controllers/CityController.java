package com.devsuperior.bds02.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.service.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() throws Exception {
		List<CityDTO> cityList = cityService.findAll();
		
		return ResponseEntity.ok(cityList);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
		try {			
			cityService.delete(id);
		}
		catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
