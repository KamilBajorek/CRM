package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.exception.AuthenticatedUserMissingException;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bajorek_kalandyk.crm.common.AuthenticationHelper.GetCurrentUser;
import static java.time.LocalDateTime.now;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository repository;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private AddressService addressService;

    @Override
    public List<Client> getAll()
    {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "Id"));
    }

    @Override
    public Client createClient(final ClientForm form) throws ClientAlreadyExistsException, AuthenticatedUserMissingException
    {
        validateForm(form);
        final Mail clientMail = mailService.createMail(form.getEmail());
        final Address clientAddress = addressService.createAddress(Address.builder()
                .street(form.getStreet())
                .city(form.getCity())
                .zipCode(form.getZipCode())
                .houseNumber(form.getHouseNumber())
                .country(form.getCountry())
                .build());
        final Client newClient = Client.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .email(clientMail)
                .address(clientAddress)
                .managerId(GetCurrentUser().getId())
                .createDate(now())
                .build();

        return repository.save(newClient);
    }

    private void validateForm(final ClientForm form) throws ClientAlreadyExistsException
    {
        final Mail existingMail = mailRepository.findMailByMail(form.getEmail());
        if (existingMail != null)
        {
            final Client existingMailByMail = repository.findClientByEmail(existingMail);
            if (existingMailByMail != null)
            {
                throw new ClientAlreadyExistsException("Klient z podanym adresem email już istnieje!");
            }
        }
    }
}
