package br.com.vfmneto.filebatchprocessor.mapper.impl;

import br.com.vfmneto.filebatchprocessor.mapper.SalesmanFieldSetMapper;
import br.com.vfmneto.filebatchprocessor.model.SalesmanLineData;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Component
public class SalesmanFieldSetMapperImpl implements SalesmanFieldSetMapper {

    @Override
    public SalesmanLineData mapFieldSet(FieldSet fieldSet) {
        SalesmanLineData salesman = new SalesmanLineData();
        salesman.setType(fieldSet.readString(TYPE));
        salesman.setCpf(fieldSet.readString(CPF));
        salesman.setName(fieldSet.readString(NAME));
        salesman.setSalary(fieldSet.readBigDecimal(SALARY));
        return salesman;
    }

}
