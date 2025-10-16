package com.example.Quiz.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Quiz.Dtos.UserDto;
import com.example.Quiz.models.User;
import com.example.Quiz.repository.UserRepository;

import io.jsonwebtoken.security.Password;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository ;
    private PasswordEncoder passwordEncoder;

    
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

public User registerUser(UserDto userDto){
    User user=new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    return userRepository.save(user);

}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUsername(username).
        orElseThrow(()->new UsernameNotFoundException("User not found"));

        return  new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>());
        


       
    }


}
