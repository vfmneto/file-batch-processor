package br.com.vfmneto.filebatchprocessor.batch.reader;

import br.com.vfmneto.filebatchprocessor.mapper.FileLineDataMapper;
import br.com.vfmneto.filebatchprocessor.model.InputFile;
import br.com.vfmneto.filebatchprocessor.model.LineData;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScanFileInDirectoryItemReaderTest {

    public static final String FILENAME_SAMPLE_EXTENSION_VALID = "fileName.dat";

    private ScanFileInDirectoryItemReader reader;

    @Mock private FileLineDataMapper fileLineDataMapperMock;
    @Mock private FileComponent fileComponentMock;

    @BeforeEach
    void setup() {
        reader = new ScanFileInDirectoryItemReader(fileLineDataMapperMock, fileComponentMock);
    }

    @Test
    @DisplayName("Given that there is a file it should return input data with data")
    void shouldReturnInputDataWithData() {

        InputFile inputFile = new InputFile(Path.of(FILENAME_SAMPLE_EXTENSION_VALID));

        when(fileComponentMock.getFilesFromInDirectory()).thenReturn(asList(inputFile));
        List<LineData> linesData = new ArrayList<>();
        when(fileLineDataMapperMock.mapFile(inputFile)).thenReturn(linesData);

        var inputDataFile = reader.read();

        verify(fileComponentMock).moveToProcessed(inputFile);

        assertAll("Different inputDataFile than expected",
                () -> assertEquals(inputDataFile.getFileName(), FILENAME_SAMPLE_EXTENSION_VALID),
                () -> assertSame(inputDataFile.getLineData(), linesData));
    }

    @Test
    @DisplayName("Given that the file extension is invalid you should move it to the invalid directory")
    void shouldMoveItToTheInvalidDirectory() {

        InputFile inputFile = new InputFile(Path.of("file.doc"));
        when(fileComponentMock.getFilesFromInDirectory()).thenReturn(asList(inputFile));

        var inputDataFile = reader.read();

        verify(fileComponentMock).moveToError(inputFile);
        verify(fileComponentMock, never()).moveToProcessed(inputFile);
        verify(fileLineDataMapperMock, never()).mapFile(inputFile);

        assertNull(inputDataFile);
    }

    @Test
    @DisplayName("Given that there is no file, it should return null")
    void shouldReturnNull() {

        when(fileComponentMock.getFilesFromInDirectory()).thenReturn(emptyList());

        var inputDataFile = reader.read();

        verify(fileLineDataMapperMock, never()).mapFile(any());
        verify(fileComponentMock, never()).moveToProcessed(any());;

        assertNull(inputDataFile);
    }

}
