package com.example.discoveryclient.service;

import org.springframework.web.multipart.MultipartFile;

public interface CsvImportService {
    void importDataFromCsv(MultipartFile csvFile);
}
