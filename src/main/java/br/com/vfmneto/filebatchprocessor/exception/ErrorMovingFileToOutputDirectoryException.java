package br.com.vfmneto.filebatchprocessor.exception;

public class ErrorMovingFileToOutputDirectoryException extends RuntimeException {

    public ErrorMovingFileToOutputDirectoryException(Throwable cause) {
        super("Error when moving file to output directory", cause);
    }
}
