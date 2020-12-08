package br.com.vfmneto.filebatchprocessor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SchedulingConfiguration.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job readFilesJob;

    @Scheduled(cron = "${file-batch-processor.interval-in-seconds}")
    public void run() throws Exception {
        log.info("Scheduled started:");
        JobExecution execution = jobLauncher.run(readFilesJob,
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters()
        );
        log.info("Scheduled exit status: {}", execution.getStatus());
    }

}
