package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.ClientLineData;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.service.ClientCounterService;
import org.springframework.stereotype.Component;

@Component
public class ClientCounterServiceImpl implements ClientCounterService {

    @Override
    public Long count(InputDataFile inputDataFile) {
        return inputDataFile.getLineData().stream().filter(line -> line instanceof ClientLineData).count();
    }
}
