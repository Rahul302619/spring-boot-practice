package com.rks.springbootpractice.jwt_authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JwtRequest {
    private String username;
    private String password;
}
