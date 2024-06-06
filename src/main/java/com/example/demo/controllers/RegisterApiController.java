package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegisterApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/register")
	public ResponseEntity registerNewUser(@Param("nombre") String nombre,
										  @Param("apellido") String apellido,
										  @Param("email") String email,
										  @Param("password") String password) {
		if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
			return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);
		}
		
		//hash password
		String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
		
		
		int result = userService.registerNewUserServiceMethod(nombre, apellido, email, hashed_password);
		
		if (result !=1) {
			return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("success",HttpStatus.OK);
	} 
	

}
