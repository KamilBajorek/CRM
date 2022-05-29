package com.bajorek_kalandyk.crm.exception;

public class ClientAlreadyExistsException extends Exception
{
    public ClientAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
