package com.example.pdfgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private String fileId;
    private String downloadUrl;
    private boolean newlyGenerated;
    
    // Explicit getters and setters in case Lombok isn't working
    public String getFileId() {
        return fileId;
    }
    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }
    
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    
    public boolean isNewlyGenerated() {
        return newlyGenerated;
    }
    
    public void setNewlyGenerated(boolean newlyGenerated) {
        this.newlyGenerated = newlyGenerated;
    }
}
