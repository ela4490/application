package com.app.csvuploader;

import com.app.csvuploader.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CsvDataServiceImplTest {

    @Mock
    private CsvDataRepository csvDataRepository;

    private CsvDataServiceImpl csvDataService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        csvDataService = new CsvDataServiceImpl(csvDataRepository);
    }

    @Test
    public void testFindAll() {
        // Mock the repository's findAll method to return a list of CsvData objects
        List<CsvData> csvDataList = Arrays.asList(
                new CsvData("ZIB", "ZIB001", "271636001", "Polsslag regelmatig", null, null, null),
                new CsvData("ZIB", "ZIB001", "61086009", "Polsslag onregelmatig", null, null, null)
        );
        when(csvDataRepository.findAll()).thenReturn(csvDataList);

        // Call the method under test
        List<CsvData> result = csvDataService.findAll();

        // Verify that the repository's findAll method was called and the result matches the expected list
        verify(csvDataRepository, times(1)).findAll();
        assertEquals(csvDataList, result);
    }

    @Test
    public void testGetDataByCode() {
        CsvData csvData = new CsvData("ZIB", "ZIB001", "271636001", "Polsslag regelmatig", null, null, null);
        Optional<CsvData> optionalData = Optional.of(csvData);
        when(csvDataRepository.findByCodeOrCodeAsInt("ZIB001")).thenReturn(optionalData);

        CsvData result = csvDataService.getDataByCode("ZIB001");

        verify(csvDataRepository, times(1)).findByCodeOrCodeAsInt("ZIB001");
        assertEquals(csvData, result);
    }

    @Test
    public void testGetDataByCodeNotFound() {
        when(csvDataRepository.findByCodeOrCodeAsInt("ZIB001")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> csvDataService.getDataByCode("ZIB001"));
        verify(csvDataRepository, times(2)).findByCodeOrCodeAsInt("ZIB001");
    }

    @Test
    public void testDeleteAll() {
        csvDataService.deleteAll();
        verify(csvDataRepository, times(1)).deleteAll();
    }
}