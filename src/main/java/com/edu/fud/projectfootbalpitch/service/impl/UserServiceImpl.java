package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.UserDto;
import com.edu.fud.projectfootbalpitch.entity.UserEntity;
import com.edu.fud.projectfootbalpitch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //https://viblo.asia/p/su-dung-modelmapper-trong-spring-boot-63vKj1Vd52R
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        UserEntity userEntity =new UserEntity();
        if (userDto.getId() == null){
            userDto.setRole("ROLE_USER");
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userEntity = beanConfig.modelMapper().map(userDto,UserEntity.class);
        }
        this.userRepository.save(userEntity);
        return beanConfig.modelMapper().map(userEntity,UserDto.class);
    }

    @Override
    public UserDto saveManager(UserDto userManager) {
        UserEntity userEntity =new UserEntity();
        if (userManager.getId()!=null){
            userManager.setRole("ROLE_MANAGER");
            userManager.setPassword(passwordEncoder.encode(userManager.getPassword()));
            userEntity = beanConfig.modelMapper().map(userManager,UserEntity.class);
            userEntity=this.userRepository.save(userEntity);
        }
        return beanConfig.modelMapper().map(userEntity,UserDto.class);
    }

    @Override
    public List<UserDto> findAllByRoleAndNoManagerPitch() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<UserEntity> userEntityList= userRepository.findAllByRoleAndNoManagerPitch();
        for (UserEntity userEntity:userEntityList
             ) {
            userDtoList.add(beanConfig.modelMapper().map(userEntity,UserDto.class));
        }
        return userDtoList;
    }

    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional=userRepository.getOneByUserName(username);
        return userEntityOptional.map(userEntity -> beanConfig.modelMapper().map(userEntity,UserDto.class));
    }
}
