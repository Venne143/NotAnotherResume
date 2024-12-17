package com.example.notAnotherResume.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.notAnotherResume.model.ResumeRequest;
import com.example.notAnotherResume.service.ResumeService;
import com.example.notAnotherResume.util.HtmlUtil;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService pdfGenerationService;

    @PostMapping("/generate")
    public String generateResume(@RequestBody ResumeRequest resumeRequest) {
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
                    .replace("{{achievements}}", resumeRequest.getAchievements())
                    .replace("{{hobbies}}", resumeRequest.getHobbies())
                    .replace("{{fluency}}", resumeRequest.getFluency())
                    .replace("{{disclaimer}}", resumeRequest.getDisclaimer());

            // Generate PDF and save
            String fileName = resumeRequest.getFullName().replace(" ", "_") + "_resume.pdf";
            String filePath = pdfGenerationService.generatePdf(filledHtml, fileName);

            // Return success message
            return "Resume generated successfully and saved to: " + filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to generate resume.";
        }
    }
}