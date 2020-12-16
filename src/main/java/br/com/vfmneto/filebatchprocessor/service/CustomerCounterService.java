package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;

public interface CustomerCounterService {

    Long count(InputDataFile inputDataFile);
}
