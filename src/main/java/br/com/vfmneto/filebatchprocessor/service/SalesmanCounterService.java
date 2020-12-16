package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;

public interface SalesmanCounterService {

    Long count(InputDataFile inputDataFile);
}
