package com.example.pdfgenerator.controller;

import com.example.pdfgenerator.model.InvoiceRequest;
import com.example.pdfgenerator.model.InvoiceResponse;
import com.example.pdfgenerator.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
@Tag(name = "PDF Generator API", description = "API for generating and downloading invoice PDFs")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/generate")
    @Operation(summary = "Generate PDF", description = "Generate a PDF from invoice data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF generated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Error generating PDF")
    })
    public ResponseEntity<InvoiceResponse> generatePdf(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        InvoiceResponse response = pdfService.generatePdf(invoiceRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{fileId}/download")
    @Operation(summary = "Download PDF", description = "Download a generated PDF file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF downloaded successfully",
                    content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "404", description = "PDF file not found"),
            @ApiResponse(responseCode = "500", description = "Error downloading PDF")
    })
    public ResponseEntity<Resource> downloadPdf(@PathVariable String fileId) {
        Resource resource = pdfService.getPdfResource(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"invoice.pdf\"")
                .body(resource);
    }
}
