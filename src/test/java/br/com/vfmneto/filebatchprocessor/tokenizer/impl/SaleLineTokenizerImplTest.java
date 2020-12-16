package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class SaleLineTokenizerImplTest {

    @Test
    @DisplayName("When creating it should setup the client line")
    void shouldSetupTheClientLine() {
        var tokenizer = new SaleLineTokenizerImpl();
        var tokenize = tokenizer.tokenize("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        assertThat(tokenize.getNames()).containsExactly(TYPE, SALE_ID, ITEMS, SALESMAN_NAME);
        assertThat(tokenize.getValues()).containsExactly("003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro");
    }

}
