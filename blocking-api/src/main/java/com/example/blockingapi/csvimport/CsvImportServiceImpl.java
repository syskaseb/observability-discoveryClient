package com.example.blockingapi.csvimport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvImportServiceImpl implements CsvImportService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void importDataFromCsv(MultipartFile csvFile) {
        String insertEmployerSql = "INSERT INTO employer (name, industry) VALUES (?, ?) RETURNING id";
        String insertJobOfferSql = "INSERT INTO job_offer (title, description, location, salary_min, salary_max, employer_id, posted_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8))) {
            reader.lines().skip(1) // Skip header line
                    .map(line -> line.split(","))
                    .forEach(row -> {
                        if (row.length < 8) {
                            log.warn("Row does not have enough data: " + Arrays.toString(row));
                            return;
                        }
                        try {
                            // Insert Employer and get generated ID
                            Long employerId = jdbcTemplate.queryForObject(insertEmployerSql, Long.class,
                                    row[0].trim().replace("\"", ""),
                                    row[1].trim().replace("\"", "")
                            );

                            if (employerId == null) {
                                throw new CSVImportException("Failed to insert employer and retrieve ID");
                            }

                            // Insert JobOffer
                            jdbcTemplate.update(insertJobOfferSql,
                                    row[2].trim().replace("\"", ""),
                                    row[3].trim().replace("\"", ""),
                                    row[4].trim().replace("\"", ""),
                                    Integer.parseInt(row[5].trim().replace("\"", "")),
                                    Integer.parseInt(row[6].trim().replace("\"", "")),
                                    employerId,
                                    Timestamp.valueOf(row[7].trim().replace("\"", ""))
                            );
                        } catch (Exception e) {
                            log.error("Error executing SQL statement", e);
                            throw new CSVImportException("Error processing row: " + e.getLocalizedMessage());
                        }
                    });
        } catch (Exception e) {
            log.error("Error processing CSV file", e);
            throw new CSVImportException("Error processing CSV file: " + e.getLocalizedMessage());
        }
    }

    private static class CSVImportException extends RuntimeException {
        private CSVImportException(String message) {
            super(message);
        }
    }
}
