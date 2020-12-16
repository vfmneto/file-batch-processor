package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.service.*;
import org.springframework.stereotype.Service;

@Service
public class ConsolidatedOutputDataFileServiceImpl implements ConsolidatedOutputDataFileService {

    private final CustomerCounterService customerCounterService;
    private final SalesmanCounterService salespeopleCounterService;
    private final MostExpensiveSaleCalculatorService mostExpensiveSaleCalculatorService;
    private final WorstSalesmanCalculatorService worstSalesmanCalculatorService;

    public ConsolidatedOutputDataFileServiceImpl(CustomerCounterService customerCounterService,
                                                 SalesmanCounterService salespeopleCounterService,
                                                 MostExpensiveSaleCalculatorService mostExpensiveSaleCalculatorService,
                                                 WorstSalesmanCalculatorService worstSalesmanCalculatorService) {
        this.customerCounterService = customerCounterService;
        this.salespeopleCounterService = salespeopleCounterService;
        this.mostExpensiveSaleCalculatorService = mostExpensiveSaleCalculatorService;
        this.worstSalesmanCalculatorService = worstSalesmanCalculatorService;
    }

    @Override
    public OutputDataFile consolid(InputDataFile inputDataFile) {
        return new OutputDataFile.Builder()
                .withFileName(inputDataFile.getFileName())
                .withClientQuantity(customerCounterService.count(inputDataFile))
                .withSalespeopleQuantity(salespeopleCounterService.count(inputDataFile))
                .withMostExpensiveSaleId(mostExpensiveSaleCalculatorService.calculate(inputDataFile))
                .withWorstSalesman(worstSalesmanCalculatorService.calculate(inputDataFile))
                .build();
    }
}
