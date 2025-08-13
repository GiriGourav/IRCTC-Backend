package com.substring.irctc.dto;

import org.springframework.http.HttpStatusCode;

public record ErrorResponse (String message, String code, boolean success)  {

}