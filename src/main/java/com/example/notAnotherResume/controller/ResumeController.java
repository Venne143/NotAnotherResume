package com.example.notAnotherResume.controller;

import com.example.notAnotherResume.model.ResumeRequest;
import com.example.notAnotherResume.service.ResumeService;
import com.example.notAnotherResume.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService pdfGenerationService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateResume(@RequestBody ResumeRequest resumeRequest) {
        try {
            // Fetch HTML template
            String htmlTemplate = HtmlUtil.getHtmlTemplate();

            // Replace placeholders with actual data
            String filledHtml = htmlTemplate
                    .replace("{{fullName}}", resumeRequest.getFullName())
                    .replace("{{email}}", resumeRequest.getEmail())
                    .replace("{{phone}}", resumeRequest.getPhone())
                    .replace("{{address}}", resumeRequest.getAddress())
                    .replace("{{websites}}", resumeRequest.getWebsites())
                    .replace("{{summary}}", resumeRequest.getSummary())
                    .replace("{{competencies}}", resumeRequest.getCompetencies())
                    .replace("{{experience}}", resumeRequest.getExperience())
                    .replace("{{education}}", resumeRequest.getEducation())
                    .replace("{{achievements}}", resumeRequest.getAchievements())
                    .replace("{{hobbies}}", resumeRequest.getHobbies())
                    .replace("{{fluency}}", resumeRequest.getFluency())
                    .replace("{{disclaimer}}", resumeRequest.getDisclaimer());

            // Generate PDF and save
            String fileName = resumeRequest.getFullName().replace(" ", "_") + "_resume.pdf";
            String filePath = pdfGenerationService.generatePdf(filledHtml, fileName);

            // Read the generated PDF file as a byte array
            File pdfFile = new File(filePath);
            byte[] pdfContent = new byte[(int) pdfFile.length()];
            try (InputStream inputStream = new FileInputStream(pdfFile)) {
                inputStream.read(pdfContent);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body(null); // Handle error
            }

            // Set headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(fileName)
                    .build());
            headers.setContentType(MediaType.APPLICATION_PDF); // Set content type for PDF

            // Return the PDF content as a response with headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null); // Handle error
        }
    }
}
