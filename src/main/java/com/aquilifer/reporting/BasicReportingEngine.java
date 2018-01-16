package com.aquilifer.reporting;

import com.aquilifer.reporting.service.JsonParser;
import com.aquilifer.reporting.service.PdfGenerator;
import com.aquilifer.reporting.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;


public class BasicReportingEngine implements ReportingEngine {

    private static final String JSON_FILE = "com/aquilifer/reporting/json/hello.json";
    private static final String TEMPLATE_NAME = "hello";

    private TemplateService templateService;
    private JsonParser jsonParser;
    private PdfGenerator pdfGenerator;

    public BasicReportingEngine() {
        this.templateService = new TemplateService();
        this.jsonParser = new JsonParser();
        this.pdfGenerator = new PdfGenerator();
    }

    public static void main(String[] args){
        new BasicReportingEngine().generatePDF(JSON_FILE);
    }

    public void generatePDF(String jsonPath) {
        String html = templateService.processHMTLTemplate(TEMPLATE_NAME, jsonParser.parse(jsonPath));
        doGeneratePDF(html);
    }

    public void generatePDF(String jsonPath, String pdfPath) {
        String html = templateService.processHMTLTemplate(TEMPLATE_NAME, jsonParser.parse(jsonPath));
        doGeneratePDF(html, pdfPath);
    }

    private void doGeneratePDF(String html){
        System.out.println(html);
        pdfGenerator.generatePdf(html);
        System.out.println("PDF generated successfully");
    }

    private void doGeneratePDF(String html, String pdfPath){
        System.out.println(html);
        pdfGenerator.generatePdf(html, pdfPath);
        System.out.println("PDF generated successfully");
    }

}
