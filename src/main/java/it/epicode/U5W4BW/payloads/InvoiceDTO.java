package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InvoiceDTO(
        @Email(message = "E-mail format is not valid")
        @NotBlank(message = "Client E-mail cannot be blank")
        String email,
        @Min(value = 50, message = "Minimum amount is 50$")
        @NotBlank(message = "Invoice amount cannot be blank")
        Double amount

) {
}
