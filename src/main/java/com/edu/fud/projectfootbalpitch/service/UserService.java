package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.UserDto;
import com.edu.fud.projectfootbalpitch.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto save(UserDto user);
    UserDto saveManager(UserDto userManager);
    List<UserDto> findAllByRoleAndNoManagerPitch();
    Optional<UserDto> getUserByUsername(String username);
}
