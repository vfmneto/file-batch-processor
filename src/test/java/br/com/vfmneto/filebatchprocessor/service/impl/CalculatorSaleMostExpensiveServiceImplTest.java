package br.com.vfmneto.filebatchprocessor.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_SALE_ID_TWO;
import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;

class CalculatorSaleMostExpensiveServiceImplTest {

    private CalculatorSaleMostExpensiveServiceImpl calculatorSaleMostExpensiveService;

    @BeforeEach
    void setup() {
        calculatorSaleMostExpensiveService = new CalculatorSaleMostExpensiveServiceImpl();
    }

    @Test
    @DisplayName("Given that sale two was the most expensive one should sale two as most expensive")
    void shouldSaleOneAsMostExpensive() {
        
        var result = calculatorSaleMostExpensiveService.calculate(createInputDataFileValid());
        Assertions.assertThat(result).isEqualTo(SAMPLE_SALE_ID_TWO);
    }

}
