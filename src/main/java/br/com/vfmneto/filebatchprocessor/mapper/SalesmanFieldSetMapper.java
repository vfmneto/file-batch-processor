package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.model.SalesmanLineData;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

public class SalesmanFieldSetMapper implements FieldSetMapper<SalesmanLineData> {

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
