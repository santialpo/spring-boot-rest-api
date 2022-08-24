package com.unosquare.blog.springblogrestapi.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;

}
