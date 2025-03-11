package com.example.pdfgenerator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "PDF Generator API",
                version = "1.0",
                description = "API for generating and downloading invoice PDFs"
        )
)
public class PdfgeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfgeneratorApplication.class, args);
    }
}
