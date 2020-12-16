package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.HasLineData;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractSaleServiceImpl {

    protected List<SaleLineData> getSaleLineData(HasLineData hasLineData) {
        return hasLineData.getLineData().stream()
                                    .filter(line -> line instanceof SaleLineData)
                                    .map(line -> (SaleLineData) line)
                                    .collect(Collectors.toList());
    }
}
