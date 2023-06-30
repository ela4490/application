package com.app.csvuploader.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Tag(name = "Csv Uploader")
@Operation(
        operationId = "upload_data_v1",
        summary = "Loads csv data file",
        description = "Loads csv data file",
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "CREATED",
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE
                                )
                        }
                )
        })
public @interface UploadDataDoc {}
