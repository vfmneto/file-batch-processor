package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public abstract class AbstractDelimitedLineTokenizerImpl extends DelimitedLineTokenizer {

    public AbstractDelimitedLineTokenizerImpl(String... columnNames) {
        super("ç");
        setNames(columnNames);
    }
}
