package br.com.vfmneto.filebatchprocessor.exception;

public class FileReadException extends RuntimeException {

    public FileReadException(Throwable cause) {
        super("Error reading file", cause);
    }
}
