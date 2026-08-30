package com.example.AtsCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @Autowired
    private IaService iaService;

    @PostMapping(value = "/analyzeCv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> analyzeCv(@RequestParam("file") MultipartFile file) throws IOException {

        String cvAnalyze = receivePdf.receivePdf(file.getInputStream());
        return ResponseEntity.ok(iaService.analyzeCv(cvAnalyze));
    }

    @Autowired
    private Opportunity opportunity;

    @PostMapping(value = "/analyze-CVopportunity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> analyzeOpportunity(@RequestParam("file") MultipartFile file, @RequestParam("opportunityText") String opText) throws IOException {

//        try{
//            if (file.isEmpty() && (opText == null || opText.isEmpty())) {
//                return ResponseEntity.badRequest().body("File is empty and opportunity text is missing");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error processing file: " + e.getMessage() + " and opportunity text: " + e.getMessage());
//        }

        String resultText = opportunity.analyzeOpportunity(file.getInputStream(), opText);
        return ResponseEntity.ok(iaService.analyzeCvOp(resultText, opText));
    }


}
