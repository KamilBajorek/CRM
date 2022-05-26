package com.bajorek_kalandyk.crm.common;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiResponse<T>
{
    private final T result;
    private final HttpStatus status;
    private final Boolean success;
    private final String message;

    public ApiResponse(T result, HttpStatus status)
    {
        this.result = result;
        this.status = status;
        this.message = "";
        this.success = true;
    }
}
