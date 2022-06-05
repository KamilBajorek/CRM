package com.bajorek_kalandyk.crm.exception;

public class ProductAlreadyExistsException extends Exception
{
    public ProductAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
