package br.com.vfmneto.filebatchprocessor.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;
import static org.assertj.core.api.Assertions.assertThat;

class ClientCounterServiceImplTest {

    private ClientCounterServiceImpl clientCounterService;

    @BeforeEach
    void setup() {
        clientCounterService = new ClientCounterServiceImpl();
    }

    @Test
    @DisplayName("Given that input data has two clients it should return number of clients two")
    void shouldReturnNumberOfClientsTwo() {

        var count = clientCounterService.count(createInputDataFileValid());
        assertThat(count).isEqualTo(2);
    }

}
