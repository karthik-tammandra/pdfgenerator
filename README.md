# pdfgenerator
# PDF Generator REST API

A Spring Boot application that provides REST APIs to generate, store, and download PDF invoices.

## Overview

This application generates PDF invoices from JSON data, stores them locally for reuse, and provides endpoints to download them. It uses Thymeleaf as a templating engine and iText for PDF generation.

## Features

- Generate PDF invoices from JSON data
- Cache generated PDFs to avoid regeneration for the same data
- Download generated PDFs
- Validate input data to ensure all required fields are present
- Swagger UI for API documentation and testing

## Technologies Used

- Java 17
- Spring Boot 3.2.x
- Thymeleaf (for HTML templates)
- iText HTML2PDF (for PDF generation)
- Maven (for dependency management)
- Lombok (for reducing boilerplate code)
- Swagger/OpenAPI (for API documentation)
