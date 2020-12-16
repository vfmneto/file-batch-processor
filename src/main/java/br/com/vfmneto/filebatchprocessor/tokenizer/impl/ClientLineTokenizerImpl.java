package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import br.com.vfmneto.filebatchprocessor.tokenizer.ClientLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class ClientLineTokenizerImpl extends AbstractDelimitedLineTokenizerImpl implements ClientLineTokenizer {

    public ClientLineTokenizerImpl() {
        super(TYPE, CNPJ, NAME, BUSINESS_AREA);
    }
}
