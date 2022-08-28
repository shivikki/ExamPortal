package com.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.config.JwtUtils;
import com.modals.JwtRequest;
import com.modals.JwtResponse;
import com.modals.User;
import com.service.UserSecurityServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticateController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticateController.class);
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserSecurityServiceImpl userSecurity;

	@Autowired
	private JwtUtils jwtUtil;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
		try {
			JwtResponse jwtRes = authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
			if (jwtRes.getToken().equals("VALID")) {
				// user Authenticated
				LOG.info(" generateToken" + jwtRequest);
				UserDetails userDetails = userSecurity.loadUserByUsername(jwtRequest.getEmail());
				LOG.info("Valid user details " + userDetails);
				String token = jwtUtil.generateToken(userDetails);
				return ResponseEntity.ok(new JwtResponse(token));
			}
			else {
				return ResponseEntity.ok(jwtRes);
			}
		} catch (Exception e) {
			LOG.error("error in  generateToken invalid user" + e);
			return ResponseEntity.ok(new JwtResponse("Invalid User"));
		}

	}

	private JwtResponse authenticate(String email, String password) {

		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		} catch (Exception e) {
			LOG.error("INVALID " + e);
			return new JwtResponse("Invalid User");
		}
		return new JwtResponse("VALID");
	}
	
	//get user details
//	@GetMapping("/currentUser")
//	public User getCurrentUser(Principal principal) {
//		return  (User) userSecurity.loadUserByUsername(principal.getName());
//	}
}
