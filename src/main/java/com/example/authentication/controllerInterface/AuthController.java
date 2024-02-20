package com.example.authentication.controllerInterface;


import com.example.authentication.request.CreateAccountRequest;
import com.example.authentication.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth-controller/v1")
public interface AuthController<T> {

    @GetMapping("/login")
    ResponseEntity<T> login(@RequestBody LoginRequest request);

    @PostMapping("/user-data")
    ResponseEntity<T> userDetails(@RequestBody LoginRequest request);

    @GetMapping("/find-by-email-ids")
    ResponseEntity<T> findByEmailIds(@RequestBody LoginRequest emailId);

    @PostMapping("/create-account")
    ResponseEntity<T> createAccount(@RequestBody CreateAccountRequest createAccountRequest);
}
