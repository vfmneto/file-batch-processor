package br.com.vfmneto.filebatchprocessor.exception;

public class ErrorGeneratingFileException extends RuntimeException {

    public ErrorGeneratingFileException(Throwable cause) {
        super("Error generating file", cause);
    }
}
