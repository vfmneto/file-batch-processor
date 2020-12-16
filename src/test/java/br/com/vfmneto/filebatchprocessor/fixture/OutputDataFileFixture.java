package br.com.vfmneto.filebatchprocessor.fixture;

import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;

import static br.com.vfmneto.filebatchprocessor.fixture.InputDataFileFixture.*;

public class OutputDataFileFixture {

    public static OutputDataFile createOutputDataFileValid() {
        return new OutputDataFile.Builder()
                .withFileName(SAMPLE_FILENAME)
                .withClientQuantity(2l)
                .withSalespeopleQuantity(1l)
                .withMostExpensiveSaleId(SAMPLE_SALE_ID_TWO)
                .withWorstSalesman(SAMPLE_SALESMAN_NAME_ONE)
                .build();
    }
}
