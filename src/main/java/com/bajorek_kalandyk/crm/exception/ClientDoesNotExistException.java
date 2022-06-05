package com.bajorek_kalandyk.crm.exception;

public class ClientDoesNotExistException extends Exception
{
    public ClientDoesNotExistException(String errorMessage)
    {
        super(errorMessage);
    }
}
