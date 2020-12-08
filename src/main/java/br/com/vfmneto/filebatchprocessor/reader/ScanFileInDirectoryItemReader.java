package br.com.vfmneto.filebatchprocessor.reader;

import br.com.vfmneto.filebatchprocessor.mapper.FileLineDataMapper;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.springframework.batch.item.ItemReader;

public class ScanFileInDirectoryItemReader implements ItemReader<InputDataFile> {

    private final FileLineDataMapper fileLineDataMapper;
    private final FileComponent fileComponent;

    public ScanFileInDirectoryItemReader(FileLineDataMapper fileLineDataMapper,
                                         FileComponent fileComponent) {
        this.fileLineDataMapper = fileLineDataMapper;
        this.fileComponent = fileComponent;
    }

    @Override
    public InputDataFile read() throws Exception {
        var inputFiles = fileComponent.getFilesFromInDirectory();
        var inputFileOptional = inputFiles.stream().findFirst();
        if (inputFileOptional.isPresent()) {
            var inputFile = inputFileOptional.get();
            if (inputFile.isValidExtension()) {
                var linesData = fileLineDataMapper.mapFile(inputFile);
                fileComponent.moveToProcessed(inputFile);
                return new InputDataFile(inputFile.getFilename(), linesData);
            } else {
                fileComponent.moveToError(inputFile);
                return null;
            }
        }
        return null;
    }

}
