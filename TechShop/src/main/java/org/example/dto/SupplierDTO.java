package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierDTO {
    @NotBlank
    @Schema(name = "name", example = "Техмаркет")
    private String name;

    @NotBlank
    @Schema(name = "phoneNumber", example = "+7-333-333-33-33")
    private String phoneNumber;

    private AddressDTO addressDTO;
}
