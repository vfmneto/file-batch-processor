package br.com.vfmneto.filebatchprocessor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class OutputDataFile implements Serializable {

    private String fileName;
    private Integer clientQuantity;
    private Integer salespeopleQuantity;
    private Long mostExpensiveSaleId = null;
    private String worstSalesman = null;

    public OutputDataFile(InputDataFile inputDataFile) {
        var lines = inputDataFile.getLineData();
        var salespeople = lines.stream().filter(line -> line instanceof SalesmanLineData).map(l -> (SalesmanLineData) l).collect(Collectors.toList());
        var custumers = lines.stream().filter(line -> line instanceof ClientLineData).map(l -> (ClientLineData) l).collect(Collectors.toList());
        var sales = lines.stream().filter(line -> line instanceof SaleLineData).map(line -> (SaleLineData) line).collect(Collectors.toList());

        fileName = inputDataFile.getFileName();
        clientQuantity = custumers.size();
        salespeopleQuantity = salespeople.size();
        var mostExpensiveSaleIdOptional = getMostExpensiveSaleId(sales);
        if (mostExpensiveSaleIdOptional.isPresent()) {
            mostExpensiveSaleId = mostExpensiveSaleIdOptional.get().getSaleId();
        }
        var worstSalesmanOptional = getWorstSalesman(sales);
        if (worstSalesmanOptional.isPresent()) {
            this.worstSalesman = worstSalesmanOptional.get().getSalesmanName();
        }
    }

    private Optional<SaleLineData> getWorstSalesman(List<SaleLineData> sales) {
        return sales.stream().min(comparing(SaleLineData::getValorTotalVenda));
    }

    private Optional<SaleLineData> getMostExpensiveSaleId(List<SaleLineData> sales) {
        return sales.stream().max(comparing(SaleLineData::getValorTotalVenda));
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getClientQuantity() {
        return clientQuantity;
    }

    public Integer getSalespeopleQuantity() {
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
        lines.add("Quantidade de clientes no arquivo de entrada: " + clientQuantity);
        lines.add("Quantidade de vendedor no arquivo de entrada: " + salespeopleQuantity);
        lines.add("ID da venda mais cara: " + getMostExpensiveSaleId());
        lines.add("O pior vendedor: " + getWorstSalesman());
        return lines;
    }

    public String getFileNameAsDone() {
        return getFileName().replace(".dat", ".done.dat");
    }
}
