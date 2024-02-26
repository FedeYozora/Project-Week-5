package it.epicode.U5W4BW.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Element with id " + id + " not found");
    }

    public NotFoundException(String mail) {
        super("Element with email " + mail + " not found");
    }
}
