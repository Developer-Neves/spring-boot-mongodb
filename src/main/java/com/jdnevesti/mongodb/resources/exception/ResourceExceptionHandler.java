package com.jdnevesti.mongodb.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jdnevesti.mongodb.services.exception.ObjectNotFoundException;

@ControllerAdvice // serve para tratar possíveis erros nas riquisições
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class) // anotação serve para quando ocorrer a exceção ObjectNotFoundException fazer o tratamento abaixo
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;		
		StandardError erro = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
}
