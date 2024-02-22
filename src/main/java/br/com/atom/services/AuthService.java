package br.com.atom.services;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.atom.dtos.RequestResponse;
import br.com.atom.entities.OurUsers;
import br.com.atom.repositories.OurUserRepository;

@Service
public class AuthService {

	private OurUserRepository ourUserRepository;
	
	private JWTUtils jwtUtils;
	
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;

	public AuthService(OurUserRepository ourUserRepository, JWTUtils jwtUtils, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.ourUserRepository = ourUserRepository;
		this.jwtUtils = jwtUtils;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}
	

	public RequestResponse signUp(RequestResponse registrationRequest) {
		RequestResponse resp = new RequestResponse();
		try {
			OurUsers ourUsers = new OurUsers();
			ourUsers.setEmail(registrationRequest.getEmail());
			ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			ourUsers.setRole(registrationRequest.getRole());
			OurUsers ourUserResult = ourUserRepository.save(ourUsers);
			if (ourUserResult != null && ourUserResult.getId()>0) {
				resp.setOurUsers(ourUserResult);
				resp.setMessage("User saved Successfully");
				resp.setStatusCode(200);
			}
		} catch (Exception e) {
			resp.setStatusCode(500);
			resp.setError(e.getMessage());
		}
		return resp;
	}
	
	public RequestResponse signIn(RequestResponse signinRequest) {
		RequestResponse response = new RequestResponse();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
					var user = ourUserRepository.findByEmail(signinRequest.getEmail()).orElseThrow();
					System.out.println("USER IS: " + user);
					var jwt = jwtUtils.generateToken(user);
					var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
					response.setStatusCode(200);
					response.setToken(jwt);
					response.setRefreshToken(refreshToken);
					response.setExpirationTime("24H");
					response.setMessage("Successfully Signed In");
		}catch (Exception e) {
			response.setStatusCode(500);
			response.setError(e.getMessage());
		}
		return response;
	}
	
	public RequestResponse refreshToken(RequestResponse refreshTokenRegister) {
		 RequestResponse response = new RequestResponse();
		 String ourEmail = jwtUtils.extractUsername(refreshTokenRegister.getToken());
		 OurUsers users = ourUserRepository.findByEmail(ourEmail).orElseThrow();
		 if (jwtUtils.isTokenValid(refreshTokenRegister.getToken(), users)) {
			 var jwt = jwtUtils.generateToken(users);
			 response.setStatusCode(200);
			 response.setToken(jwt);
			 response.setRefreshToken(refreshTokenRegister.getToken());
			 response.setExpirationTime("24H");
			 response.setMessage("Successfully Refreshed Token");
		 }
		 response.setStatusCode(500);
		 return response;
	}
}
