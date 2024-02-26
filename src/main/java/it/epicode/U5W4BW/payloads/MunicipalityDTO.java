package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.NotBlank;

public record MunicipalityDTO(
        @NotBlank(message = "Province code field cannot be empty")
        String provinceCode,
        @NotBlank(message = "Municipality name field cannot be empty")
        String name,
        @NotBlank(message = "Province field cannot be empty")
        String province

) {
}
