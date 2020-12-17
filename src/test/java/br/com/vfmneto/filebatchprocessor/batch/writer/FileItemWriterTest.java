package br.com.vfmneto.filebatchprocessor.batch.writer;

import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static br.com.vfmneto.filebatchprocessor.fixture.OutputDataFileFixture.createOutputDataFileValid;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileItemWriterTest {

    private FileItemWriter fileItemWriter;

    @Mock private FileComponent fileComponentMock;

    @BeforeEach
    void setup() {
        fileItemWriter = new FileItemWriter(fileComponentMock);
    }

    @Test
    @DisplayName("Given that there are output data you should write to the output directory")
    void shouldWriteToTheOutputDirectory() {
        var outputDataFileOne = createOutputDataFileValid();
        var outputDataFileTwo = createOutputDataFileValid();

        fileItemWriter.write(Arrays.asList(outputDataFileOne, outputDataFileTwo));

        verify(fileComponentMock).writeToOutDirectory(outputDataFileOne);
        verify(fileComponentMock).writeToOutDirectory(outputDataFileTwo);
    }

    @Test
    @DisplayName("Given that there is no output data you should not write to the output directory")
    void shouldNotWriteToTheOutputDirectory() {
        fileItemWriter.write(EMPTY_LIST);
        verify(fileComponentMock, never()).writeToOutDirectory(any());
    }

}
