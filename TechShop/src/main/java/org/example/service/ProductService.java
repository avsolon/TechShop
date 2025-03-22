package org.example.service;

import org.example.dto.ProductDTO;
import org.example.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product addProduct(ProductDTO productDTO);
    void deleteProduct(UUID id);
    Product getProductById(UUID id);
    List<Product> getAllProducts();
    void decreaseStock(UUID id, Integer amount);
}
