package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.model.Image;
import org.example.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
@Tag(name = "image")
@Validated
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;

    @PostMapping
    @Operation(summary = "Запрос на добавление изображения товара")
    public ResponseEntity<UUID> addImage(@RequestPart("file") MultipartFile file) throws IOException {
        UUID imageId = imageService.addImage(file).getId();
        return new ResponseEntity<>(imageId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Запрос на удаление изображения по id")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") UUID id){
        imageService.deleteImage(id);
        return ResponseEntity.noContent().
                header("Deletion-Message", "Image deleted successfully").build(); //204
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "Запрос на получение изображения по id")
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") UUID id) {
        Optional<Image> imageOptional = imageService.getImageById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(image.getImage());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Запрос на получение всех изображений")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return ResponseEntity.ok(images);
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Запрос на изменение изображения")
    public ResponseEntity<Void> updateImage(@PathVariable UUID id, @RequestPart("file") MultipartFile file) throws IOException {
        imageService.updateImage(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
