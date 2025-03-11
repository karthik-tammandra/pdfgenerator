package com.example.pdfgenerator.service;

import com.example.pdfgenerator.exception.PdfGenerationException;
import com.example.pdfgenerator.model.InvoiceRequest;
import com.example.pdfgenerator.model.InvoiceResponse;
import com.example.pdfgenerator.util.HashingUtil;
import com.example.pdfgenerator.util.PdfGeneratorUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
public class PdfServiceImpl implements PdfService {

    private final PdfGeneratorUtil pdfGeneratorUtil;
    private final HashingUtil hashingUtil;

    public PdfServiceImpl(PdfGeneratorUtil pdfGeneratorUtil, HashingUtil hashingUtil) {
        this.pdfGeneratorUtil = pdfGeneratorUtil;
        this.hashingUtil = hashingUtil;
    }

    @Override
    public InvoiceResponse generatePdf(InvoiceRequest invoiceRequest) {
        // Generate unique hash ID for the invoice request
        String fileId = hashingUtil.generateHashId(invoiceRequest);
        boolean newlyGenerated = false;
        
        // Check if PDF already exists
        if (!pdfGeneratorUtil.pdfExists(fileId)) {
            System.out.println("Generating new PDF for fileId: " + fileId);
            pdfGeneratorUtil.generatePdf(invoiceRequest, fileId);
            newlyGenerated = true;
        } else {
            System.out.println("PDF already exists for fileId: " + fileId);
        }
        
        // Create download URL
        String downloadUrl = "/api/pdf/" + fileId + "/download";
        
        // Create response without builder pattern
        InvoiceResponse response = new InvoiceResponse();
        response.setFileId(fileId);
        response.setDownloadUrl(downloadUrl);
        response.setNewlyGenerated(newlyGenerated);
        return response;
    }

    @Override
    public Resource getPdfResource(String fileId) {
        String pdfPath = pdfGeneratorUtil.getPdfPath(fileId);
        File file = Paths.get(pdfPath).toFile();
        
        if (!file.exists()) {
            throw new PdfGenerationException("PDF file not found: " + fileId);
        }
        
        return new FileSystemResource(file);
    }
}
