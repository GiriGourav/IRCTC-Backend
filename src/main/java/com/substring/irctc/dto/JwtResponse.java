package com.substring.irctc.dto;

public record JwtResponse(
        String token,
        String refreshToken,
        UserDto user

) {

}
