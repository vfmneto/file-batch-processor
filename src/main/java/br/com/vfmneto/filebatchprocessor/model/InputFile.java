package br.com.vfmneto.filebatchprocessor.model;

import org.apache.commons.io.FilenameUtils;

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

    public String getExtension() {
        return FilenameUtils.getExtension(getFilename());
    }

    public Boolean isInvalidExtension() {
        return !"dat".equals(getExtension());
    }
}
