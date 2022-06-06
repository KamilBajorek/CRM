package com.bajorek_kalandyk.crm.exception;

public class ProductCategoryDoesNotExistsException extends Exception
{
    public ProductCategoryDoesNotExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
