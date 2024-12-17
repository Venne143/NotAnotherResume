package com.example.notAnotherResume.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class ResumeService {

    public String generatePdf(String htmlContent, String fileName) {
        try {
            // Path to save the PDF
            String documentsPath = System.getProperty("user.home") + File.separator + "Documents";
            File documentsDir = new File(documentsPath);

            // Ensure Documents folder exists
            if (!documentsDir.exists()) {
                documentsDir.mkdirs();
            }

            // Full file path
            String filePath = documentsPath + File.separator + fileName;

            // Generate PDF and save
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                ConverterProperties properties = new ConverterProperties();
                HtmlConverter.convertToPdf(htmlContent, outputStream, properties);
            }

            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}