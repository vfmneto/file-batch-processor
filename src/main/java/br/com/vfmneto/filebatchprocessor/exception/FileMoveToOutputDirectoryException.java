package br.com.vfmneto.filebatchprocessor.exception;

public class FileMoveToOutputDirectoryException extends RuntimeException {

    public FileMoveToOutputDirectoryException(Throwable cause) {
        super("Error when moving file to output directory", cause);
    }
}
