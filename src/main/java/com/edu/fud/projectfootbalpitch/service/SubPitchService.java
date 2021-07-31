package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.dto.SubPitchDto;

import java.util.List;

public interface SubPitchService {
    List<SubPitchDto> findAllSubPitchByUserId(long userId);
}
