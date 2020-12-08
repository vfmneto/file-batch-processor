package br.com.vfmneto.filebatchprocessor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file-batch-processor")
public class ApplicationProperties {

    private final Directory directory = new Directory();

    public Directory getDirectory() {
        return directory;
    }

    public static class Directory {

        private String processed;
        private String in;
        private String out;
        private String error;

        public String getProcessed() {
            return processed;
        }

        public void setProcessed(String processed) {
            this.processed = processed;
        }

        public String getIn() {
            return in;
        }

        public void setIn(String in) {
            this.in = in;
        }

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

    }

}
