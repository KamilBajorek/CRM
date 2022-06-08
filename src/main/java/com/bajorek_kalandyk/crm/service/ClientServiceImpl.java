package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.ClientForm;
import com.bajorek_kalandyk.crm.domain.form.ClientUpdateForm;
import com.bajorek_kalandyk.crm.domain.model.Address;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Mail;
import com.bajorek_kalandyk.crm.exception.AddressDoesNotExistException;
import com.bajorek_kalandyk.crm.exception.AuthenticatedUserMissingException;
import com.bajorek_kalandyk.crm.exception.ClientAlreadyExistsException;
import com.bajorek_kalandyk.crm.exception.ClientDoesNotExistException;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.bajorek_kalandyk.crm.common.AuthenticationHelper.GetCurrentUser;

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
    public List<Client> getByManagerId(final Long managerId)
    {
        return repository.findByManagerId(managerId);
    }

    @Override
    public Optional<Client> getById(final Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Client updateClient(final ClientUpdateForm form) throws ClientDoesNotExistException, AddressDoesNotExistException
    {
        Optional<Client> existingClient = repository.findById(form.getId());
        if (existingClient.isEmpty())
        {
            throw new ClientDoesNotExistException("Klient o podanym id nie istnieje");
        }
        final Address updatedAddress = addressService.updateAddress(Address.builder()
                .id(existingClient.get().getAddress().getId())
                .street(form.getStreet())
                .city(form.getCity())
                .zipCode(form.getZipCode())
                .houseNumber(form.getHouseNumber())
                .country(form.getCountry())
                .build());
        Mail updatedMail = existingClient.get().getEmail();
        if (!form.getEmail().equals(existingClient.get().getEmail().getMail()))
        {
            updatedMail = mailService.createMail(form.getEmail());
        }
        return repository.save(existingClient.get().toBuilder()
                .name(form.getName())
                .surname(form.getSurname())
                .email(updatedMail)
                .address(updatedAddress)
                .build());
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
                .createDate(Timestamp.valueOf(LocalDateTime.now()))
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
