package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {
    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String street;
}
