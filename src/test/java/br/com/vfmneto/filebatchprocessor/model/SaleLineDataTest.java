package br.com.vfmneto.filebatchprocessor.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.vfmneto.filebatchprocessor.fixture.SaleItemLineDataFixture.createSaleItemLineData;

class SaleLineDataTest {

    private SaleLineData saleLineData;

    @BeforeEach
    void setup() {
        saleLineData = new SaleLineData();
        List<SaleItemLineData> items = new ArrayList<>();
        items.add(createSaleItemLineData(1l, BigDecimal.valueOf(4), BigDecimal.valueOf(25)));
        items.add(createSaleItemLineData(2l, BigDecimal.valueOf(2), BigDecimal.valueOf(50)));
        saleLineData.setSaleItemsLineData(items);
    }

    @Test
    @DisplayName("Given that input data has 1 salespeople it should return number of salespeople one")
    void shouldReturnNumberOfSalesmanOne() {
        Assertions.assertThat(saleLineData.getValorTotalVenda().doubleValue()).isEqualTo(200);
    }

}
