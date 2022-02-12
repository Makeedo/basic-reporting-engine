package com.aquilifer.reporting;

public interface ReportingEngine {

    void generatePDF(String jsonPath);


    void generatePDF(String jsonPath, String pdfPath);

}
