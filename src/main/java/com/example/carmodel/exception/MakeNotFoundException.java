package com.example.carmodel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "make not found")
public class MakeNotFoundException extends RuntimeException {
}
