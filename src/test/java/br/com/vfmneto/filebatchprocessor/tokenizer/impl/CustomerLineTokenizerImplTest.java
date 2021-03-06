package br.com.vfmneto.filebatchprocessor.tokenizer.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerLineTokenizerImplTest {

    @Test
    @DisplayName("When creating it should setup the customer line")
    void shouldSetupTheCustomerLine() {
        var tokenizer = new CustomerLineTokenizerImpl();
        var tokenize = tokenizer.tokenize("002ç2345675434544345çJose da SilvaçRural");
        assertThat(tokenize.getNames()).containsExactly(TYPE, CNPJ, NAME, BUSINESS_AREA);
        assertThat(tokenize.getValues()).containsExactly("002", "2345675434544345", "Jose da Silva", "Rural");
    }

}
