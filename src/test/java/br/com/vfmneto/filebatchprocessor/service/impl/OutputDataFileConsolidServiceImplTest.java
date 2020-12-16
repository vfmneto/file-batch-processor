package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture;
import br.com.vfmneto.filebatchprocessor.service.CalculatorSaleMostExpensiveService;
import br.com.vfmneto.filebatchprocessor.service.CalculatorWorstSalesmanService;
import br.com.vfmneto.filebatchprocessor.service.ClientCounterService;
import br.com.vfmneto.filebatchprocessor.service.SalespeopleCounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.SAMPLE_SALESMAN_NAME_ONE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutputDataFileConsolidServiceImplTest {

    public static final long CLIENT_QUANTITY = 2;
    public static final long SALESPEOPLE_QUANTITY = 4;
    public static final long MOST_EXPENSIVE_SALE_ID = 20;

    private OutputDataFileConsolidServiceImpl consolidService;

    @Mock private ClientCounterService clientCounterServiceMock;
    @Mock private SalespeopleCounterService salespeopleCounterServiceMock;
    @Mock private CalculatorSaleMostExpensiveService calculatorSaleMostExpensiveServiceMock;
    @Mock private CalculatorWorstSalesmanService calculatorWorstSalesmanServiceMock;

    @BeforeEach
    void setup() {
        consolidService = new OutputDataFileConsolidServiceImpl(clientCounterServiceMock,
                                                                salespeopleCounterServiceMock,
                                                                calculatorSaleMostExpensiveServiceMock,
                                                                calculatorWorstSalesmanServiceMock);
    }

    @Test
    @DisplayName("Given InputDataFile should consolid to OutputDataFile")
    void shouldConsolidToOutputDataFile() {

        var inputDataFileValid = InputDataFileFixture.createInputDataFileValid();

        when(clientCounterServiceMock.count(inputDataFileValid)).thenReturn(CLIENT_QUANTITY);
        when(salespeopleCounterServiceMock.count(inputDataFileValid)).thenReturn(SALESPEOPLE_QUANTITY);
        when(calculatorSaleMostExpensiveServiceMock.calculate(inputDataFileValid)).thenReturn(MOST_EXPENSIVE_SALE_ID);
        when(calculatorWorstSalesmanServiceMock.calculate(inputDataFileValid)).thenReturn(SAMPLE_SALESMAN_NAME_ONE);

        var result = consolidService.consolid(inputDataFileValid);

        assertAll(() -> assertEquals(CLIENT_QUANTITY, result.getClientQuantity()),
                  () -> assertEquals(SALESPEOPLE_QUANTITY, result.getSalespeopleQuantity()),
                  () -> assertEquals(MOST_EXPENSIVE_SALE_ID, result.getMostExpensiveSaleId()),
                  () -> assertEquals(SAMPLE_SALESMAN_NAME_ONE, result.getWorstSalesman()));
    }

}
