package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {
    @NotBlank
    @Schema(name = "country", example = "Russia")
    private String country;

    @NotBlank
    @Schema(name = "city", example = "Novosibirsk")
    private String city;

    @NotBlank
    @Schema(name = "street", example = "Marks Square, 7")
    private String street;
}
