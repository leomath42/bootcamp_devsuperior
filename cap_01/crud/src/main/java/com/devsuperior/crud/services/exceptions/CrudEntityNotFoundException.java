package com.devsuperior.crud.services.exceptions;

public class CrudEntityNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CrudEntityNotFoundException(String msg) {
		super(msg);
	}
	public CrudEntityNotFoundException(Long id) {
		super(String.format("Entity Not Found. Missing Entity with id \"%d\"", id));
	}
	public CrudEntityNotFoundException() {
		super("Entity Not Found");
	}
}
