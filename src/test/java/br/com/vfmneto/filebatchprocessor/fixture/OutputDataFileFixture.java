package br.com.vfmneto.filebatchprocessor.fixture;

import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.createInputDataFileValid;

public class OutputDataFileFixture {

    public static OutputDataFile createOutputDataFileValid() {
        return new OutputDataFile(createInputDataFileValid());
    }
}
