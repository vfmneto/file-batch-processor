package br.com.vfmneto.filebatchprocessor.fixture;

import br.com.vfmneto.filebatchprocessor.model.SaleItemLineData;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;

import java.util.List;

public class SaleLineDataFixture {

    public static SaleLineData createSaleLineData(Long sampleSaleIdOne, String sampleSalesmanNameOne, List<SaleItemLineData> items) {
        var saleLineData = new SaleLineData();
        saleLineData.setSalesmanName(sampleSalesmanNameOne);
        saleLineData.setSaleId(sampleSaleIdOne);
        saleLineData.setSaleItemsLineData(items);
        return saleLineData;
    }

}
