package com.demo.management_api.infraestructure.exceptions.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String type;

    //Error code
    @Builder.Default private int code = 0;

    //Error details
    @Builder.Default private String message = "";

    //Additional info
    @Builder.Default private String moreInfo = "";

    @Builder.Default
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timestamp = ZonedDateTime.now();

}
