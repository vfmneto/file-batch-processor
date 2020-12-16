package br.com.vfmneto.filebatchprocessor.mapper.impl;

import br.com.vfmneto.filebatchprocessor.mapper.CustomerFielSetMapper;
import br.com.vfmneto.filebatchprocessor.model.CustomerLineData;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class CustomerFielSetMapperImpl implements CustomerFielSetMapper {

    @Override
    public CustomerLineData mapFieldSet(FieldSet fieldSet) {
        var customer = new CustomerLineData();
        customer.setType(fieldSet.readString(TYPE));
        customer.setName(fieldSet.readString(NAME));
        customer.setCnpj(fieldSet.readString(CNPJ));
        customer.setBusinessArea(fieldSet.readString(BUSINESS_AREA));
        return customer;
    }

}
