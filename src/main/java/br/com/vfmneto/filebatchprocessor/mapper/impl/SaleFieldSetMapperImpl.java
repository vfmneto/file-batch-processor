package br.com.vfmneto.filebatchprocessor.mapper.impl;

import br.com.vfmneto.filebatchprocessor.mapper.SaleFieldSetMapper;
import br.com.vfmneto.filebatchprocessor.model.SaleItemLineData;
import br.com.vfmneto.filebatchprocessor.model.SaleLineData;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class SaleFieldSetMapperImpl implements SaleFieldSetMapper {

    private final DelimitedLineTokenizer saleItemLineTokenizer;
    private final SaleItemFieldSetMapper saleItemFieldSetMapper;

    public SaleFieldSetMapperImpl() {
        saleItemFieldSetMapper = new SaleItemFieldSetMapper();
        saleItemLineTokenizer = new DelimitedLineTokenizer("-");
        saleItemLineTokenizer.setNames(ID_ITEM, QUANTITY, PRICE);
    }

    @Override
    public SaleLineData mapFieldSet(FieldSet fieldSet) {
        var sale = new SaleLineData();
        sale.setType(fieldSet.readString(TYPE));
        sale.setSaleId(fieldSet.readLong(SALE_ID));
        sale.setSalesmanName(fieldSet.readString(SALESMAN_NAME));
        sale.setSaleItemsLineData(getItems(fieldSet.readString(ITEMS)));
        return sale;
    }

    private List<SaleItemLineData> getItems(String itens) {
        return getSaleItemsString(itens)
                .stream()
                .map(s -> saleItemFieldSetMapper.mapFieldSet(saleItemLineTokenizer.tokenize(s)))
                .collect(Collectors.toList());
    }

    private List<String> getSaleItemsString(String itens) {
        return Arrays.asList(itens.replace("[", "").replace("]", "").split(","));
    }

}
