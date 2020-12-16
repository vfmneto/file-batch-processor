package br.com.vfmneto.filebatchprocessor.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_SALESMAN_NAME_ONE;
import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;

class CalculatorWorstSalesmanServiceImplTest {

    private CalculatorWorstSalesmanServiceImpl calculatorWorstSalesmanService;

    @BeforeEach
    void setup() {
        calculatorWorstSalesmanService = new CalculatorWorstSalesmanServiceImpl();
    }

    @Test
    @DisplayName("Given that the seller one sold less should return joao as the worst one")
    void shouldReturnSalesmanOneAsTheWorst() {

        var result = calculatorWorstSalesmanService.calculate(createInputDataFileValid());
        Assertions.assertThat(result).isEqualTo(SAMPLE_SALESMAN_NAME_ONE);
    }

}
