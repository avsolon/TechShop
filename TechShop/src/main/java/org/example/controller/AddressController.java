package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.AddressDTO;
import org.example.model.Address;
import org.example.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
@Tag(name = "address")
@Validated
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @PostMapping
    @Operation(summary = "Запрос на создание нового адреса")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        Address savedAddress = addressService.createAddress(addressDTO);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Запрос на удаление адреса по id")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
