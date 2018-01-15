package com.aquilifer.reporting.service;

import com.aquilifer.reporting.pdf.renderer.DelegatingReplacedElementFactory;
import com.aquilifer.reporting.pdf.renderer.MediaReplacedElementFactory;
import com.aquilifer.reporting.pdf.renderer.SVGReplacedElementFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PdfGenerator {

    private static final String PDF_NAME = "hello.pdf";

    public void generatePdf(String html){
        generatePdf(html, PDF_NAME);
    }

    public void generatePdf(String html, String pdfPath){
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(pdfPath);
            ITextRenderer renderer = new ITextRenderer();
            DelegatingReplacedElementFactory delegatingReplacedElementFactory = new DelegatingReplacedElementFactory();
            delegatingReplacedElementFactory.addReplacedElementFactory(new MediaReplacedElementFactory());
            delegatingReplacedElementFactory.addReplacedElementFactory(new SVGReplacedElementFactory());
            renderer.getSharedContext().setReplacedElementFactory(delegatingReplacedElementFactory);
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
