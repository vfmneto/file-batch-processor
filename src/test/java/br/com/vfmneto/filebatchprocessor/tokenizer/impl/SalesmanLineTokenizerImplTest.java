package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class SalesmanLineTokenizerImplTest {

    @Test
        @DisplayName("When creating it should setup the client line")
    void shouldSetupTheClientLine() {
        var tokenizer = new SalesmanLineTokenizerImpl();
        var tokenize = tokenizer.tokenize("001ç1234567891234çPedroç50000");
        assertThat(tokenize.getNames()).containsExactly(TYPE, CPF, NAME, SALARY);
        assertThat(tokenize.getValues()).containsExactly("001", "1234567891234", "Pedro", "50000");
    }

}
