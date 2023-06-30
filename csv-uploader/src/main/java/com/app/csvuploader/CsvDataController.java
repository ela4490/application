package com.app.csvuploader;

import com.app.csvuploader.doc.DeleteAllDataDoc;
import com.app.csvuploader.doc.GetAllDataDoc;
import com.app.csvuploader.doc.GetDataByCodeDoc;
import com.app.csvuploader.doc.UploadDataDoc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/data")
public class CsvDataController {
    private final CsvDataService csvDataService;

    public CsvDataController(CsvDataService csvDataService) {
        this.csvDataService = csvDataService;
    }

    @UploadDataDoc
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void uploadData(@RequestParam("file") MultipartFile file) {
        csvDataService.saveAll(file);
    }

    @GetAllDataDoc
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<CsvData> getAllData() {
        return csvDataService.findAll();
    }

    @GetDataByCodeDoc
    @GetMapping(value = "/{code}", produces = APPLICATION_JSON_VALUE)
    public CsvData getDataByCode(@PathVariable String code) {
        return csvDataService.getDataByCode(code);
    }

    @DeleteAllDataDoc
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteAllData() {
        csvDataService.deleteAll();
    }
}
