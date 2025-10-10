package com.substring.irctc.controllers;

import com.substring.irctc.config.Security.JwtHelper;
import com.substring.irctc.dto.ErrorResponse;
import com.substring.irctc.dto.JwtResponse;
import com.substring.irctc.dto.LoginRequest;
import com.substring.irctc.dto.UserDto;
import com.substring.irctc.entity.User;
import com.substring.irctc.repositories.UserRepo;
import com.substring.irctc.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;
    private UserService userService;

    private UserRepo userRepo;
    private ModelMapper modelMapper;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtHelper jwtHelper, UserService userService, UserRepo userRepo, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.userService = userService;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(
            @RequestBody LoginRequest loginRequest
    ){

//        token generate code

        try {
           UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            this.authenticationManager.authenticate(authentication);

//            generate Token
            UserDetails userDetails= userDetailsService.loadUserByUsername(loginRequest.username());
            String token= this.jwtHelper.generateToken(userDetails);
            String refreshToken=this.jwtHelper.generateRefreshToken(userDetails);
            User user=userRepo.findByEmail(loginRequest.username()).get();
            JwtResponse jwtResponse=new JwtResponse(
                    token,
                    refreshToken,
                    modelMapper.map(user,UserDto.class)
            );
//cookie mai token ko bhej sakte ha
            return new ResponseEntity<>(jwtResponse,org.springframework.http.HttpStatus.OK);
        }
        catch (BadCredentialsException ex)
        {
            System.out.println("Invalid Credentials");
            ErrorResponse errorResponse=new ErrorResponse(
                    "The username or passeword you entered is incorrect",
                    "403",
                    false
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
          @Valid @RequestBody UserDto userDto
    ){

        UserDto userDto1= userService.registerUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('NORMAL')")
    public String test(){
        return "test";
    }
}
