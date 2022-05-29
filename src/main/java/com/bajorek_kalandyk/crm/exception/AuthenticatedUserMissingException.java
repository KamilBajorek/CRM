package com.bajorek_kalandyk.crm.exception;

public class AuthenticatedUserMissingException extends Exception
{
    public AuthenticatedUserMissingException(String errorMessage)
    {
        super(errorMessage);
    }
}
