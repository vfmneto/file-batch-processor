package br.com.vfmneto.filebatchprocessor.mapper;

import br.com.vfmneto.filebatchprocessor.config.ApplicationProperties;
import br.com.vfmneto.filebatchprocessor.exception.UnsupportedLineException;
import br.com.vfmneto.filebatchprocessor.model.InputFile;
import br.com.vfmneto.filebatchprocessor.model.LineData;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;

import java.util.List;
import java.util.stream.Collectors;

public class FileLineDataMapperImpl implements FileLineDataMapper {

    private static final Logger log = LoggerFactory.getLogger(FileLineDataMapperImpl.class);

    private final PatternMatchingCompositeLineMapper compositeLineMapper;
    private FileComponent fileComponent;
    private ApplicationProperties applicationProperties;

    public FileLineDataMapperImpl(PatternMatchingCompositeLineMapper compositeLineMapper,
                                  FileComponent fileComponent,
                                  ApplicationProperties applicationProperties) {
        this.compositeLineMapper = compositeLineMapper;
        this.fileComponent = fileComponent;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public List<LineData> mapFile(InputFile inputFile) {
        List<String> linesString = fileComponent.readAllLines(inputFile);
        var linesData = linesString.stream().map(s -> mapLineToObject(s, inputFile)).collect(Collectors.toList());
        log.info("Successfully reading the file in: {}", inputFile.getPath());
        return linesData;
    }

    private LineData mapLineToObject(String line, InputFile inputFile) {
        try {
            return (LineData) compositeLineMapper.mapLine(line, 0);
        } catch (Exception e) {
            fileComponent.moveToError(inputFile);
            throw new UnsupportedLineException(e);
        }
    }

}
