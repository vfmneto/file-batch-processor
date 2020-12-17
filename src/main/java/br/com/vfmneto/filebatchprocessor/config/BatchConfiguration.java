package br.com.vfmneto.filebatchprocessor.config;

import br.com.vfmneto.filebatchprocessor.mapper.CustomerFielSetMapper;
import br.com.vfmneto.filebatchprocessor.mapper.FileLineDataMapper;
import br.com.vfmneto.filebatchprocessor.mapper.SalesmanFieldSetMapper;
import br.com.vfmneto.filebatchprocessor.mapper.impl.SaleFieldSetMapperImpl;
import br.com.vfmneto.filebatchprocessor.model.InputDataFile;
import br.com.vfmneto.filebatchprocessor.model.OutputDataFile;
import br.com.vfmneto.filebatchprocessor.batch.processor.InputDataFileItemProcessor;
import br.com.vfmneto.filebatchprocessor.batch.reader.ScanFileInDirectoryItemReader;
import br.com.vfmneto.filebatchprocessor.service.ConsolidatedOutputDataFileService;
import br.com.vfmneto.filebatchprocessor.tokenizer.CustomerLineTokenizer;
import br.com.vfmneto.filebatchprocessor.tokenizer.SaleLineTokenizer;
import br.com.vfmneto.filebatchprocessor.tokenizer.SalesmanLineTokenizer;
import br.com.vfmneto.filebatchprocessor.util.FileComponent;
import br.com.vfmneto.filebatchprocessor.batch.writer.FileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
    private SalesmanLineTokenizer salesmanLineTokenizer;

    @Autowired
    private CustomerLineTokenizer customerLineTokenizer;

    @Autowired
    private SaleLineTokenizer saleLineTokenizer;

    @Autowired
    private SalesmanFieldSetMapper salesmanFieldSetMapper;

    @Autowired
    private CustomerFielSetMapper customerFielSetMapperImpl;

    @Autowired
    private SaleFieldSetMapperImpl saleFieldSetMapperImpl;

    @Autowired
    private FileLineDataMapper lineDataMapper;

    @Autowired
    private FileComponent fileComponent;

    @Autowired
    private ConsolidatedOutputDataFileService consolidatedOutputDataFileService;

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
        return stepBuilderFactory.get("step").<InputDataFile, OutputDataFile>chunk(5)
                .reader(fileItemReader())
                .processor(fileItemProcessor())
                .writer(writer())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public ScanFileInDirectoryItemReader fileItemReader() {
        ScanFileInDirectoryItemReader scanFileInDirectoryItemReader =
                new ScanFileInDirectoryItemReader(lineDataMapper, fileComponent);
        return scanFileInDirectoryItemReader;
    }

    @Bean
    public ItemProcessor<InputDataFile, OutputDataFile> fileItemProcessor() {
        return new InputDataFileItemProcessor(consolidatedOutputDataFileService);
    }

    @Bean
    public FileItemWriter writer() {
        return new FileItemWriter(fileComponent);
    }

    @Bean
    public PatternMatchingCompositeLineMapper compositeLineMapper() {
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();

        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put(SALESMAN_TYPE, salesmanLineTokenizer);
        tokenizers.put(CLIENT_TYPE, customerLineTokenizer);
        tokenizers.put(SALE_TYPE, saleLineTokenizer);

        lineMapper.setTokenizers(tokenizers);

        Map<String, FieldSetMapper> mappers = new HashMap<>();
        mappers.put(SALESMAN_TYPE, salesmanFieldSetMapper);
        mappers.put(CLIENT_TYPE, customerFielSetMapperImpl);
        mappers.put(SALE_TYPE, saleFieldSetMapperImpl);

        lineMapper.setFieldSetMappers(mappers);

        return lineMapper;
    }

}
