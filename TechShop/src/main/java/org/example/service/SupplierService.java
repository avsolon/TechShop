package org.example.service;

import org.example.dto.AddressDTO;
import org.example.dto.SupplierDTO;
import org.example.model.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierService {
    Supplier addSupplier(SupplierDTO supplierDTO);
    void deleteSupplier(UUID id);
    Optional<Supplier> getSupplierById(UUID id);
    List<Supplier> getAllSuppliers();
    Supplier updateSupplierAddress(UUID supplierId, AddressDTO addressDTO);
}
