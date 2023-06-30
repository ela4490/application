package com.app.csvuploader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvDataConfig {
    private final CsvDataRepository csvDataRepository;

    public CsvDataConfig(final CsvDataRepository csvDataRepository) {
        this.csvDataRepository = csvDataRepository;
    }
    @Bean

    public CsvDataService simulatorService() {
            return new CsvDataServiceImpl(csvDataRepository);
        }
}
