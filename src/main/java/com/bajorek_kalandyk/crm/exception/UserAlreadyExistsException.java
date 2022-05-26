package com.bajorek_kalandyk.crm.exception;

public class UserAlreadyExistsException extends Exception
{
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
