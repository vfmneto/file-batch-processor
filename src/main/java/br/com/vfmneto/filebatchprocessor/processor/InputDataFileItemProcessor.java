package br.com.vfmneto.filebatchprocessor.processor;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class InputDataFileItemProcessor implements ItemProcessor<InputDataFile, OutputDataFile> {

    private static final Logger log = LoggerFactory.getLogger(InputDataFileItemProcessor.class);

    @Override
    public OutputDataFile process(InputDataFile inputDataFile) {
        var outputDataFile = new OutputDataFile(inputDataFile);
        log.info("Processed file: {}", outputDataFile.getFileName());
        return outputDataFile;
    }
}
