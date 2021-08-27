package com.devsuperior.crud.services.exceptions;

public class DataBaseException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DataBaseException(String msg) {
		super(msg);
	}
	public DataBaseException(Long id) {
		super(String.format("Database Integrity violation. Can't delete this entity with \"%d\"", id));
	}
	public DataBaseException() {
		super("Database Integrity violation.");
	}
	
}