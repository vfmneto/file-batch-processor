package br.com.vfmneto.filebatchprocessor.processor;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_FILENAME;

class InputDataFileItemProcessorTest {

    private InputDataFileItemProcessor processor;

    @BeforeEach
    void setup() {
        processor = new InputDataFileItemProcessor();
    }

    @Test
    @DisplayName("Should process for OutputDataFile")
    void shouldProcessForOutputDataFile() {
        var process = processor.process(new InputDataFile(SAMPLE_FILENAME, new ArrayList<>()));
        Assertions.assertThat(process.getFileName()).isEqualTo(SAMPLE_FILENAME);
    }

}
