package org.example.service;

import org.example.model.Address;

import java.util.UUID;

public interface AddressService {
    Address createAddress(Address address);
    void deleteAddress(UUID id);
}
