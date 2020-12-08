package br.com.vfmneto.filebatchprocessor.model;

import java.nio.file.Path;

public class InputFile {

    private Path path;

    public InputFile(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public String getFilename() {
        return path.getFileName().toString();
    }

    public Boolean isValidExtension() {
        return getFilename().endsWith(".dat");
    }
}
