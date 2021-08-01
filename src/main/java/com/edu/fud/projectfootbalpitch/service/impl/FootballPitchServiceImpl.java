package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;
import com.edu.fud.projectfootbalpitch.entity.UserEntity;
import com.edu.fud.projectfootbalpitch.entity.WardEntity;
import com.edu.fud.projectfootbalpitch.service.FootbalPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballPitchServiceImpl implements FootbalPitchService {

    @Autowired
    private FootballPitchRepository footballPitchRepository;


    @Autowired
    private BeanConfig beanConfig;

    @Override
    public FootBallPitchDto save(FootBallPitchDto footBallPitchDto) {
        FootballPitchEntity footballPitchEntity=new FootballPitchEntity();
        footballPitchEntity=beanConfig.modelMapper().map(footBallPitchDto,FootballPitchEntity.class);
        UserEntity userEntity=new UserEntity();
        WardEntity wardEntity =new WardEntity();
        userEntity.setId(footBallPitchDto.getUserId());
        wardEntity.setId(footBallPitchDto.getWardId());
        footballPitchEntity.setUserEntityPitch(userEntity);
        footballPitchEntity.setWardEntityPitch(wardEntity);
        footballPitchRepository.save(footballPitchEntity);
        return beanConfig.modelMapper().map(footballPitchEntity,FootBallPitchDto.class);
    }

    @Override
    public FootBallPitchDto findFootballPitchDtoByUserId(long userId) {
        FootballPitchEntity footballPitchEntity= footballPitchRepository.findFootballPitchEntitiesByUserId(userId);
        FootBallPitchDto footBallPitchDto=new FootBallPitchDto();
        footBallPitchDto=beanConfig.modelMapper().map(footballPitchEntity,FootBallPitchDto.class);
        footBallPitchDto.setDistrictName(footballPitchEntity.getWardEntityPitch().getDistrictEntity().getDistrictName());
        return footBallPitchDto;
    }


}
