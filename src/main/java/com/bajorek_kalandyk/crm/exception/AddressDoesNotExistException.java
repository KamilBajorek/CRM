package com.bajorek_kalandyk.crm.exception;

public class AddressDoesNotExistException extends Exception
{
    public AddressDoesNotExistException(String errorMessage)
    {
        super(errorMessage);
    }
}
