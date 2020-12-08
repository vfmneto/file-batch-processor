package br.com.vfmneto.filebatchprocessor.exception;

public class UnsupportedLineException extends RuntimeException {

    public UnsupportedLineException(Throwable cause) {
        super("Unsupported line", cause);
    }
}
