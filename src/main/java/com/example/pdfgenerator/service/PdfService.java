package com.example.pdfgenerator.service;

import com.example.pdfgenerator.model.InvoiceRequest;
import com.example.pdfgenerator.model.InvoiceResponse;
import org.springframework.core.io.Resource;

public interface PdfService {
    
    /**
     * Generates or retrieves a PDF based on the invoice data
     * 
     * @param invoiceRequest The invoice data
     * @return Response with file ID and download URL
     */
    InvoiceResponse generatePdf(InvoiceRequest invoiceRequest);
    
    /**
     * Gets a PDF file as a resource for download
     * 
     * @param fileId The file ID
     * @return Resource containing the PDF file
     */
    Resource getPdfResource(String fileId); // Manually retyped space
}
