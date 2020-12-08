package br.com.vfmneto.filebatchprocessor.exception;

public class ErrorReadingFileException extends RuntimeException {

    public ErrorReadingFileException(Throwable cause) {
        super("Error reading file", cause);
    }
}
