package tklimczak.atm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tklimczak.atm.payload.JwtAuthenticationResponse;
import tklimczak.atm.payload.LoginRequest;
import tklimczak.atm.repository.AccountRepository;
import tklimczak.atm.repository.RoleRepository;
import tklimczak.atm.security.JwtTokenProvider;

@RestController
@RequestMapping("/atm/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	JwtTokenProvider tokenProvider;

	@CrossOrigin
	@PostMapping()
	public ResponseEntity<?> authenticateAccount(@Valid @RequestBody LoginRequest loginRequest) {
		final String cardNumber = loginRequest.getCardNumber();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(cardNumber, loginRequest.getPin()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, cardNumber));
	}
}
