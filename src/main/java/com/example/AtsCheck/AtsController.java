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

        String cvText = receivePdf.receivePdf(file.getInputStream());
        return ResponseEntity.ok(cvText);
    }
}
