package br.com.vfmneto.filebatchprocessor.service.impl;

import br.com.vfmneto.filebatchprocessor.model.CustomerLineData;
import br.com.vfmneto.filebatchprocessor.model.HasLineData;
import br.com.vfmneto.filebatchprocessor.service.CustomerCounterService;
import org.springframework.stereotype.Component;

@Component
public class CustomerCounterServiceImpl implements CustomerCounterService {

    @Override
    public Long count(HasLineData hasLineData) {
        return hasLineData.getLineData().stream().filter(line -> line instanceof CustomerLineData).count();
    }
}
