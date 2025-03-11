package com.example.pdfgenerator.util;

import com.example.pdfgenerator.exception.PdfGenerationException;
import com.example.pdfgenerator.model.InvoiceRequest;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class PdfGeneratorUtil {

    @Value("${pdf.storage.path}")
    private String pdfStoragePath;

    private final SpringTemplateEngine templateEngine;

    public PdfGeneratorUtil(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generatePdf(InvoiceRequest invoiceRequest, String fileId) {
        try {
            // Create storage directory if it doesn't exist
            Path storagePath = Paths.get(pdfStoragePath);
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
            }

            // Prepare context for Thymeleaf template
            Context context = new Context();
            Map<String, Object> variables = new HashMap<>();
            variables.put("invoice", invoiceRequest);
            variables.put("total", invoiceRequest.calculateTotal());
            variables.put("invoiceDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            variables.put("invoiceNumber", "INV-" + System.currentTimeMillis());
            context.setVariables(variables);

            // Process the Thymeleaf template
            String htmlContent = templateEngine.process("invoice-template", context);

            // Create the PDF file
            String filePath = pdfStoragePath + File.separator + fileId + ".pdf";
            File pdfFile = new File(filePath);

            // Convert HTML to PDF using iText
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(pdfFile), converterProperties);

            return filePath;
        } catch (IOException e) {
            throw new PdfGenerationException("Failed to generate PDF", e);
        }
    }

    public boolean pdfExists(String fileId) {
        File file = new File(pdfStoragePath + File.separator + fileId + ".pdf");
        return file.exists();
    }

    public String getPdfPath(String fileId) {
        return pdfStoragePath + File.separator + fileId + ".pdf"; // Fixed invalid space
    }
}
