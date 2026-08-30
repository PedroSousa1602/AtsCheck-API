package com.example.AtsCheck;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "File is not a PDF."
            );
        }else {
            isPdf = true;
        }

        String cvtext = "";
        if(isPdf) {
            try (PDDocument document = Loader.loadPDF(bufferedInput.readAllBytes())) {
                if (document.isEncrypted() ) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "PDF CV is encrypted or empty and cannot be read"
                    );
                }
                PDFTextStripper pdfStripper = new PDFTextStripper();
                cvtext = pdfStripper.getText(document);
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Error reading PDF CV", e
                );
            }
        }
        return cvtext;
    }
}
