package com.ibm.academia.ruleta.exceptions;

public class BadRequestException extends  RuntimeException
{
    
	private static final long serialVersionUID = -1226683243875677793L;

	public  BadRequestException(String message){
        super(message);
    }
}
