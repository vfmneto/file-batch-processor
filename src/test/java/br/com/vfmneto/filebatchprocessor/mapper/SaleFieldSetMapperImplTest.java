package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.model.SaleItemLineData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.file.transform.DefaultFieldSet;

import java.math.BigDecimal;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;
import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.SALESMAN_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class SaleFieldSetMapperImplTest {

    public static final String SAMPLE_TYPE = "003";
    private static final String SAMPLE_SALE_ID = "10";
    private static final String SAMPLE_ITEMS = "[1-10-100,2-30-2.5]";
    private static final String SAMPLE_SALESMAN_NAME = "SAMPLE SALESMAN NAME";

    private SaleFieldSetMapperImpl mapper;

    @BeforeEach
    void setup() {
        mapper = new SaleFieldSetMapperImpl();
    }

    @Test
    @DisplayName("Should map to Sale")
    void shouldMapToSale() {

        var tokens = new String[]{SAMPLE_TYPE, SAMPLE_SALE_ID, SAMPLE_ITEMS, SAMPLE_SALESMAN_NAME};
        var names = new String[]{TYPE, SALE_ID, ITEMS, SALESMAN_NAME};

        var saleLineData = mapper.mapFieldSet(new DefaultFieldSet(tokens, names));


        assertAll("Different sale than expected",
                () -> assertEquals(SAMPLE_TYPE, saleLineData.getType()),
                () -> assertEquals(Long.valueOf(SAMPLE_SALE_ID), saleLineData.getSaleId()),
                () -> assertEquals(SAMPLE_SALESMAN_NAME, saleLineData.getSalesmanName()),
                () -> assertThat(saleLineData.getSaleItemsLineData())
                        .extracting(SaleItemLineData::getIdItem, SaleItemLineData::getQuantity, SaleItemLineData::getPrice)
                        .contains(tuple(Long.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(100)),
                                  tuple(Long.valueOf(2), BigDecimal.valueOf(30), BigDecimal.valueOf(2.5))));
    }
}
