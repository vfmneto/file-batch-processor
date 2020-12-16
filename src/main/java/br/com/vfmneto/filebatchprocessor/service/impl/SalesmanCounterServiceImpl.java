package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.SalesmanLineData;
import br.com.vfmneto.filebatchprocessor.service.SalesmanCounterService;
import org.springframework.stereotype.Component;

@Component
public class SalesmanCounterServiceImpl implements SalesmanCounterService {

    @Override
    public Long count(InputDataFile inputDataFile) {
        return inputDataFile.getLineData().stream().filter(line -> line instanceof SalesmanLineData).count();
    }
}
