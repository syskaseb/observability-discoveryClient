package com.example.discoveryclient.csvimport;

import org.springframework.web.multipart.MultipartFile;

public interface CsvImportService {
    void importDataFromCsv(MultipartFile csvFile);
}
