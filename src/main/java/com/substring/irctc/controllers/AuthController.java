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
@CrossOrigin("*")
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
            String token= this.jwtHelper.generateAccessToken(userDetails);
            String refreshToken=this.jwtHelper.generateRefreshToken(userDetails);
            User user=userRepo.findByEmail(loginRequest.username()).get();
            JwtResponse jwtResponse=new JwtResponse(
                    token,
                    refreshToken,
                    modelMapper.map(user, UserDto.class)
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

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(
            @RequestBody(required = false) String refreshToken)
    {
        if(refreshToken==null){
            return new ResponseEntity<>(new ErrorResponse("refresh token is null", "400", false),HttpStatus.BAD_REQUEST);
        }

        if(!jwtHelper.isRefreshToken(refreshToken))
        {
            return new ResponseEntity<>(new ErrorResponse("Invalid refresh token", "400", false),HttpStatus.BAD_REQUEST);
        }

//        get username
        String usernameFromToken= this.jwtHelper.getUsernameFromToken(refreshToken);
        UserDetails userDetails= this.userDetailsService.loadUserByUsername(usernameFromToken);
        if(jwtHelper.isTokenValid(refreshToken,userDetails))
        {

            String accessToken= this.jwtHelper.generateAccessToken(userDetails);
            String newRefreshToken=this.jwtHelper.generateRefreshToken(userDetails);
            UserDto userDto=modelMapper.map(userRepo.findByEmail(usernameFromToken).orElse(null) , UserDto.class);
            return new ResponseEntity<>(new JwtResponse(accessToken,newRefreshToken,userDto),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ErrorResponse(" refresh token is not valid", "400", false),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/test")
    @PreAuthorize("hasRole('NORMAL')")
    public String test(){
        return "test";
    }
}
