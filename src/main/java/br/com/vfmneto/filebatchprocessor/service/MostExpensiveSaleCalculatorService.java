package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.HasLineData;

public interface MostExpensiveSaleCalculatorService {

    Long calculate(HasLineData hasLineData);
}
