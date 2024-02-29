package it.epicode.U5W4BW.payloads;

import it.epicode.U5W4BW.enums.ClientType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotNull(message = "Type cannot be blank")
        @Enumerated(EnumType.STRING)
        ClientType type,
        @NotBlank(message = "Street cannot be blank")
        @Size(min = 10, message = "Street's length must be at least 10 characters")
        String street,
        @NotBlank(message = "Street number cannot be blank")
        @Size(min = 1, message = "Street's number length must be at least 1 character")
        String streetNumber,
        @NotBlank(message = "City cannot be blank")
        @Size(min = 3, message = "City's length must be at least 3 character")
        String city,
        @NotBlank(message = "Zip code cannot be blank")
        @Size(min = 4, message = "Zip code's length must be at least 4 character")
        String zipCode,
        @NotBlank(message = "Province name cannot be blank")
        @Size(min = 3, message = "Province name's length must be at least 3 character")
        String provinceName,
        @NotBlank(message = "Municipality name cannot be blank")
        @Size(min = 3, message = "Municipality name's length must be at least 3 character")
        String municipalityName
) {
}

// CONTACT INFO COULD ALSO BE SET AT A DIFFERENT TIME
