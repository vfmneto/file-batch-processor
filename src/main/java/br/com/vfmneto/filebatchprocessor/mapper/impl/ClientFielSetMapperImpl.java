package br.com.vfmneto.filebatchprocessor.mapper.impl;

import br.com.vfmneto.filebatchprocessor.mapper.ClientFielSetMapper;
import br.com.vfmneto.filebatchprocessor.model.ClientLineData;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class ClientFielSetMapperImpl implements ClientFielSetMapper {

    @Override
    public ClientLineData mapFieldSet(FieldSet fieldSet) {
        var client = new ClientLineData();
        client.setType(fieldSet.readString(TYPE));
        client.setName(fieldSet.readString(NAME));
        client.setCnpj(fieldSet.readString(CNPJ));
        client.setBusinessArea(fieldSet.readString(BUSINESS_AREA));
        return client;
    }

}
