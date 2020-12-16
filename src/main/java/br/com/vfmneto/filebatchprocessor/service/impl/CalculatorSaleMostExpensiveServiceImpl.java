package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;
import br.com.vfmneto.filebatchprocessor.service.CalculatorSaleMostExpensiveService;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Comparator.comparing;

@Component
public class CalculatorSaleMostExpensiveServiceImpl extends AbstractSaleServiceImpl implements CalculatorSaleMostExpensiveService {

    public Long calculate(InputDataFile inputDataFile) {
        return getMostExpensiveSaleId(inputDataFile).map(SaleLineData::getSaleId).orElse(null);
    }

    private Optional<SaleLineData> getMostExpensiveSaleId(InputDataFile inputDataFile) {
        return getSaleLineData(inputDataFile).stream().max(comparing(SaleLineData::getValorTotalVenda));
    }
}
