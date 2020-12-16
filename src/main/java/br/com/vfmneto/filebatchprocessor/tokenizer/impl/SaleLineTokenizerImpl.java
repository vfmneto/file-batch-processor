package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import br.com.vfmneto.filebatchprocessor.tokenizer.SaleLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class SaleLineTokenizerImpl extends AbstractDelimitedLineTokenizerImpl implements SaleLineTokenizer {

    public SaleLineTokenizerImpl() {
        super(TYPE, SALE_ID, ITEMS, SALESMAN_NAME);
    }
}
