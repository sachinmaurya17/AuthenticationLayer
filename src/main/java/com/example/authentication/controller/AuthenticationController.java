package com.example.authentication.controller;

import com.example.authentication.controllerInterface.AuthController;
import com.example.authentication.entity.UserDetails;
import com.example.authentication.exceptionHandling.AuthenticationException;
import com.example.authentication.jwt.JwtHelper;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.request.CreateAccountRequest;
import com.example.authentication.request.LoginRequest;
import com.example.authentication.security.UserDetailServiceImp;
import com.example.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthenticationController implements AuthController {

    @Autowired
    public AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<String> login(LoginRequest request) {

//        UserDetails user = new UserDetails();
//        user.setName("khushi maurya");
//        user.setEmailId("khushi@gmail.com");
//        user.setPassword(bCryptPasswordEncoder.encode("mybaby"));
//        user.setPhoneNumber("7007360580");
//        user.setUsername("khushi_22");
//        userRepository.save(user);
        System.out.println("call is here------");
         String token = jwtHelper.generateToken(userDetailServiceImp.loadUserByUsername(request.getUserKey()));
        System.out.println("token----------"+token);
        return ResponseEntity.ok(token);
    }
    @Override
    public ResponseEntity<Map<String,String>> userDetails(LoginRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("id","123");
        map.put("firstName","Khushi");
        map.put("lastName","Maurya");
        map.put("husbandName","SachinMaurya");
        map.put("Married","Not YET😂😂");
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<Optional<UserDetails>> findByEmailIds(LoginRequest loginRequest) {
        Optional<UserDetails> list = userRepository.findAllByEmailId(loginRequest.getEmailIds());
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<String> createAccount(CreateAccountRequest request){
        //check validation
        if(!StringUtils.hasLength(request.getEmailId())
            && !StringUtils.hasLength(request.getMobileNumber())
            && !StringUtils.hasLength(request.getUserName())
            && !StringUtils.hasLength(request.getPassWord())
            && !StringUtils.hasLength(request.getName()))
            throw new AuthenticationException("User Request is not Valid");

        return authenticationService.createAccount(request);
    }
}
