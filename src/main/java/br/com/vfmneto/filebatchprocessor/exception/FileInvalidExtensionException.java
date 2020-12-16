package br.com.vfmneto.filebatchprocessor.exception;

public class FileInvalidExtensionException extends RuntimeException {

    public FileInvalidExtensionException(String extension) {
        super(String.format("File invalid extension %s.", extension));
    }
}
