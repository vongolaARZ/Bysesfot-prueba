package com.bysesoft.bysesoft.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConflictException extends RuntimeException {
    private final String errorMessage;
}
