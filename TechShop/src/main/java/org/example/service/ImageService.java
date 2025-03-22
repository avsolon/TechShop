package org.example.service;

import org.example.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageService {
    Image addImage(MultipartFile file) throws IOException;
    void deleteImage(UUID id);
    Optional<Image> getImageById(UUID id);
    List<Image> getAllImages();
    void updateImage(UUID imageId, MultipartFile file) throws IOException;
}
