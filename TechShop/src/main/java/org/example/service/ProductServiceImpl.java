package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private SupplierService supplierService;
    private ImageService imageService;

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = Product.builder().
                name(productDTO.getName()).
                category(productDTO.getCategory()).
                price(productDTO.getPrice()).
                availableStock(productDTO.getAvailableStock()).
                lastUpdateDate(productDTO.getLastUpdateDate()).build();
        product.setSupplier(supplierService.getSupplierById(productDTO.getSupplierId()).get());
        product.setImage(imageService.getImageById(productDTO.getImageId()).get());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void decreaseStock(UUID id, Integer amount) {
        Product product = getProductById(id);
        if (product.getAvailableStock() < amount) {
            throw new IllegalArgumentException("Not enough products" + amount);
        }
        product.setAvailableStock(product.getAvailableStock() - amount);
        productRepository.save(product);
    }
}
