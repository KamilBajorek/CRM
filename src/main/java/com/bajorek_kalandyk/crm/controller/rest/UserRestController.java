package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.common.ApiResponse;
import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserRestController
{
    private static final String ENDPOINT = "user";

    @Autowired
    private UserService userService;

    @RequestMapping(value = ENDPOINT + "/getAll",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ApiResponse<List<User>> getUsers()
    {
        return new ApiResponse<>(userService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/getById/{userId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ApiResponse<User> getUser(@PathVariable("userId") Long id)
    {
        Optional<User> user = userService.getById(id);
        return user.map(value -> new ApiResponse<>(value, HttpStatus.OK))
                .orElseGet(() -> new ApiResponse<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ApiResponse<User> createUser(@RequestBody @Valid UserForm form)
    {
        try
        {
            final User createdUser = userService.createUser(form);
            return new ApiResponse<>(createdUser, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ApiResponse<>(null, HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}