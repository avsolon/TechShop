package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.AddressDTO;
import org.example.dto.ClientDTO;
import org.example.model.Client;
import org.example.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "client")
@Validated
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Запрос на добавление нового клиента")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO){
        Client savedClient = clientService.addClient(clientDTO);
        ClientDTO responseDTO = modelMapper.map(savedClient, ClientDTO.class);
        responseDTO.setAddressDTO(modelMapper.map(savedClient.getAddress(), AddressDTO.class));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Запрос на удаление клиента по id")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().
                header("Deletion-Message", "Client deleted successfully").build(); //204
    }

    @GetMapping("/searchNameSurname")
    @Operation(summary = "Запрос на получение клиента по имени и фамилии")
    public ResponseEntity<List<ClientDTO>> getClients(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String surName){

        List<Client> clients = clientService.getClientNameAndSurname(name, surName);
        List<ClientDTO> clientDTOs = clients.stream()
                .map(client -> {
                    ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
                    clientDTO.setAddressDTO(modelMapper.map(client.getAddress(), AddressDTO.class));
                    return clientDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOs);
    }

    @GetMapping("/allClients")
    @Operation(summary = "Запрос на получение всех клиентов")
    public ResponseEntity<List<Client>> getAllClients(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset){
        List<Client> clients = clientService.getAllClients(limit, offset);
        return ResponseEntity.ok(clients);

    }

    @PutMapping("/{id}/address")
    @Operation(summary = "Запрос на изменение адреса клиента")
    public ResponseEntity<ClientDTO> updateClientAddress(@PathVariable UUID id, @Valid @RequestBody AddressDTO addressDTO){
        try {
            Client updatedClient = clientService.updateClientAddress(id, addressDTO);
            ClientDTO responseDTO = modelMapper.map(updatedClient, ClientDTO.class);
            responseDTO.setAddressDTO(modelMapper.map(updatedClient.getAddress(), AddressDTO.class));
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500
        }
    }
}
