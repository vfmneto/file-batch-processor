package br.com.vfmneto.filebatchprocessor.mapper.impl;

import br.com.vfmneto.filebatchprocessor.config.ApplicationProperties;
import br.com.vfmneto.filebatchprocessor.exception.UnsupportedLineException;
import br.com.vfmneto.filebatchprocessor.model.InputFile;
import br.com.vfmneto.filebatchprocessor.model.LineData;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileLineDataMapperImplTest {

    public static final String LINE_ONE = "line one";
    public static final String LINE_TWO = "line two";

    private FileLineDataMapperImpl mapper;

    @Mock private PatternMatchingCompositeLineMapper compositeLineMapperMock;
    @Mock private FileComponent fileComponentMock;
    @Mock private InputFile inputFileMock;
    @Mock private ApplicationProperties applicationPropertiesMock;

    @BeforeEach
    void setup() {
        mapper = new FileLineDataMapperImpl(compositeLineMapperMock, fileComponentMock);
    }

    @Test
    @DisplayName("Should map to list LineData")
    void shouldMapToListLineData() throws Exception {

        when(fileComponentMock.readAllLines(inputFileMock)).thenReturn(asList(LINE_ONE, LINE_TWO));

        when(compositeLineMapperMock.mapLine(LINE_ONE, 0)).thenReturn(new LineDataSample("001"));
        when(compositeLineMapperMock.mapLine(LINE_TWO, 0)).thenReturn(new LineDataSample("002"));

        var lineData = mapper.mapFile(inputFileMock);

        assertThat(lineData)
                .hasSize(2)
                .extracting(LineData::getType)
                .containsAnyOf("001", "002");
    }

    @Test
    @DisplayName("Should move file to error directory when unsupported line")
    void shouldMoveFileToErrorDirectoryWhenUnsupportedLine() throws Exception {

        when(fileComponentMock.readAllLines(inputFileMock)).thenReturn(asList(LINE_ONE));
        when(compositeLineMapperMock.mapLine(any(), anyInt())).thenThrow(new UnsupportedLineException(any()));

        assertThrows(UnsupportedLineException.class, () -> {
            mapper.mapFile(inputFileMock);
        });
        verify(fileComponentMock).moveToError(inputFileMock);
    }

    private class LineDataSample extends LineData {

        public LineDataSample(String type) {
            setType(type);
        }
    }

}
