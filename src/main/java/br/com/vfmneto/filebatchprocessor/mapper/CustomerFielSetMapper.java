package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.model.CustomerLineData;
import org.springframework.batch.item.file.mapping.FieldSetMapper;

public interface CustomerFielSetMapper extends FieldSetMapper<CustomerLineData> {
}
