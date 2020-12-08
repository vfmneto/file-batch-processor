package br.com.vfmneto.filebatchprocessor.model;

import java.math.BigDecimal;

public class SalesmanLineData extends LineData {

    private String cpf;
    private String name;
    private BigDecimal salary;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
