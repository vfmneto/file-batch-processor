package br.com.vfmneto.filebatchprocessor.util;

import br.com.vfmneto.filebatchprocessor.model.InputFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;

import java.util.List;

public interface FileComponent {

    List<String> readAllLines(InputFile inputFile);

    void moveToProcessed(InputFile inputFile);

    List<InputFile> getFilesFromInDirectory();

    void writeToOutDirectory(OutputDataFile outputDataFile);

    void moveToError(InputFile inputFile);
}
