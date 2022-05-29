package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.common.AuthenticationHelper;
import com.bajorek_kalandyk.crm.domain.form.UserForm;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<User>> getUsers()
    {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/getById/{userId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id)
    {
        Optional<User> user = userService.getById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserForm form)
    {
        try
        {
            final User createdUser = userService.createUser(form);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}