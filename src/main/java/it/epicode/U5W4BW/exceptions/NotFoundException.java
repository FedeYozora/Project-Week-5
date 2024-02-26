package it.epicode.U5W4BW.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Device with  " + id + " was not found");
    }
}
