package com.rks.springbootpractice.jwt_authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private final String jwtToken;
}
