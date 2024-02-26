package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.NotBlank;

public record ProvinceDTO(
        @NotBlank(message = "Name field cannot be empty")
        String name,
        @NotBlank(message = "Acronym field cannot be empty")
        String acronym,
        @NotBlank(message = "Region field cannot be empty")
        String region
) {
}
