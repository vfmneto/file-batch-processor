package br.com.vfmneto.filebatchprocessor.tokenizer;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class SaleLineTokenizerImpl extends DelimitedLineTokenizer implements SaleLineTokenizer {

    public SaleLineTokenizerImpl() {
        super("ç");
        setNames(TYPE, SALE_ID, ITEMS, SALESMAN_NAME);
    }
}
