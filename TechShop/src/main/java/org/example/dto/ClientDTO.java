package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {
    @NotBlank
    @Schema(name = "clientName", example = "Иван")
    private String clientName;

    @NotBlank
    @Schema(name = "clientSurName", example = "Иванов")
    private String clientSurname;

    @NotNull
    @Schema(name = "birthday", example = "1999-01-01")
    private LocalDate birthday;

    @NotBlank
    @Schema(name = "gender", example = "м/ж")
    private String gender;

    private AddressDTO addressDTO;
}
