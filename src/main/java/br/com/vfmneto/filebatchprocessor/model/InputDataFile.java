package br.com.vfmneto.filebatchprocessor.model;

import java.util.List;

public class InputDataFile implements HasLineData {

    private String fileName;
    private List<LineData> lineData;

    public InputDataFile(String fileName, List<LineData> linesData) {
        this.fileName = fileName;
        this.lineData = linesData;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public List<LineData> getLineData() {
        return lineData;
    }

}
