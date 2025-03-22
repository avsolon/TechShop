package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Image;
import org.example.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Override
    public org.example.model.Image addImage(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Image image = new Image(null, bytes);
        imageRepository.save(image);
        return null;
    }

    @Override
    public void deleteImage(UUID id){
        imageRepository.deleteById(id);
    }

    @Override
    public Optional<Image> getImageById(UUID id){
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }

    @Override
    public void updateImage(UUID id, MultipartFile file) throws IOException{
        Image image = imageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Image not found"));
        byte[] newImage = file.getBytes();
        image.setImage(newImage);
        imageRepository.save(image);
    }
}
