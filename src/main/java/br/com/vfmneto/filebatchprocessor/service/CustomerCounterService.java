package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.HasLineData;

public interface CustomerCounterService {

    Long count(HasLineData hasLineData);
}
