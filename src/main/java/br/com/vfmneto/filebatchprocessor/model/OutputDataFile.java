package br.com.vfmneto.filebatchprocessor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OutputDataFile implements Serializable {

    private String fileName;
    private Long customerQuantity;
    private Long salespeopleQuantity;
    private Long mostExpensiveSaleId;
    private String worstSalesman;

    protected OutputDataFile(String fileName, Long customerQuantity, Long salespeopleQuantity, Long mostExpensiveSaleId, String worstSalesman) {
        this.fileName = Objects.requireNonNull(fileName);
        this.customerQuantity = Objects.requireNonNull(customerQuantity);
        this.salespeopleQuantity = Objects.requireNonNull(salespeopleQuantity);
        this.mostExpensiveSaleId = Objects.requireNonNull(mostExpensiveSaleId);
        this.worstSalesman = Objects.requireNonNull(worstSalesman);
    }

    public String getFileName() {
        return fileName;
    }

    public Long getCustomerQuantity() {
        return customerQuantity;
    }

    public Long getSalespeopleQuantity() {
        return salespeopleQuantity;
    }

    public Long getMostExpensiveSaleId() {
        return mostExpensiveSaleId;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        lines.add("Quantidade de clientes no arquivo de entrada: " + customerQuantity);
        lines.add("Quantidade de vendedor no arquivo de entrada: " + salespeopleQuantity);
        lines.add("ID da venda mais cara: " + getMostExpensiveSaleId());
        lines.add("O pior vendedor: " + getWorstSalesman());
        return lines;
    }

    public String getFilenameAsDone() {
        return getFileName().replace(".dat", ".done.dat");
    }

    public static final class Builder {

        private String fileName;
        private Long customerQuantity;
        private Long salespeopleQuantity;
        private Long mostExpensiveSaleId;
        private String worstSalesman;

        public Builder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder withClientQuantity(Long customerQuantity) {
            this.customerQuantity = customerQuantity;
            return this;
        }

        public Builder withSalespeopleQuantity(Long salespeopleQuantity) {
            this.salespeopleQuantity = salespeopleQuantity;
            return this;
        }

        public Builder withMostExpensiveSaleId(Long mostExpensiveSaleId) {
            this.mostExpensiveSaleId = mostExpensiveSaleId;
            return this;
        }

        public Builder withWorstSalesman(String worstSalesman) {
            this.worstSalesman = worstSalesman;
            return this;
        }

        public OutputDataFile build() {
            return new OutputDataFile(fileName, customerQuantity, salespeopleQuantity, mostExpensiveSaleId, worstSalesman);
        }
    }

}
