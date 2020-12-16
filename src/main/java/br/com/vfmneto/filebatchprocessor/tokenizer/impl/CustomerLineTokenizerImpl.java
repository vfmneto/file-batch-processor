package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import br.com.vfmneto.filebatchprocessor.tokenizer.CustomerLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class CustomerLineTokenizerImpl extends AbstractDelimitedLineTokenizerImpl implements CustomerLineTokenizer {

    public CustomerLineTokenizerImpl() {
        super(TYPE, CNPJ, NAME, BUSINESS_AREA);
    }
}
