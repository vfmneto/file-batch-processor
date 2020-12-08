package br.com.vfmneto.filebatchprocessor.model;

import java.math.BigDecimal;

public class SaleItemLineData {

    private Long idItem;
    private BigDecimal quantity;
    private BigDecimal price;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
