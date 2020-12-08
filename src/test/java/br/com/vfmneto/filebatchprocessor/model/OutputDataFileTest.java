package br.com.vfmneto.filebatchprocessor.model;

import br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.*;

public class OutputDataFileTest {

    private OutputDataFile outputDataFile;

    @BeforeEach
    void setup() {
        outputDataFile = new OutputDataFile(InputDataFileFixture.createInputDataFileValid());
    }

    @Test
    @DisplayName("Given that input data has two clients it should return number of clients two")
    void shouldReturnNumberOfClientsTwo() {
        Assertions.assertThat(outputDataFile.getClientQuantity()).isEqualTo(2);
    }

    @Test
    @DisplayName("Given that input data has 1 salespeople it should return number of salespeople one")
    void shouldReturnNumberOfSalesmanOne() {
        Assertions.assertThat(outputDataFile.getSalespeopleQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("Given that the seller one sold less should return joao as the worst one")
    void shouldReturnSalesmanOneAsTheWorst() {
        Assertions.assertThat(outputDataFile.getWorstSalesman()).isEqualTo(SAMPLE_SALESMAN_NAME_ONE);
    }

    @Test
    @DisplayName("Given that sale two was the most expensive one should sale two as most expensive")
    void shouldSaleOneAsMostExpensive() {
        Assertions.assertThat(outputDataFile.getMostExpensiveSaleId()).isEqualTo(SAMPLE_SALE_ID_TWO);
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
