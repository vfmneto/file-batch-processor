package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.HasLineData;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;
import br.com.vfmneto.filebatchprocessor.service.WorstSalesmanCalculatorService;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Comparator.comparing;

@Component
public class WorstSalesmanCalculatorServiceImpl extends AbstractSaleServiceImpl implements WorstSalesmanCalculatorService {

    @Override
    public String calculate(HasLineData hasLineData) {

        return getWorstSalesman(hasLineData).map(saleLineData -> saleLineData.getSalesmanName()).orElse(null);
    }

    private Optional<SaleLineData> getWorstSalesman(HasLineData hasLineData) {
        return getSaleLineData(hasLineData).stream().min(comparing(SaleLineData::getValorTotalVenda));
    }
}
