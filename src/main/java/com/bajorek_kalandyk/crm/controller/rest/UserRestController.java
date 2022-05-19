package com.bajorek_kalandyk.crm.controller.rest;


import com.bajorek_kalandyk.crm.domain.User;
import com.bajorek_kalandyk.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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
            headers = "Accept=application/json")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}