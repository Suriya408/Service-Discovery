package com.usk.bank.service;

import com.usk.bank.dto.ApplicationResponseDto;
import com.usk.bank.dto.UserRequest;

public interface UserService {

	ApplicationResponseDto registerUser(UserRequest userRequest);

	ApplicationResponseDto login(String userName, String password);

}
