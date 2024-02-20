package com.example.authentication.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String userKey;
    private String passKey;
    private String emailIds;
}
