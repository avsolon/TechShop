package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {
    @NotBlank
    private String clientName;

    @NotBlank
    private String clientSurname;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    private String gender;

    @NotNull
    private UUID addressId;
}
