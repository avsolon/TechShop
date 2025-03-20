package org.example.service;

import org.example.dto.AddressDTO;

import java.util.UUID;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    void deleteAddress(UUID id);
}
