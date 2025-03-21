package org.example.service;

import org.example.dto.AddressDTO;
import org.example.dto.ClientDTO;
import org.example.model.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    Client addClient(ClientDTO clientDTO);
    void deleteClient(UUID id);
    List<Client> getClientNameAndSurname(String name, String surname);
    List<Client> getAllClients(int limit, int offset);
    Client updateClientAddress(UUID id, AddressDTO addressDTO);
}
