package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDTO {
    @NotNull
    private byte[] image;
}
