package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import br.com.vfmneto.filebatchprocessor.tokenizer.SalesmanLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class SalesmanLineTokenizerImpl extends AbstractDelimitedLineTokenizerImpl implements SalesmanLineTokenizer {

    public SalesmanLineTokenizerImpl() {
        super(TYPE, CPF, NAME, SALARY);
    }
}
