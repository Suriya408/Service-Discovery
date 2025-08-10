package com.usk.bank.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usk.bank.dto.ApplicationResponseDto;
import com.usk.bank.dto.UserRequest;
import com.usk.bank.dto.UserResponse;
import com.usk.bank.model.Account;
import com.usk.bank.model.Users;
import com.usk.bank.repository.UserRepository;

@Service
public class Userserviceimpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public ApplicationResponseDto registerUser(UserRequest userRequest) {
		ApplicationResponseDto response = new ApplicationResponseDto();
		UserResponse userResponse = new UserResponse();
		Users user = new Users();
		BeanUtils.copyProperties(userRequest, user);
		Long accountNumber = generateAccountNumber();
		if (userRequest.getStartingBalance() > 10000) {
			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setBalance(userRequest.getStartingBalance());
			user.setAccount(account);
			userRepository.save(user);
			BeanUtils.copyProperties(user, userResponse);
			response.setMessage("Registered successfully");
			response.setStatusCode(1001);
			response.setData(userResponse);
		} else {
			response.setMessage("Balance should be greater than 10000");
			response.setStatusCode(1000);
			BeanUtils.copyProperties(userRequest, userResponse);
			response.setData(userResponse);
		}
		return response;
	}

	private Long generateAccountNumber() {
		return userRepository.getAccountNumber();
	}

	@Override
	public ApplicationResponseDto login(String userName, String password) {
		ApplicationResponseDto response = new ApplicationResponseDto();
		UserResponse userResponse = new UserResponse();
		Optional<Users> user = Optional.ofNullable(userRepository.findByNameAndPassword(userName, password));
		if (user.isPresent()) {
			BeanUtils.copyProperties(user, userResponse);
			response.setStatusCode(1000);
			response.setMessage("Login Successfully");
			response.setData(userResponse);
		} else {
			response.setStatusCode(1001);
			response.setMessage("Login Failed");
			response.setData(user);
		}
		return response;
	}

}
