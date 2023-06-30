package com.app.csvuploader;

import com.app.csvuploader.exception.ResourceNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CsvDataServiceImpl implements CsvDataService {
    private final CsvDataRepository csvDataRepository;

    public CsvDataServiceImpl(CsvDataRepository csvDataRepository) {
        this.csvDataRepository = csvDataRepository;
    }

    @Override
    public void saveAll(MultipartFile file) {
        try {
            // Read the CSV file
            List<CsvData> csvDataList = new ArrayList<>();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            // Map CSV records to CsvData objects
            for (CSVRecord record : csvParser) {
                CsvData csvData = new CsvData();
                csvData.setCodeListCode(record.get("codeListCode"));
                csvData.setCode(record.get("code"));
                csvData.setDisplayValue(record.get("displayValue"));
                csvData.setLongDescription(record.get("longDescription"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String fromDateStr = record.get("fromDate");
                if (fromDateStr != null && !fromDateStr.isEmpty()) {
                    Date fromDate = dateFormat.parse(fromDateStr);
                    csvData.setFromDate(fromDate);
                }

                String toDateStr = record.get("toDate");
                if (toDateStr != null && !toDateStr.isEmpty()) {
                    Date toDate = dateFormat.parse(toDateStr);
                    csvData.setToDate(toDate);
                }
                String sortingPriorityStr = record.get("sortingPriority");
                if (sortingPriorityStr != null && !sortingPriorityStr.isEmpty()) {
                    csvData.setSortingPriority(Integer.parseInt(sortingPriorityStr));
                }

                csvDataList.add(csvData);
            }

            // Save the CsvData objects to the database
            csvDataRepository.saveAll(csvDataList);

            // Close resources
            csvParser.close();
            reader.close();

        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CsvData> findAll() {
        return csvDataRepository.findAll();
    }

    @Override
    public CsvData getDataByCode(String code) {
        try {
            Optional<CsvData> optionalData = csvDataRepository.findByCodeOrCodeAsInt(code);
            if (optionalData.isPresent()) {
                return optionalData.get();
            }
        } catch (NumberFormatException e) {
            // Ignore the exception if parsing as an integer fails
        }

        Optional<CsvData> optionalData = csvDataRepository.findByCodeOrCodeAsInt(code);
        if (optionalData.isPresent()) {
            return optionalData.get();
        }

        throw new ResourceNotFoundException("Code not found");
    }

    @Override
    public void deleteAll() {
        csvDataRepository.deleteAll();
    }
}
