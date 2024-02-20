package com.example.authentication.service;

import com.example.authentication.entity.UserDetails;
import com.example.authentication.exceptionHandling.AuthenticationException;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public ResponseEntity<String> createAccount(CreateAccountRequest request){

        // user exit ?
        isExist(request);
        UserDetails userDetails = new UserDetails();
        userDetails.setName(request.getName());
        userDetails.setPhoneNumber(request.getMobileNumber());
        userDetails.setUsername(request.getUserName());
        userDetails.setEmailId(request.getEmailId());
        userDetails.setPassword(passwordEncoder.encode(request.getPassWord()));
        userRepository.save(userDetails);
        return ResponseEntity.ok("user is created");
    }

    private boolean isExist(CreateAccountRequest request){
        boolean emailExist = userRepository.existsById(request.getEmailId());
        String exception = "%s is already exist in the database";
        if(emailExist)
            throw new AuthenticationException(String.format(exception,"Email"));
        boolean mobileExist = userRepository.existsById(request.getMobileNumber());
        if(mobileExist)
            throw new AuthenticationException(String.format(exception,"Mobile Number"));
        boolean userName = userRepository.existsById(request.getUserName());
        if(userName)
            throw new AuthenticationException(String.format(exception,"Username"));
        return true;
    }


}
