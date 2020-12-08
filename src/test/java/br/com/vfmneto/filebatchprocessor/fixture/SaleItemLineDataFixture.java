package br.com.vfmneto.filebatchprocessor.fixture;

import br.com.vfmneto.filebatchprocessor.model.SaleItemLineData;

import java.math.BigDecimal;

public class SaleItemLineDataFixture {

    public static SaleItemLineData createSaleItemLineData(Long idItem, BigDecimal quantity, BigDecimal price) {
        var saleItemLineData = new SaleItemLineData();
        saleItemLineData.setIdItem(idItem);
        saleItemLineData.setQuantity(quantity);
        saleItemLineData.setPrice(price);
        return saleItemLineData;
    }

}
