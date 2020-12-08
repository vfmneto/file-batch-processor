package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.model.InputFile;
import br.com.vfmneto.filebatchprocessor.model.LineData;

import java.util.List;

public interface FileLineDataMapper {

    List<LineData> mapFile(InputFile inputFile) throws Exception;
}
