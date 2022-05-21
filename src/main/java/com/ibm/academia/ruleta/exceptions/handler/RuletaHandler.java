package com.ibm.academia.ruleta.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.ruleta.exceptions.BadRequestException;
import com.ibm.academia.ruleta.exceptions.NotFoundException;
import com.ibm.academia.ruleta.exceptions.NotReadableException;

@ControllerAdvice
public class RuletaHandler 
{
	
	    @ExceptionHandler(value = BadRequestException.class)
	    public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception)
	    {
	        Map<String, Object> respuesta = new HashMap<String, Object>();
	        respuesta.put("error", exception.getMessage());
	        return  new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(value = NotFoundException.class)
	    public ResponseEntity<?> noExisteException(NotFoundException exception)
	    {
	        Map<String, Object> respuesta = new HashMap<String, Object>();
	        respuesta.put("error", exception.getMessage());
	        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(value= NotReadableException.class)
	    public ResponseEntity<?> noSePuedeLeerException(NotReadableException exception)
	    {
	    	 Map<String, Object> respuesta = new HashMap<String, Object>();
		     respuesta.put("error", exception.getMessage());
		     return  new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	    }
}

