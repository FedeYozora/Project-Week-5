package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InvoiceDTO(
        @Email(message = "E-mail format is not valid")
        @NotBlank(message = "Client E-mail cannot be blank")
        String email,
        @Min(value = 50, message = "Minimum amount is 50$")
        @NotBlank(message = "Invoice amount cannot be blank")
        Double amount,
        @NotBlank(message = "Invoice status field cannot be empty")
        @Size(min = 3, max = 20, message = "Invoice status's length must be between 3 and 20 characters")
        String invoice_status

) {
}
