package br.com.vfmneto.filebatchprocessor.mapper.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerFielSetMapperImplTest {

    public static final String SAMPLE_TYPE = "001";
    public static final String SAMPLE_CNPJ = "12345678000195";
    public static final String SAMPLE_NAME = "SAMPLE CLIENT NAME";
    public static final String SAMPLE_BUSINESS_AREA = "SAMPLE BUSINESS AREA";

    private CustomerFielSetMapperImpl mapper;

    @BeforeEach
    void setup() {
        mapper = new CustomerFielSetMapperImpl();
    }

    @Test
    @DisplayName("Should map to Client")
    void shouldMapToClient() {

        var tokens = new String[]{SAMPLE_TYPE, SAMPLE_CNPJ, SAMPLE_NAME, SAMPLE_BUSINESS_AREA};
        var names = new String[]{TYPE, CNPJ, NAME, BUSINESS_AREA};

        var customerLineData = mapper.mapFieldSet(new DefaultFieldSet(tokens, names));

        assertAll("Different customer than expected",
               () -> assertEquals(SAMPLE_TYPE, customerLineData.getType()),
               () -> assertEquals(SAMPLE_NAME, customerLineData.getName()),
               () -> assertEquals(SAMPLE_BUSINESS_AREA, customerLineData.getBusinessArea()),
               () -> assertEquals(SAMPLE_CNPJ, customerLineData.getCnpj()));
    }
}
