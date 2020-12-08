package br.com.vfmneto.filebatchprocessor.config;

import br.com.vfmneto.filebatchprocessor.mapper.*;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.processor.InputDataFileItemProcessor;
import br.com.vfmneto.filebatchprocessor.reader.ScanFileInDirectoryItemReader;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import br.com.vfmneto.filebatchprocessor.util.FileComponentImpl;
import br.com.vfmneto.filebatchprocessor.writer.FileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static br.com.vfmneto.filebatchprocessor.mapper.MapperConstants.*;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public static final String SALESMAN_TYPE = "001*";
    public static final String CLIENT_TYPE = "002*";
    public static final String SALE_TYPE = "003*";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public Job readFilesJob() {
        return jobBuilderFactory
                .get("readFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<InputDataFile, OutputDataFile>chunk(1)
                .reader(fileItemReader())
                .processor(fileItemProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    public ScanFileInDirectoryItemReader fileItemReader() {
        ScanFileInDirectoryItemReader scanFileInDirectoryItemReader =
                new ScanFileInDirectoryItemReader(lineDataMapper(),
                                                  fileComponent()
                );
        return scanFileInDirectoryItemReader;
    }

    @Bean
    public ItemProcessor<InputDataFile, OutputDataFile> fileItemProcessor() {
        return new InputDataFileItemProcessor();
    }

    @Bean
    public FileItemWriter writer() {
        return new FileItemWriter(fileComponent());
    }

    @Bean
    public PatternMatchingCompositeLineMapper compositeLineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();

        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put(SALESMAN_TYPE, salesmanTokenizer());
        tokenizers.put(CLIENT_TYPE, clientTokenizer());
        tokenizers.put(SALE_TYPE, saleTokenizer());

        lineMapper.setTokenizers(tokenizers);

        Map<String, FieldSetMapper> mappers = new HashMap<>();
        mappers.put(SALESMAN_TYPE, new SalesmanFieldSetMapper());
        mappers.put(CLIENT_TYPE, new ClientFielSetMapper());
        mappers.put(SALE_TYPE, new SaleFieldSetMapper());

        lineMapper.setFieldSetMappers(mappers);

        return lineMapper;
    }

    @Bean
    public FileLineDataMapper lineDataMapper() {
        return new FileLineDataMapperImpl(compositeLineMapper(), fileComponent(), applicationProperties);
    }

    @Bean
    public FileComponent fileComponent() {
        return new FileComponentImpl(applicationProperties);
    }

    private LineTokenizer salesmanTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("ç");
        lineTokenizer.setNames(TYPE, CPF, NAME, SALARY);
        return lineTokenizer;
    }

    private LineTokenizer clientTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("ç");
        lineTokenizer.setNames(TYPE, CNPJ, NAME, BUSINESS_AREA);
        return lineTokenizer;
    }

    private LineTokenizer saleTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("ç");
        lineTokenizer.setNames(TYPE, SALE_ID, ITEMS, SALESMAN_NAME);
        return lineTokenizer;
    }
}
