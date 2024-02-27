package it.epicode.U5W4BW.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserRoleDTO(
        @NotBlank(message = "Role cannot be blank")
        String role
) {
}
