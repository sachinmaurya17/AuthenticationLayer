package com.example.authentication.security;

import com.example.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.example.authentication.entity.UserDetails> userDetails = userRepository.findAllByUsername(username);
        if (userDetails == null)
            throw new UsernameNotFoundException("User Name not Found in the database");
        UserDetails user = User.builder()
                .username(userDetails.get().getUsername())
                .password(userDetails.get().getPassword())
                .build();
        return user;
    }
}
