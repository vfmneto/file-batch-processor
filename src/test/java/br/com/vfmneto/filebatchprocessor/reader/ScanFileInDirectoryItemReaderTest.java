package br.com.vfmneto.filebatchprocessor.reader;

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

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScanFileInDirectoryItemReaderTest {

    public static final String FILENAME_SAMPLE = "fileName sample";

    private ScanFileInDirectoryItemReader reader;

    @Mock private FileLineDataMapper fileLineDataMapperMock;
    @Mock private FileComponent fileComponentMock;
    @Mock private InputFile inputFileMock;

    @BeforeEach
    void setup() {
        reader = new ScanFileInDirectoryItemReader(fileLineDataMapperMock, fileComponentMock);
    }

    @Test
    @DisplayName("Given that there is a file it should return input data with data")
    void shouldReturnInputDataWithData() throws Exception {

        when(fileComponentMock.getFilesFromInDirectory()).thenReturn(asList(inputFileMock));
        List<LineData> linesData = new ArrayList<>();
        when(fileLineDataMapperMock.mapFile(inputFileMock)).thenReturn(linesData);
        when(inputFileMock.getFilename()).thenReturn(FILENAME_SAMPLE);
        when(inputFileMock.isValidExtension()).thenReturn(true);

        var inputDataFile = reader.read();

        verify(fileComponentMock).moveToProcessed(inputFileMock);

        assertAll("Different inputDataFile than expected",
                () -> assertEquals(inputDataFile.getFileName(), FILENAME_SAMPLE),
                () -> assertSame(inputDataFile.getLineData(), linesData));
    }

    @Test
    @DisplayName("Given that the file extension is invalid you should move it to the invalid directory")
    void shouldMoveItToTheInvalidDirectory() throws Exception {

        when(fileComponentMock.getFilesFromInDirectory()).thenReturn(asList(inputFileMock));
        List<LineData> linesData = new ArrayList<>();
        when(inputFileMock.isValidExtension()).thenReturn(false);

        var inputDataFile = reader.read();

        verify(fileComponentMock).moveToError(inputFileMock);
        verify(fileComponentMock, never()).moveToProcessed(inputFileMock);
        verify(fileLineDataMapperMock, never()).mapFile(inputFileMock);

        assertNull(inputDataFile);
    }

    @Test
    @DisplayName("Given that there is no file, it should return null")
    void shouldReturnNull() throws Exception {

        when(fileComponentMock.getFilesFromInDirectory()).thenReturn(emptyList());

        var inputDataFile = reader.read();

        verify(fileLineDataMapperMock, never()).mapFile(any());
        verify(fileComponentMock, never()).moveToProcessed(any());;

        assertNull(inputDataFile);
    }

}
