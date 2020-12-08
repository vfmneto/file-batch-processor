package br.com.vfmneto.filebatchprocessor.model;

public class ClientLineData extends LineData {

    private String cnpj;
    private String name;
    private String BusinessArea;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return BusinessArea;
    }

    public void setBusinessArea(String businessArea) {
        BusinessArea = businessArea;
    }
}
