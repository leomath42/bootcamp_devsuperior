package com.devsuperior.crud.resources.exceptions;

import java.time.Instant;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.devsuperior.crud.services.exceptions.CrudEntityNotFoundException;
import com.devsuperior.crud.services.exceptions.DataBaseException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(value = CrudEntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public DefaultError crudEntityNotFoundException(CrudEntityNotFoundException e, HttpServletRequest request) {
		return buildError(e, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = DataBaseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public DefaultError dataBaseException(DataBaseException e, HttpServletRequest request) {
		return buildError(e, HttpStatus.BAD_REQUEST, request);
	}

	private DefaultError buildError(RuntimeException e, HttpStatus status, HttpServletRequest request) {
		return new DefaultError(Instant.now(), status.value(), e.getMessage(), e.getMessage(), request.getRequestURI());
	}
}
