package br.com.vfmneto.filebatchprocessor.writer;

import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class FileItemWriter implements ItemWriter<OutputDataFile> {

    private final FileComponent fileComponent;

    public FileItemWriter(FileComponent fileComponent) {
        this.fileComponent = fileComponent;
    }

    @Override
    public void write(List<? extends OutputDataFile> itens) {
        itens.forEach(fileComponent::writeToOutDirectory);
    }
}
