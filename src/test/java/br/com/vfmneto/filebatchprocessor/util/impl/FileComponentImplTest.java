package br.com.vfmneto.filebatchprocessor.util.impl;

import br.com.vfmneto.filebatchprocessor.config.ApplicationProperties;
import br.com.vfmneto.filebatchprocessor.exception.FileGenerationException;
import br.com.vfmneto.filebatchprocessor.exception.FileMoveToOutputDirectoryException;
import br.com.vfmneto.filebatchprocessor.exception.FileReadException;
import br.com.vfmneto.filebatchprocessor.fixture.OutputDataFileFixture;
import br.com.vfmneto.filebatchprocessor.model.InputFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static br.com.vfmneto.filebatchprocessor.fixture.OutputDataFileFixture.createOutputDataFileValid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileComponentImplTest {

    private FileComponentImpl fileComponent;

    @Mock private ApplicationProperties applicationPropertiesMock;
    @Mock private ApplicationProperties.Directory directoryMock;

    @TempDir
    static Path temporyDirectory;

    @BeforeEach
    void setup() {
        fileComponent = new FileComponentImpl(applicationPropertiesMock);
    }

    @Test
    @DisplayName("readAllLines: Given that the file has two lists it should return a list with the lines")
    void readAllLinesShouldReturnListWithTheLines() throws Exception {

        Path linesPath = temporyDirectory.resolve("lines.dat");
        var linesExpected = Arrays.asList("line one", "line two");
        Files.write(linesPath, linesExpected);

        var inputFile = new InputFile(linesPath);

        var result = fileComponent.readAllLines(inputFile);

        assertAll(() -> assertThat(result).hasSize(2),
                  () -> assertLinesMatch(linesExpected, result));
    }

    @Test
    @DisplayName("readAllLines: Given that the file does not exist it should throw exception")
    void readAllLinesShouldThrowExceptionWhenFileDoesNotExist() {

        var inputFile = new InputFile(Paths.get("path not exists"));

        assertThrows(FileReadException.class, () -> {
            fileComponent.readAllLines(inputFile);
        });

    }

    @Test
    @DisplayName("moveToProcessed: Given that the exist file should move to processed")
    void moveToProcessedShouldMoveToProcessed() throws IOException {

        var processedDirectory = temporyDirectory.toString() + "/processed";
        Files.createDirectories(Paths.get(processedDirectory));

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getProcessed()).thenReturn(processedDirectory);

        Path file = temporyDirectory.resolve("lines.dat");
        Files.write(file, Arrays.asList("line one"));
        var inputFile = new InputFile(file);

        fileComponent.moveToProcessed(inputFile);

        assertThat(Files.exists(Paths.get(temporyDirectory.toString() + "/processed/lines.dat"))).isTrue();
    }

    @Test
    @DisplayName("moveToProcessed: Given that file does not exist it should throw exception")
    void moveToProcessedShouldThrowExceptionWhenFileDoesNotExist() {

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getProcessed()).thenReturn("processedDirectory");

        var inputFile = new InputFile(Paths.get("path not exists"));

        assertThrows(FileMoveToOutputDirectoryException.class, () -> {
            fileComponent.moveToProcessed(inputFile);
        });
    }

    @Test
    @DisplayName("moveToError: Given that the exist file should move to error")
    void moveToProcessedShouldMoveToError() throws IOException {

        var errorDirectory = temporyDirectory.toString() + "/error";
        Files.createDirectories(Paths.get(errorDirectory));

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getError()).thenReturn(errorDirectory);

        Path file = temporyDirectory.resolve("lines.dat");
        Files.write(file, Arrays.asList("line one"));
        var inputFile = new InputFile(file);

        fileComponent.moveToError(inputFile);

        assertThat(Files.exists(Paths.get(temporyDirectory.toString() + "/error/lines.dat"))).isTrue();
    }

    @Test
    @DisplayName("getFilesFromInDirectory: Given that there are two files it should return")
    void getFilesFromInDirectoryShouldReturnListWithTwoInputFile() throws Exception {

        var inDirectory = temporyDirectory.toString() + "/inDirectory";
        Files.createDirectories(Paths.get(inDirectory));
        createFile(inDirectory + "/file1.dat", Arrays.asList("line one"));
        createFile(inDirectory + "/file2.dat", Arrays.asList("line two"));

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getIn()).thenReturn(inDirectory);

        var filesFromInDirectory = fileComponent.getFilesFromInDirectory();

        assertAll(() -> assertThat(filesFromInDirectory).hasSize(2),
                  () -> assertThat(filesFromInDirectory)
                          .extracting(InputFile::getFilename)
                          .contains("file1.dat", "file2.dat"));
    }

    @Test
    @DisplayName("getFilesFromInDirectory: Given that there are no files in the directory")
    void getFilesFromInDirectoryShouldReturnEmptyList() throws Exception {

        var inDirectory = temporyDirectory.toString() + "/inDirectory";
        Files.createDirectories(Paths.get(inDirectory));

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getIn()).thenReturn(inDirectory);

        var filesFromInDirectory = fileComponent.getFilesFromInDirectory();

        assertThat(filesFromInDirectory.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("getFilesFromInDirectory: Given that the directory does not exist, it should throw an exception")
    void getFilesFromInDirectoryShouldThrowExceptionWhenDirectoryDoesNotExist() {

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getIn()).thenReturn("deretorio invalido");

        assertThat(fileComponent.getFilesFromInDirectory().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("writeToOutDirectory: Given input data should generate a file")
    void writeToOutDirectoryShouldGenerateFile() throws IOException {

        var outDirectory = temporyDirectory.toString() + "/out";
        Files.createDirectories(Paths.get(outDirectory));

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getOut()).thenReturn(outDirectory);

        var outputDataFileValid = createOutputDataFileValid();
        fileComponent.writeToOutDirectory(outputDataFileValid);

        var generatedFilePath = Paths.get(outDirectory + "/" + outputDataFileValid.getFilenameAsDone());

        assertAll(() -> assertThat(Files.exists(generatedFilePath)).isTrue(),
                  () -> assertLinesMatch(Files.readAllLines(generatedFilePath), outputDataFileValid.getLines()));
    }

    @Test
    @DisplayName("writeToOutDirectory: Given that there was an error it should throw exception")
    void writeToOutDirectoryShouldThrowAnExceptionWhenThereIsAnError() {

        when(applicationPropertiesMock.getDirectory()).thenReturn(directoryMock);
        when(directoryMock.getOut()).thenReturn("path not exists");

        assertThrows(FileGenerationException.class, () -> {
            fileComponent.writeToOutDirectory(OutputDataFileFixture.createOutputDataFileValid());
        });

    }

    private void createFile(String file, List<String> lines) throws IOException {
        Files.write(temporyDirectory.resolve(file), lines);
    }

}
