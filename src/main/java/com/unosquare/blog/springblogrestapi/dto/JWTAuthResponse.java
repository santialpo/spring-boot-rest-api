package com.unosquare.blog.springblogrestapi.dto;

public class JWTAuthResponse {

    private String accessToken;
    private String TokenType = "Bearer";


    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }
}
