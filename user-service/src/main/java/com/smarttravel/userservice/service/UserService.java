package com.smarttravel.userservice.service;

import com.smarttravel.userservice.dto.UserRequestDTO;
import com.smarttravel.userservice.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(Long id);
}

