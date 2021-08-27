package com.devsuperior.crud.dto;

import java.io.Serializable;
import java.time.Instant;

import com.devsuperior.crud.entities.Client;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	private String cpf;
	private Double inCome;
	private Instant birthDate;
	private Integer children;
	
	public ClientDTO() {
		
	}

	public ClientDTO(Long id, String name, String cpf, Double inCome, Instant birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.inCome = inCome;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.inCome = entity.getInCome();
		this.birthDate = entity.getBirthDate();
		this.children = entity.getChildren();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@JsonProperty("income")
	public Double getInCome() {
		return inCome;
	}

	public void setInCome(Double inCome) {
		this.inCome = inCome;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDTO other = (ClientDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
