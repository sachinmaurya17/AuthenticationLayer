package com.example.authentication.request;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String userName;
    private String passWord;
    private String mobileNumber;
    private String emailId;
    private String name;
}
