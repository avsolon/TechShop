package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Address;
import org.example.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;
    @Override
    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(UUID id){
        addressRepository.deleteById(id);
    }
}
