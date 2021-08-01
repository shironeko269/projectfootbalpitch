package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.SubPitchDto;
import com.edu.fud.projectfootbalpitch.entity.SubPitchEntity;
import com.edu.fud.projectfootbalpitch.service.SubPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubPitchServiceImpl implements SubPitchService {
    @Autowired
    private SubPitchRepository subPitchRepository;
    @Autowired
    private BeanConfig beanConfig;

    @Override
    public List<SubPitchDto> findAllSubPitchByUserId(long userId) {
        List<SubPitchEntity> footballPitchEntityList=subPitchRepository.findAllSubPitchByUserId(userId);
        List<SubPitchDto> subPitchDtoList=new ArrayList<>();
        for (SubPitchEntity subPitchEntity:footballPitchEntityList
             ) {
            subPitchDtoList.add(beanConfig.modelMapper().map(subPitchEntity,SubPitchDto.class));
        }
        return subPitchDtoList;
    }
}
