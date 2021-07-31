package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.SubPitchDto;
import com.edu.fud.projectfootbalpitch.entity.SubPitchEntity;

import java.util.List;
import java.util.Optional;

public interface SubPitchService {
    SubPitchDto save(SubPitchDto subPitchDto);
    SubPitchDto saveByPitchOld(SubPitchDto subPitchDto);
    Optional<SubPitchDto> findById(long id);
    List<SubPitchDto> findAllByFootballPitchEntitySub();
    List<SubPitchDto> findAndByIdPitchBig(long id);
    void deleteSubPitch(long id);
}
