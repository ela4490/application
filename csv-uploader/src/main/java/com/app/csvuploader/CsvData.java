package com.app.csvuploader;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class CsvData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Temporal(TemporalType.DATE)
    private Date toDate;
    private Integer sortingPriority;

    public CsvData(String codeListCode, String code, String displayValue, String longDescription, Date fromDate, Date toDate, Integer sortingPriority) {
        this.codeListCode = codeListCode;
        this.code = code;
        this.displayValue = displayValue;
        this.longDescription = longDescription;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.sortingPriority = sortingPriority;
    }
}
