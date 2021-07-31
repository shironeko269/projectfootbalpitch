package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.PitchTypeDto;
import com.edu.fud.projectfootbalpitch.entity.PitchTypeEntity;
import com.edu.fud.projectfootbalpitch.repository.PitchTypeRepository;
import com.edu.fud.projectfootbalpitch.service.PitchTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PitchTypeServiceImpl implements PitchTypeService {

    @Autowired
    private PitchTypeRepository pitchTypeRepository;

    @Autowired
    private BeanConfig beanConfig;

    @Override
    public List<PitchTypeDto> findAll() {
        List<PitchTypeDto> pitchTypeDtos=new ArrayList<>();
        List<PitchTypeEntity> pitchTypeEntities= pitchTypeRepository.findAll();
        for (PitchTypeEntity pitchTypeEntity:
             pitchTypeEntities) {
            pitchTypeDtos.add(beanConfig.modelMapper().map(pitchTypeEntity,PitchTypeDto.class));
        }
        return pitchTypeDtos;
    }
}
