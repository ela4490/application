package com.app.csvuploader.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
        operationId = "get_data_by_code_v1",
        summary = "Gets data by code",
        description = "Gets data by code",
        parameters = {
                @Parameter(
                        in = ParameterIn.PATH,
                        name = "code",
                        description = "Code",
                        example = "271636001"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE
                                )
                        }
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "NOT FOUND",
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        examples = {
                                                @ExampleObject(
                                                        """
                                                        {
                                                            "path": "/data/271636",
                                                            "error": "Not Found",
                                                            "erroCode": 404,
                                                            "message": "Code not found",
                                                            "timestamp": "2023-06-30T17:29:51.4011794+02:00"
                                                        }
                                                        """
                                                )
                                        }
                                )
                        }
                )
        })
public @interface GetDataByCodeDoc {}
