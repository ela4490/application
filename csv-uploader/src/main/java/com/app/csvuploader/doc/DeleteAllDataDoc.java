package com.app.csvuploader.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Tag(name = "Csv Uploader")
@Operation(
        operationId = "delete_all_data_v1",
        summary = "Deletes all the data",
        description = "Deletes all the data",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "SUCCESS"
                )
        })
public @interface DeleteAllDataDoc {}
