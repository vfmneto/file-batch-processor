package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;

public interface MostExpensiveSaleCalculatorService {

    Long calculate(InputDataFile inputDataFile);
}
