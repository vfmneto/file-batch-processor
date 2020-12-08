package br.com.vfmneto.filebatchprocessor.tokenizer;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class ClientLineTokenizerImpl extends DelimitedLineTokenizer implements ClientLineTokenizer {

    public ClientLineTokenizerImpl() {
        super("ç");
        setNames(TYPE, CNPJ, NAME, BUSINESS_AREA);
    }
}
