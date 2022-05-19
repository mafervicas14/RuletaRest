package com.ibm.academia.ruleta.exceptions;

public class NotFoundException extends RuntimeException
{
   
	private static final long serialVersionUID = -4454722937754380628L;

	public NotFoundException(String message)
    {
        super(message);
    }
}
