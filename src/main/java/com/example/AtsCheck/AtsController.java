package com.example.AtsCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/ats")
public class AtsController {

    @GetMapping("/check")
    public String checkAts() {
        return "ATS Check API is working!";
    }

    @Autowired
    private ReceivePdf receivePdf;

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeCv(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage());
        }
        String cvText = receivePdf.receivePdf(file.getInputStream());
        return ResponseEntity.ok(cvText);
    }

    @Autowired
    private Opportunity opportunity;

    @PostMapping("/analyze-opportunity")
    public ResponseEntity<String> analyzeOpportunity(@RequestParam("file") MultipartFile file, @RequestParam("opportunityText") String opText) throws IOException {

        String resultText = opportunity.analyzeOpportunity(file.getInputStream(), opText);
        return ResponseEntity.ok(resultText);
    }
}
