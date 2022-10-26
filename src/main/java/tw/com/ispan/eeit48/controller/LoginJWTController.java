package tw.com.ispan.eeit48.controller;

import java.util.HashMap;

import javax.security.auth.message.AuthException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.utils.JwtToken;

@RestController
@RequestMapping("/api")

public class LoginJWTController {
	
	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody HashMap <String, String> user) {
	    JwtToken jwtToken = new JwtToken();
	    String token = jwtToken.generateToken(user); // 取得token
	    return ResponseEntity.status(HttpStatus.OK).body(token);
	   }
	
	@GetMapping("/hello")
	public ResponseEntity hello(@RequestHeader("Authorization") String au) {
	    String token = au.substring(6);
	    JwtToken jwtToken = new JwtToken();
	    try {
	        jwtToken.validateToken(token);
	    } catch (AuthException e) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
	    }

	    return ResponseEntity.status(HttpStatus.OK).body("Hello CaiLi");
	  }
}
