package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.AddressDTO;
import org.example.dto.ClientDTO;
import org.example.model.Address;
import org.example.model.Client;
import org.example.repository.AddressRepository;
import org.example.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;
    private AddressService addressService;
    private AddressRepository addressRepository;
    private ModelMapper modelMapper;

    @Override
    public Client addClient(ClientDTO clientDTO) {
        Address newAddress = modelMapper.map(clientDTO.getAddressDTO(), Address.class);
        Address saveAddress = addressRepository.save(newAddress);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Client client = Client.builder().
                clientName(clientDTO.getClientName()).
                clientSurname(clientDTO.getClientSurname()).
                birthday(clientDTO.getBirthday()).
                gender(clientDTO.getGender()).
                registrationDate(currentDateTime).
                address(saveAddress).build();
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getClientNameAndSurname(String name, String surname) {
        return clientRepository.findByClientNameAndClientSurname(name, surname);
    }

    @Override
    public List<Client> getAllClients(int limit, int offset) {
        Pageable pageable = PageRequest.of(limit, offset);
        return clientRepository.findAll(pageable).getContent();
    }

    @Override
    public Client updateClientAddress(UUID clientId, AddressDTO addressDTO) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {return null;}
        Address newAddress = new Address(null, addressDTO.getCountry(), addressDTO.getCity(), addressDTO.getStreet());
        Address savedAddress = addressRepository.save(newAddress);
        client.setAddress(savedAddress);
        return clientRepository.save(client);
    }
}
