package com.bajorek_kalandyk.crm.exception;

public class ProductCategoryAlredyExistsException extends Exception
{
    public ProductCategoryAlredyExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
