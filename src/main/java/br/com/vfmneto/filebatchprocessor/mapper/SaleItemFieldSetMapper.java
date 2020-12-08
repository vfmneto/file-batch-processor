package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.model.SaleItemLineData;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

public class SaleItemFieldSetMapper implements FieldSetMapper<SaleItemLineData> {

    @Override
    public SaleItemLineData mapFieldSet(FieldSet fieldSet) {
        var saleItem = new SaleItemLineData();
        saleItem.setIdItem(fieldSet.readLong(ID_ITEM));
        saleItem.setQuantity(fieldSet.readBigDecimal(QUANTITY));
        saleItem.setPrice(fieldSet.readBigDecimal(PRICE));
        return saleItem;
    }

}
