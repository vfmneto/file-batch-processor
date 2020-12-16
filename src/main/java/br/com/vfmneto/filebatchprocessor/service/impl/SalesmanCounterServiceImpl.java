package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.HasLineData;
import br.com.vfmneto.filebatchprocessor.model.SalesmanLineData;
import br.com.vfmneto.filebatchprocessor.service.SalesmanCounterService;
import org.springframework.stereotype.Component;

@Component
public class SalesmanCounterServiceImpl implements SalesmanCounterService {

    @Override
    public Long count(HasLineData hasLineData) {
        return hasLineData.getLineData().stream().filter(line -> line instanceof SalesmanLineData).count();
    }
}
