package br.com.vfmneto.filebatchprocessor.service;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;

public interface OutputDataFileConsolidService {

    OutputDataFile consolid(InputDataFile inputDataFile);
}
