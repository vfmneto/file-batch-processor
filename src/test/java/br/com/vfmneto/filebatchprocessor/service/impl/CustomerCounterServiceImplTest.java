package br.com.vfmneto.filebatchprocessor.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerCounterServiceImplTest {

    private CustomerCounterServiceImpl customerCounterService;

    @BeforeEach
    void setup() {
        customerCounterService = new CustomerCounterServiceImpl();
    }

    @Test
    @DisplayName("Given that input data has two customers it should return number of customers two")
    void shouldReturnNumberOfClientsTwo() {

        var count = customerCounterService.count(createInputDataFileValid());
        assertThat(count).isEqualTo(2);
    }

}
