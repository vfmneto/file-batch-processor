package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.HasLineData;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;
import br.com.vfmneto.filebatchprocessor.service.MostExpensiveSaleCalculatorService;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Comparator.comparing;

@Component
public class MostExpensiveSaleCalculatorServiceImpl extends AbstractSaleServiceImpl implements MostExpensiveSaleCalculatorService {

    public Long calculate(HasLineData hasLineData) {
        return getMostExpensiveSaleId(hasLineData).map(SaleLineData::getSaleId).orElse(null);
    }

    private Optional<SaleLineData> getMostExpensiveSaleId(HasLineData hasLineData) {
        return getSaleLineData(hasLineData).stream().max(comparing(SaleLineData::getValorTotalVenda));
    }
}
