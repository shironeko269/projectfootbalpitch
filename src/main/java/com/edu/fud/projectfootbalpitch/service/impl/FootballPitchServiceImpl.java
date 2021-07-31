package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;
import com.edu.fud.projectfootbalpitch.entity.UserEntity;
import com.edu.fud.projectfootbalpitch.entity.WardEntity;
import com.edu.fud.projectfootbalpitch.repository.FootballPitchRepository;
import com.edu.fud.projectfootbalpitch.repository.UserRepository;
import com.edu.fud.projectfootbalpitch.repository.WardRepository;
import com.edu.fud.projectfootbalpitch.service.FootbalPitchService;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FootballPitchServiceImpl implements FootbalPitchService {

    @Autowired
    private FootballPitchRepository footballPitchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private BeanConfig beanConfig;

    @Override
    public FootBallPitchDto save(FootBallPitchDto footBallPitchDto) {
        UserEntity userEntity = new UserEntity();
        WardEntity wardEntity = wardRepository.getById(footBallPitchDto.getWardId());
        if (footBallPitchDto.getUserId() == null) {
            footBallPitchDto.setUserId(null);
        } else {
            userEntity = userRepository.getById(footBallPitchDto.getUserId());
        }
        FootballPitchEntity footballPitchEntity = new FootballPitchEntity();
        if (footBallPitchDto.getId() != null) {
            FootballPitchEntity oldPitchEntity = footballPitchRepository.getById(footBallPitchDto.getId());
            oldPitchEntity = beanConfig.modelMapper().map(footBallPitchDto, FootballPitchEntity.class);
            if (footBallPitchDto.getUserId() == null) {
                oldPitchEntity.setUserEntityPitch(null);
            } else {
                oldPitchEntity.setUserEntityPitch(userEntity);
            }
            oldPitchEntity.setWardEntityPitch(wardEntity);
            footballPitchRepository.save(oldPitchEntity);
        } else {
            footballPitchEntity = beanConfig.modelMapper().map(footBallPitchDto, FootballPitchEntity.class);
//        UserEntity userEntity = new UserEntity();
//        WardEntity wardEntity = new WardEntity();
            userEntity.setId(footBallPitchDto.getUserId());
            wardEntity.setId(footBallPitchDto.getWardId());
            if (footBallPitchDto.getUserId() == null) {
                footballPitchEntity.setUserEntityPitch(null);
            } else {
                footballPitchEntity.setUserEntityPitch(userEntity);
            }
            footballPitchEntity.setWardEntityPitch(wardEntity);
            footballPitchRepository.save(footballPitchEntity);
        }
        return beanConfig.modelMapper().map(footballPitchEntity, FootBallPitchDto.class);
    }

    @Override
    public FootBallPitchDto findByIdMax() {
        FootballPitchEntity footballPitchEntity = footballPitchRepository.findByIdMax();
        FootBallPitchDto footBallPitchDto = new FootBallPitchDto();
        footBallPitchDto = beanConfig.modelMapper().map(footballPitchEntity, FootBallPitchDto.class);
        footBallPitchDto.setDistrictName(footballPitchEntity.getWardEntityPitch().getDistrictEntity().getDistrictName());
        return footBallPitchDto;
    }

    @Override
    public List<FootBallPitchDto> findAll() {
        List<FootballPitchEntity> footballPitchEntityList = footballPitchRepository.findAll();
        List<FootBallPitchDto> footBallPitchDtoList = new ArrayList<>();
        for (FootballPitchEntity footballPitchEntity : footballPitchEntityList
        ) {
            FootBallPitchDto footBallPitchDto = beanConfig.modelMapper().map(footballPitchEntity, FootBallPitchDto.class);
            //kiem tra neu khong co ai quan li san thi null
            if (footBallPitchDto.getUserId() == null) {
                footBallPitchDto.setManagerFullName("Chưa có ai quản lí!");
            } else {
                footBallPitchDto.setManagerFullName(footballPitchEntity.getUserEntityPitch().getFullName());
            }
            //set ten quan
            footBallPitchDto.setDistrictName(footballPitchEntity.getWardEntityPitch().getDistrictEntity().getDistrictName());
            footBallPitchDtoList.add(footBallPitchDto);
        }
        return footBallPitchDtoList;
    }

    @Override
    public Optional<FootBallPitchDto> findPitchById(long id) {
        Optional<FootballPitchEntity> optional = footballPitchRepository.findById(id);
        return optional.map(footballPitchEntity -> beanConfig.modelMapper().map(footballPitchEntity, FootBallPitchDto.class));
    }

    @Override
    public FootBallPitchDto findPitchByIdEdit(long id) {
        FootballPitchEntity footballPitchEntity = footballPitchRepository.getById(id);
        FootBallPitchDto footBallPitchDto = new FootBallPitchDto();
        footBallPitchDto = beanConfig.modelMapper().map(footballPitchEntity, FootBallPitchDto.class);
        footBallPitchDto.setUserId(footballPitchEntity.getUserEntityPitch().getId());
        return footBallPitchDto;
    }

    @Override
    public void deletePitch(long id) {
        footballPitchRepository.deleteById(id);
    }

    @Override
    public Optional<FootBallPitchDto> findAllStreetNumber(String streetNumber) {
        Optional<FootballPitchEntity> footballPitchEntityList=footballPitchRepository.findAllStreetNumber(streetNumber);
        return footballPitchEntityList.map(footballPitchEntity ->
                beanConfig.modelMapper().map(footballPitchEntity,FootBallPitchDto.class));
    }

    @Override
    public Optional<FootBallPitchDto> findAllUrlMap(String urlMap) {
        Optional<FootballPitchEntity> footballPitchEntityList=footballPitchRepository.findAllUrlMap(urlMap);
        return footballPitchEntityList.map(footballPitchEntity ->
                beanConfig.modelMapper().map(footballPitchEntity,FootBallPitchDto.class));
    }

    @Override
    public String findStreetNumberByPitchId(long pitchId) {
        String streetNumber=footballPitchRepository.findStreetNumberByPitchId(pitchId);
        return streetNumber;
    }

    @Override
    public String findUrlMapByPitchId(long pitchId) {
        String urlMap=footballPitchRepository.findUrlMapByPitchId(pitchId);
        return urlMap;
    }

}
