package br.com.vfmneto.filebatchprocessor.mapper.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientFielSetMapperImplTest {

    public static final String SAMPLE_TYPE = "001";
    public static final String SAMPLE_CNPJ = "12345678000195";
    public static final String SAMPLE_NAME = "SAMPLE CLIENT NAME";
    public static final String SAMPLE_BUSINESS_AREA = "SAMPLE BUSINESS AREA";

    private ClientFielSetMapperImpl mapper;

    @BeforeEach
    void setup() {
        mapper = new ClientFielSetMapperImpl();
    }

    @Test
    @DisplayName("Should map to Client")
    void shouldMapToClient() {

        var tokens = new String[]{SAMPLE_TYPE, SAMPLE_CNPJ, SAMPLE_NAME, SAMPLE_BUSINESS_AREA};
        var names = new String[]{TYPE, CNPJ, NAME, BUSINESS_AREA};

        var clientLineData = mapper.mapFieldSet(new DefaultFieldSet(tokens, names));

        assertAll("Different client than expected",
               () -> assertEquals(SAMPLE_TYPE, clientLineData.getType()),
               () -> assertEquals(SAMPLE_NAME, clientLineData.getName()),
               () -> assertEquals(SAMPLE_BUSINESS_AREA, clientLineData.getBusinessArea()),
               () -> assertEquals(SAMPLE_CNPJ, clientLineData.getCnpj()));
    }
}
