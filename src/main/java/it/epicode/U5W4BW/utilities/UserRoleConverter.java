package it.epicode.U5W4BW.utilities;

import it.epicode.U5W4BW.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class UserRoleConverter implements AttributeConverter<Set<UserRole>, String> {
    @Override
    public String convertToDatabaseColumn(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(Enum::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<UserRole> convertToEntityAttribute(String rolesAsString) {
        String[] roleArray = rolesAsString.split(",");
        return Arrays.stream(roleArray)
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }
}
