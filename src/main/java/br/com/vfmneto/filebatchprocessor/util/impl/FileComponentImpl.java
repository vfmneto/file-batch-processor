package br.com.vfmneto.filebatchprocessor.util.impl;

import br.com.vfmneto.filebatchprocessor.config.ApplicationProperties;
import br.com.vfmneto.filebatchprocessor.exception.ErrorGeneratingFileException;
import br.com.vfmneto.filebatchprocessor.exception.ErrorMovingFileToOutputDirectoryException;
import br.com.vfmneto.filebatchprocessor.exception.ErrorReadingFileException;
import br.com.vfmneto.filebatchprocessor.model.InputFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.unmodifiableList;

@Component
public class FileComponentImpl implements FileComponent {

    private static final Logger log = LoggerFactory.getLogger(FileComponentImpl.class);

    private final ApplicationProperties applicationProperties;

    public FileComponentImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public List<String> readAllLines(InputFile inputFile) {
        try {
            return unmodifiableList(Files.readAllLines(inputFile.getPath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ErrorReadingFileException(e);
        }
    }

    @Override
    public List<InputFile> getFilesFromInDirectory() {
        try {
            var inputFiles = Files.list(Paths.get(applicationProperties.getDirectory().getIn())).map(InputFile::new).collect(Collectors.toList());
            log.info("Listing ({}) files in: {}", inputFiles.size(), applicationProperties.getDirectory().getIn());
            return inputFiles;
        } catch (IOException e) {
            return EMPTY_LIST;
        }
    }

    @Override
    public void writeToOutDirectory(OutputDataFile outputDataFile) {
        try {
            var pathFile = applicationProperties.getDirectory().getOut() + "/" + outputDataFile.getFilenameAsDone();
            Files.write(Paths.get(pathFile), outputDataFile.getLines());
            log.info("Generated file for: {}", pathFile);
        } catch (IOException e) {
            throw new ErrorGeneratingFileException(e);
        }
    }

    @Override
    public void moveToProcessed(InputFile inputFile) {
        moveFileTo(inputFile, applicationProperties.getDirectory().getProcessed());
    }

    @Override
    public void moveToError(InputFile inputFile) {
        moveFileTo(inputFile, applicationProperties.getDirectory().getError());
    }

    private void moveFileTo(InputFile inputFile, String directory) {
        try {
            Files.move(inputFile.getPath(), Paths.get(directory + "/" + inputFile.getFilename()));
            log.info("Moved file to: {}", directory + "/" + inputFile.getFilename());
        } catch (IOException e) {
            throw new ErrorMovingFileToOutputDirectoryException(e);
        }
    }

}
