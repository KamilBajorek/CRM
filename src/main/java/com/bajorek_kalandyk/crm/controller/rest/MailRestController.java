package com.bajorek_kalandyk.crm.controller.rest;

import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.service.MailService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class MailRestController
{
    private static final String ENDPOINT = "mail";

    @Autowired
    private MailService mailService;

    @RequestMapping(value = ENDPOINT + "/getAll",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<List<Mail>> getMails()
    {
        return new ResponseEntity<>(mailService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = ENDPOINT + "/getById/{mailId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<Mail> getMail(@PathVariable("mailId") Long id)
    {
        Optional<Mail> Mail = mailService.getById(id);
        return Mail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ENDPOINT + "/create",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<Mail> createMail(@RequestBody @JsonSerialize @Email String mail)
    {
        Mail createdMail = mailService.createMail(mail);
        return new ResponseEntity<>(createdMail, HttpStatus.OK);
    }
}
