package br.com.vfmneto.filebatchprocessor.exception;

public class FileGenerationException extends RuntimeException {

    public FileGenerationException(Throwable cause) {
        super("Error generating file", cause);
    }
}
