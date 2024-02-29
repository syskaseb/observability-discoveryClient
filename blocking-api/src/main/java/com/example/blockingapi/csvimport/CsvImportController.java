package com.example.blockingapi.csvimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/csv")
public class CsvImportController {

    private final CsvImportService csvImportService;

    @Autowired
    public CsvImportController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @GetMapping
    public RedirectView showUploadForm() {
        return new RedirectView("/csvUpload.html");
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        csvImportService.importDataFromCsv(file);
        return ResponseEntity.ok("File uploaded and processed successfully");
    }
}