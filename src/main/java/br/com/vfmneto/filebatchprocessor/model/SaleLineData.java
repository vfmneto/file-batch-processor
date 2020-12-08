package br.com.vfmneto.filebatchprocessor.model;

import java.math.BigDecimal;
import java.util.List;

public class SaleLineData extends LineData {

    private Long saleId;
    private String salesmanName;
    private List<SaleItemLineData> saleItemsLineData;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public List<SaleItemLineData> getSaleItemsLineData() {
        return saleItemsLineData;
    }

    public void setSaleItemsLineData(List<SaleItemLineData> saleItemsLineData) {
        this.saleItemsLineData = saleItemsLineData;
    }

    public BigDecimal getValorTotalVenda() {
        return saleItemsLineData
                .stream()
                .map(itemVenda -> itemVenda.getQuantity().multiply(itemVenda.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
