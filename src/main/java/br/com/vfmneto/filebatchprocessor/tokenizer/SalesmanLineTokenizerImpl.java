package br.com.vfmneto.filebatchprocessor.tokenizer;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class SalesmanLineTokenizerImpl extends DelimitedLineTokenizer implements SalesmanLineTokenizer {

    public SalesmanLineTokenizerImpl() {
        super("ç");
        setNames(TYPE, CPF, NAME, SALARY);
    }
}
