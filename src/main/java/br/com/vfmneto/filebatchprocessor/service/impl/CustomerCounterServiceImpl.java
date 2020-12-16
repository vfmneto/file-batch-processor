package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.CustomerLineData;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.service.CustomerCounterService;
import org.springframework.stereotype.Component;

@Component
public class CustomerCounterServiceImpl implements CustomerCounterService {

    @Override
    public Long count(InputDataFile inputDataFile) {
        return inputDataFile.getLineData().stream().filter(line -> line instanceof CustomerLineData).count();
    }
}
