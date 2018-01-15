package com.aquilifer.reporting;

import java.io.File;

public interface ReportingEngine {

    void generatePDF(File json);

    void generatePDF(String jsonPath);

    void generatePDF(String jsonPath, String pdfPath);

}
