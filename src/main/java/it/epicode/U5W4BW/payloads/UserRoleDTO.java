package it.epicode.U5W4BW.payloads;

import it.epicode.U5W4BW.enums.UserRoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record UserRoleDTO(
        @NotBlank(message = "Role cannot be blank")
        String role
) {
}
