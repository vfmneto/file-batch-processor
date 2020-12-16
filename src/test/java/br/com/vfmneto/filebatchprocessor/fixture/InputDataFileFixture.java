package br.com.vfmneto.filebatchprocessor.fixture;

import br.com.vfmneto.filebatchprocessor.model.CustomerLineData;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.LineData;
import br.com.vfmneto.filebatchprocessor.model.SalesmanLineData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.vfmneto.filebatchprocessor.fixture.SaleItemLineDataFixture.createSaleItemLineData;
import static br.com.vfmneto.filebatchprocessor.fixture.SaleLineDataFixture.createSaleLineData;
import static java.util.Arrays.asList;

public class InputDataFileFixture {

    public static final String SAMPLE_FILENAME = "filename.dat";
    public static final String SAMPLE_SALESMAN_NAME_ONE = "SALESMAN_NAME SAMPLE 1";
    public static final String SAMPLE_SALESMAN_NAME_TWO = "SALESMAN_NAME SAMPLE 2";;
    public static final Long SAMPLE_SALE_ID_ONE = 1l;
    public static final Long SAMPLE_SALE_ID_TWO = 2l;

    public static InputDataFile createInputDataFileValid() {
        List<LineData> linesData = new ArrayList<>();
        linesData.add(new CustomerLineData());
        linesData.add(new CustomerLineData());
        linesData.add(new SalesmanLineData());

        linesData.add(createSaleLineData(SAMPLE_SALE_ID_ONE, SAMPLE_SALESMAN_NAME_ONE, asList(createSaleItemLineData(1l, BigDecimal.ONE, BigDecimal.TEN))));
        linesData.add(createSaleLineData(SAMPLE_SALE_ID_TWO, SAMPLE_SALESMAN_NAME_TWO, asList(createSaleItemLineData(1l, BigDecimal.ONE, BigDecimal.valueOf(100)))));

        return new InputDataFile(SAMPLE_FILENAME, linesData);
    }

}
