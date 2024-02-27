package it.epicode.U5W4BW.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Element with id " + id + " not found");
    }

    public NotFoundException(UUID id) {
        super("Element with uuid " + id + " not found");
    }

    public NotFoundException(String mail) {
        super("Element with email " + mail + " not found");
    }
}
