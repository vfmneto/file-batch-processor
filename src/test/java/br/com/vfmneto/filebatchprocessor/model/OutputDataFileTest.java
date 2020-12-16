package br.com.vfmneto.filebatchprocessor.model;

import br.com.vfmneto.filebatchprocessor.fixture.OutputDataFileFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_FILENAME;
import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_SALESMAN_NAME_ONE;

public class OutputDataFileTest {

    private OutputDataFile outputDataFile;

    @BeforeEach
    void setup() {
        outputDataFile = OutputDataFileFixture.createOutputDataFileValid();
    }

    @Test
    @DisplayName("Should return name of the informing file by the input data")
    void shouldReturnNameOfTheInformingFileByTheInputData() {
        Assertions.assertThat(outputDataFile.getFileName()).isEqualTo(SAMPLE_FILENAME);
    }

    @Test
    @DisplayName("Should return rows with processed data")
    void shouldReturnRowsWithProcessedData() {
        Assertions.assertThat(outputDataFile.getLines())
                .contains("Quantidade de clientes no arquivo de entrada: 2",
                          "Quantidade de vendedor no arquivo de entrada: 1",
                          "ID da venda mais cara: 2",
                          "O pior vendedor: " + SAMPLE_SALESMAN_NAME_ONE);
    }

}
