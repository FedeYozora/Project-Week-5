package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InvoiceStatusDTO(
        @NotBlank(message = "Invoice status field cannot be empty")
        @Size(min = 3, max = 20, message = "Invoice status's length must be between 3 and 20 characters")
        String invoice_status
) {
}
