package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.service.*;
import org.springframework.stereotype.Service;

@Service
public class OutputDataFileConsolidServiceImpl implements OutputDataFileConsolidService {

    private final ClientCounterService clientCounterService;
    private final SalespeopleCounterService salespeopleCounterService;
    private final CalculatorSaleMostExpensiveService calculatorSaleMostExpensiveService;
    private final CalculatorWorstSalesmanService calculatorWorstSalesmanService;

    public OutputDataFileConsolidServiceImpl(ClientCounterService clientCounterService,
                                             SalespeopleCounterService salespeopleCounterService,
                                             CalculatorSaleMostExpensiveService calculatorSaleMostExpensiveService,
                                             CalculatorWorstSalesmanService calculatorWorstSalesmanService) {
        this.clientCounterService = clientCounterService;
        this.salespeopleCounterService = salespeopleCounterService;
        this.calculatorSaleMostExpensiveService = calculatorSaleMostExpensiveService;
        this.calculatorWorstSalesmanService = calculatorWorstSalesmanService;
    }

    @Override
    public OutputDataFile consolid(InputDataFile inputDataFile) {
        return new OutputDataFile.Builder()
                .withFileName(inputDataFile.getFileName())
                .withClientQuantity(clientCounterService.count(inputDataFile))
                .withSalespeopleQuantity(salespeopleCounterService.count(inputDataFile))
                .withMostExpensiveSaleId(calculatorSaleMostExpensiveService.calculate(inputDataFile))
                .withWorstSalesman(calculatorWorstSalesmanService.calculate(inputDataFile))
                .build();
    }
}
