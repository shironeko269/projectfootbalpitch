package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.PitchScheduleServiceDto;
import com.edu.fud.projectfootbalpitch.entity.PitchScheduleServiceEntity;
import com.edu.fud.projectfootbalpitch.service.PitchScheduleServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PitchScheduleServiceServiceImpl implements PitchScheduleServiceService {


    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private PitchScheduleServiceRepository pitchScheduleServiceRepository;

    @Override
    public List<PitchScheduleServiceDto> findAllServiceByPitchScheduleByBooking(long bookingId) {
        List<PitchScheduleServiceDto> pitchScheduleServiceDtoList=new ArrayList<>();
        List<PitchScheduleServiceEntity> pitchScheduleServiceEntityList= pitchScheduleServiceRepository.findAllServiceByPitchScheduleByBooking(bookingId);
        for (PitchScheduleServiceEntity pitchScheduleServiceEntity : pitchScheduleServiceEntityList
        ){
            PitchScheduleServiceDto pitchScheduleServiceDto=
                beanConfig.modelMapper().map(pitchScheduleServiceEntity,PitchScheduleServiceDto.class);
            pitchScheduleServiceDto.setNameService(pitchScheduleServiceEntity.getServiceEntityPitch().getName());
            pitchScheduleServiceDto.setUnit(pitchScheduleServiceEntity.getServiceEntityPitch().getUnit());
            pitchScheduleServiceDtoList.add(pitchScheduleServiceDto);
        }
        return pitchScheduleServiceDtoList;
    }
}
