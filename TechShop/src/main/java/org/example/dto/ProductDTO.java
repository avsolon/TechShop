package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @NotBlank
    @Schema(name = "name", example = "утюг")
    private String name;
    @NotBlank
    @Schema(name = "category", example = "мелкая техника")
    private String category;
    @NotNull
    private Double price;
    @NotNull
    private Integer availableStock;
    @NotNull
    private LocalDate lastUpdateDate;
    @NotNull
    private UUID supplierId;
    @NotNull
    private UUID imageId;
}
