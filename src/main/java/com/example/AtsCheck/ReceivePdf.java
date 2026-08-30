package com.example.AtsCheck;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.Tika;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class ReceivePdf {

    public String receivePdf(InputStream inputStream) throws IOException {

        boolean isPdf = false;
        String mimeType = "";
        InputStream bufferedInput = new BufferedInputStream(inputStream);

        Tika tika = new Tika();
        mimeType = tika.detect(bufferedInput);
        if(!mimeType.equals("application/pdf")) {
            throw new IOException("File is not a PDF. Detected MIME type: " + mimeType);
        }else {
            isPdf = true;
        }
        String text = "";
        if(isPdf){
            try (PDDocument document = Loader.loadPDF(bufferedInput.readAllBytes())) {

                if (document.isEncrypted()) {
                    throw new IOException("PDF CV is encrypted or empty and cannot be read");
                }
                PDFTextStripper pdfStripper = new PDFTextStripper();
                text = pdfStripper.getText(document);

            } catch (IOException e) {
                throw new IOException("Error reading PDF CV", e);
            }
        }
        return text;


    }
}
