package it.epicode.U5W4BW.payloads;

import it.epicode.U5W4BW.enums.ClientType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewClientDTO(
        @NotBlank(message = "Business name cannot be blank")
        @Size(min = 2, max = 20, message = "Business name's length must be between 2 and 20 characters")
        String businessName,
        @NotBlank(message = "VAT number cannot be blank")
        @Size(min = 8, max = 20, message = "Vat number's length must be between 8 and 20 characters")
        String vatNumber,
        @Email(message = "E-mail format is not valid")
        @NotBlank(message = "Email cannot be blank")
        String email,
        @Email(message = "Verified E-mail format is not valid")
        @NotBlank(message = "Verified Email cannot be blank")
        String verifiedEmail,
        @NotBlank(message = "Phone number cannot be blank")
        @Size(min = 10, max = 20, message = "Phone number's length must be between 10 and 20 characters")
        String phoneNumber,
        @Email(message = "Contact E-mail format is not valid")
        @NotBlank(message = "Contact email cannot be blank")
        String contactEmail,
        @NotBlank(message = "Contact name cannot be blank")
        @Size(min = 2, max = 20, message = "Contact name's length must be between 2 and 20 characters")
        String contactName,
        @NotBlank(message = "Contact surname cannot be blank")
        @Size(min = 2, max = 20, message = "Contact surname's length must be between 2 and 20 characters")
        String contactSurname,
        @NotBlank(message = "Contact phone number cannot be blank")
        @Size(min = 10, max = 20, message = "Contact phone number's length must be between 10 and 20 characters")
        String contactPhoneNumber,
        @NotBlank(message = "Type cannot be blank")
        @Enumerated(EnumType.STRING)
        ClientType type


) {
}

// CONTACT INFO COULD ALSO BE SET AT A DIFFERENT TIME
