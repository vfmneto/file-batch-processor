package br.com.vfmneto.filebatchprocessor.batch.processor;

import br.com.vfmneto.filebatchprocessor.service.ConsolidatedOutputDataFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;
import static br.com.vfmneto.filebatchprocessor.fixture.OutputDataFileFixture.createOutputDataFileValid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputDataFileItemProcessorTest {

    private InputDataFileItemProcessor processor;

    @Mock private ConsolidatedOutputDataFileService consolidatedOutputDataFileServiceMock;

    @BeforeEach
    void setup() {
        processor = new InputDataFileItemProcessor(consolidatedOutputDataFileServiceMock);
    }

    @Test
    @DisplayName("Should process for OutputDataFile")
    void shouldProcessForOutputDataFile() {

        var inputDataFileValid = createInputDataFileValid();
        var outputDataFileValid = createOutputDataFileValid();

        when(consolidatedOutputDataFileServiceMock.consolid(inputDataFileValid)).thenReturn(outputDataFileValid);

        var result = processor.process(inputDataFileValid);

        assertThat(result).isSameAs(outputDataFileValid);
    }

}
