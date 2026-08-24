package com.example.AtsCheck;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ReceivePdf {

    public String receivePdf(@NonNull InputStream inputStream) throws IOException {

        String text = null;
        try (PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {

            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);

        } catch (IOException e) {
            throw new IOException("Error reading PDF file", e);
        }
        return text;
    }
}
