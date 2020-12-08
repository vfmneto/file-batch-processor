package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.model.ClientLineData;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

public class ClientFielSetMapper implements FieldSetMapper<ClientLineData> {

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
