package br.com.vfmneto.filebatchprocessor.processor;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.service.OutputDataFileConsolidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class InputDataFileItemProcessor implements ItemProcessor<InputDataFile, OutputDataFile> {

    private static final Logger log = LoggerFactory.getLogger(InputDataFileItemProcessor.class);

    private final OutputDataFileConsolidService outputDataFileConsolidService;

    public InputDataFileItemProcessor(OutputDataFileConsolidService outputDataFileConsolidService) {
        this.outputDataFileConsolidService = outputDataFileConsolidService;
    }

    @Override
    public OutputDataFile process(InputDataFile inputDataFile) {
        var outputDataFile = outputDataFileConsolidService.consolid(inputDataFile);
        log.info("Processed file: {}", outputDataFile.getFileName());
        return outputDataFile;
    }
}
