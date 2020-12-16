package br.com.vfmneto.filebatchprocessor.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;

class SalespeopleCounterServiceImplTest {

    private SalespeopleCounterServiceImpl salespeopleCounterService;

    @BeforeEach
    void setup() {
        salespeopleCounterService = new SalespeopleCounterServiceImpl();
    }

    @Test
    @DisplayName("Given that input data has 1 salespeople it should return number of salespeople one")
    void shouldReturnNumberOfSalesmanOne() {

        var result = salespeopleCounterService.count(createInputDataFileValid());
        Assertions.assertThat(result).isEqualTo(1);
    }

}
