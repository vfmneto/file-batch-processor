package br.com.vfmneto.filebatchprocessor.reader;

import br.com.vfmneto.filebatchprocessor.exception.FileInvalidExtensionException;
import br.com.vfmneto.filebatchprocessor.mapper.FileLineDataMapper;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.InputFile;
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
    public InputDataFile read() {
        var inputFiles = fileComponent.getFilesFromInDirectory();
        var inputFileOptional = inputFiles.stream().findFirst();
        return inputFileOptional.map(this::readFile).orElse(null);
    }

    private InputDataFile readFile(InputFile inputFile) {
        try {
            validInputFileExtension(inputFile);
            var linesData = fileLineDataMapper.mapFile(inputFile);
            fileComponent.moveToProcessed(inputFile);
            return new InputDataFile(inputFile.getFilename(), linesData);
        } catch (FileInvalidExtensionException e) {
            fileComponent.moveToError(inputFile);
            return null;
        }
    }

    private void validInputFileExtension(InputFile inputFile) {
        if (inputFile.isInvalidExtension()) {
            throw new FileInvalidExtensionException(inputFile.getExtension());
        }
    }
}
