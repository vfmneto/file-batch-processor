package br.com.vfmneto.filebatchprocessor.exception;

public class InvalidFileExtensionException extends RuntimeException {

    public InvalidFileExtensionException(String extension) {
        super(String.format("Invalid file extension %s.", extension));
    }
}
