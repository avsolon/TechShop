package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.model.Product;
import org.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name="product")
@Validated
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Запрос на добавление товара")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO){
        Product savedProduct = productService.addProduct(productDTO);
        return new ResponseEntity<>(modelMapper.map(savedProduct, ProductDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Запрос на удаление товара по id")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Запрос на получение товара по id")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID id){
        Product product = productService.getProductById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    @Operation(summary = "Запрос на получение всех товаров")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @PatchMapping("/{id}/decrease-stock")
    @Operation(summary = "Запрос на уменьшение товара")
    public ResponseEntity<Void> decreaseStock(@PathVariable UUID id, @RequestParam Integer amount) {
        try {
            productService.decreaseStock(id, amount);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
