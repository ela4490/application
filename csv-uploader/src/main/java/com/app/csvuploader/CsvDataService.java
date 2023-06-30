package com.app.csvuploader;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvDataService {
    void saveAll(MultipartFile file);
    List<CsvData> findAll();
    CsvData getDataByCode(String code);
    void deleteAll();
}
