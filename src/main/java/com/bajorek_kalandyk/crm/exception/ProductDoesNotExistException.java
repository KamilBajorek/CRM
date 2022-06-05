package com.bajorek_kalandyk.crm.exception;

public class ProductDoesNotExistException extends Exception
{
    public ProductDoesNotExistException(String errorMessage)
    {
        super(errorMessage);
    }
}
