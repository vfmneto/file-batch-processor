package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;
import br.com.vfmneto.filebatchprocessor.service.CalculatorWorstSalesmanService;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Comparator.comparing;

@Component
public class CalculatorWorstSalesmanServiceImpl extends AbstractSaleServiceImpl implements CalculatorWorstSalesmanService {

    @Override
    public String calculate(InputDataFile inputDataFile) {

        return getWorstSalesman(inputDataFile).map(saleLineData -> saleLineData.getSalesmanName()).orElse(null);
    }

    private Optional<SaleLineData> getWorstSalesman(InputDataFile inputDataFile) {
        return getSaleLineData(inputDataFile).stream().min(comparing(SaleLineData::getValorTotalVenda));
    }
}
