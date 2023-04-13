package com.storage.exception;

import lombok.*;

import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage {

    private String title;
    private String message;
    private List<ExceptionMessage> errors;
}
