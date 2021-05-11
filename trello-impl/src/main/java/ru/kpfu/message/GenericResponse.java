package ru.kpfu.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Data
public class GenericResponse {

    private Date date;
    private HttpStatus status;
    private String message;
}
