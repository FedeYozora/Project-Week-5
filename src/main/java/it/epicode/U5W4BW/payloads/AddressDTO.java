package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank(message = "VAT number cannot be blank")
        String street,
        @NotBlank(message = "VAT number cannot be blank")
        String streetNumber,
        @NotBlank(message = "VAT number cannot be blank")
        String city,
        @NotBlank(message = "VAT number cannot be blank")
        String zipCode
) {
}
