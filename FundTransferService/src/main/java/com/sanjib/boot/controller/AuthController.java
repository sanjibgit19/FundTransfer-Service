package com.sanjib.boot.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjib.boot.Entity.RegistrationEntity;
import com.sanjib.boot.model.JwtResponse;
import com.sanjib.boot.model.LoginRequest;
import com.sanjib.boot.model.RegistrationModel;
import com.sanjib.boot.security.jwt.JwtUtils;
import com.sanjib.boot.security.service.UserDetailsImpl;
import com.sanjib.boot.service.AuthService;
import com.sanjib.boot.util.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/auth")
@Slf4j
@Api
@Validated
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping(value = "/registerUser", consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "POST API",response = ResponseEntity.class)
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationModel regModel){
		ResponseEntity<?> responseEntity=null;
		
			responseEntity= authService.registerUser(regModel);
			log.info("Service call done for user registration in controller");
		return responseEntity;
	}//registerUser()
	
	@PostMapping("/login")
	@ApiOperation(value = "POST API",response = ResponseEntity.class)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(),  
												 roles));
	}//authenticateUser(-)

}//class