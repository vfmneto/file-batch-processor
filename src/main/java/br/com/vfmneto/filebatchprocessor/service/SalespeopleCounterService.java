package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;

public interface SalespeopleCounterService {

    Long count(InputDataFile inputDataFile);
}
