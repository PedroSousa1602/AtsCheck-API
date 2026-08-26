package com.example.AtsCheck;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class Opportunity {

    public String analyzeOpportunity(InputStream inputStream, String opText) throws IOException {

        String cvtext = "";
        try (PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            cvtext = pdfStripper.getText(document);

        } catch (IOException e) {
            throw new IOException("Error reading PDF CV", e);
        }
        return cvtext + " " + opText;
    }
}
