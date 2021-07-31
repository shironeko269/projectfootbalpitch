package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;

import java.util.List;

public interface FootbalPitchService {
    FootBallPitchDto save(FootBallPitchDto footBallPitchDto);
    FootBallPitchDto findFootballPitchDtoByUserId(long userId);

}
