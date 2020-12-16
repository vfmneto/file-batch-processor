package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture;
import br.com.vfmneto.filebatchprocessor.service.MostExpensiveSaleCalculatorService;
import br.com.vfmneto.filebatchprocessor.service.WorstSalesmanCalculatorService;
import br.com.vfmneto.filebatchprocessor.service.CustomerCounterService;
import br.com.vfmneto.filebatchprocessor.service.SalesmanCounterService;
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
class ConsolidatedOutputDataFileServiceImplTest {

    public static final long CLIENT_QUANTITY = 2;
    public static final long SALESPEOPLE_QUANTITY = 4;
    public static final long MOST_EXPENSIVE_SALE_ID = 20;

    private ConsolidatedOutputDataFileServiceImpl consolidService;

    @Mock private CustomerCounterService customerCounterServiceMock;
    @Mock private SalesmanCounterService salesmanCounterServiceMock;
    @Mock private MostExpensiveSaleCalculatorService mostExpensiveSaleCalculatorServiceMock;
    @Mock private WorstSalesmanCalculatorService worstSalesmanCalculatorServiceMock;

    @BeforeEach
    void setup() {
        consolidService = new ConsolidatedOutputDataFileServiceImpl(customerCounterServiceMock,
                salesmanCounterServiceMock,
                mostExpensiveSaleCalculatorServiceMock,
                worstSalesmanCalculatorServiceMock);
    }

    @Test
    @DisplayName("Given InputDataFile should consolid to OutputDataFile")
    void shouldConsolidToOutputDataFile() {

        var inputDataFileValid = InputDataFileFixture.createInputDataFileValid();

        when(customerCounterServiceMock.count(inputDataFileValid)).thenReturn(CLIENT_QUANTITY);
        when(salesmanCounterServiceMock.count(inputDataFileValid)).thenReturn(SALESPEOPLE_QUANTITY);
        when(mostExpensiveSaleCalculatorServiceMock.calculate(inputDataFileValid)).thenReturn(MOST_EXPENSIVE_SALE_ID);
        when(worstSalesmanCalculatorServiceMock.calculate(inputDataFileValid)).thenReturn(SAMPLE_SALESMAN_NAME_ONE);

        var result = consolidService.consolid(inputDataFileValid);

        assertAll(() -> assertEquals(CLIENT_QUANTITY, result.getCustomerQuantity()),
                  () -> assertEquals(SALESPEOPLE_QUANTITY, result.getSalespeopleQuantity()),
                  () -> assertEquals(MOST_EXPENSIVE_SALE_ID, result.getMostExpensiveSaleId()),
                  () -> assertEquals(SAMPLE_SALESMAN_NAME_ONE, result.getWorstSalesman()));
    }

}
