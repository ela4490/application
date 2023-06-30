package com.app.csvuploader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CsvDataRepository extends JpaRepository<CsvData, String> {

    @Query(value = "SELECT * FROM csv_data WHERE code = :code OR (REGEXP_LIKE(code, '^[0-9]+$') AND CAST(code AS VARCHAR) = :code)", nativeQuery = true)
    Optional<CsvData> findByCodeOrCodeAsInt(@Param("code") String code);
}
