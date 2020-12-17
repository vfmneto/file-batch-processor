package br.com.vfmneto.filebatchprocessor.batch.processor;

import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.service.ConsolidatedOutputDataFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class InputDataFileItemProcessor implements ItemProcessor<InputDataFile, OutputDataFile> {

    private static final Logger log = LoggerFactory.getLogger(InputDataFileItemProcessor.class);

    private final ConsolidatedOutputDataFileService consolidatedOutputDataFileService;

    public InputDataFileItemProcessor(ConsolidatedOutputDataFileService consolidatedOutputDataFileService) {
        this.consolidatedOutputDataFileService = consolidatedOutputDataFileService;
    }

    @Override
    public OutputDataFile process(InputDataFile inputDataFile) {
        var outputDataFile = consolidatedOutputDataFileService.consolid(inputDataFile);
        log.info("Processed file: {}", outputDataFile.getFileName());
        return outputDataFile;
    }
}
