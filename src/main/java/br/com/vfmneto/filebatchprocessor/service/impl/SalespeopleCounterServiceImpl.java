package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.SalesmanLineData;
import br.com.vfmneto.filebatchprocessor.service.SalespeopleCounterService;
import org.springframework.stereotype.Component;

@Component
public class SalespeopleCounterServiceImpl implements SalespeopleCounterService {

    @Override
    public Long count(InputDataFile inputDataFile) {
        return inputDataFile.getLineData().stream().filter(line -> line instanceof SalesmanLineData).count();
    }
}
