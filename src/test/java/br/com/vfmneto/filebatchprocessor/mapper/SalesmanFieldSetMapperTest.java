package br.com.vfmneto.filebatchprocessor.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;

import java.math.BigDecimal;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SalesmanFieldSetMapperTest {

    private static final String SAMPLE_TYPE = "002";
    private static final String SAMPLE_CPF = "1234567801";
    private static final String SAMPLE_NAME = "SAMPLE SALESMAN NAME";
    private static final String SAMPLE_SALARY = "5000.50";

    private SalesmanFieldSetMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new SalesmanFieldSetMapper();
    }

    @Test
    @DisplayName("Should map to Salesman")
    void shouldMapToSalesman() {
        var tokens = new String[]{SAMPLE_TYPE, SAMPLE_CPF, SAMPLE_NAME, SAMPLE_SALARY};
        var names = new String[]{TYPE, CPF, NAME, SALARY};

        var salesmanLineData = mapper.mapFieldSet(new DefaultFieldSet(tokens, names));

        assertAll("Different salesman than expected",
                () -> assertEquals(SAMPLE_TYPE, salesmanLineData.getType()),
                () -> assertEquals(SAMPLE_CPF, salesmanLineData.getCpf()),
                () -> assertEquals(SAMPLE_NAME, salesmanLineData.getName()),
                () -> assertEquals(new BigDecimal(SAMPLE_SALARY), salesmanLineData.getSalary()));
    }
}
