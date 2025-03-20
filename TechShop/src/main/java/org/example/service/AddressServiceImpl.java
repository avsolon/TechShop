package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.AddressDTO;
import org.example.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{
    private AddressRepository addressRepository;
    @Override
    public AddressDTO createAddress(AddressDTO addressDTO){
        return addressRepository.save(addressDTO);
    }

    @Override
    public void deleteAddress(UUID id){
        addressRepository.deleteById(id);
    }
}
