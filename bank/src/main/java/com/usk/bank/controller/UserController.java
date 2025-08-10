package com.usk.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usk.bank.dto.ApplicationResponseDto;
import com.usk.bank.dto.UserRequest;
import com.usk.bank.model.Users;
import com.usk.bank.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ApplicationResponseDto register(@RequestBody UserRequest userRequest) {
		System.out.println(userRequest.getName());
		System.out.println(userRequest.getEmail());
		return userService.registerUser(userRequest);
	}

	@GetMapping("/login/{userName}/{password}")
	public ApplicationResponseDto login(@RequestParam String userName, @RequestParam String password) {
		return userService.login(userName, password);
	}

}
