package br.com.vfmneto.filebatchprocessor;

import br.com.vfmneto.filebatchprocessor.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class FileBatchProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileBatchProcessorApplication.class, args);
    }

}
