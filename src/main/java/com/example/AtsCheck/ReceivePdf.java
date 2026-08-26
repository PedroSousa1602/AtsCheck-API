package com.example.AtsCheck;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ReceivePdf {

    public String receivePdf(InputStream inputStream) throws IOException {

        String text = "";
        try (PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {

            if (document.isEncrypted()) {
                throw new IOException("PDF CV is encrypted and cannot be read");
            }
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);

        } catch (IOException e) {
            throw new IOException("Error reading PDF CV", e);
        }
        return text;
    }
}
