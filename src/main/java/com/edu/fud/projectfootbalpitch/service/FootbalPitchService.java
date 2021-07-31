package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;

import java.util.List;
import java.util.Optional;

public interface FootbalPitchService {
    FootBallPitchDto save(FootBallPitchDto footBallPitchDto);
    FootBallPitchDto findByIdMax();
    List<FootBallPitchDto> findAll();
    Optional<FootBallPitchDto> findPitchById(long id);
    FootBallPitchDto findPitchByIdEdit(long id);
    void deletePitch(long id);
    Optional<FootBallPitchDto> findAllStreetNumber(String streetNumber);
    Optional<FootBallPitchDto> findAllUrlMap(String urlMap);
    String findStreetNumberByPitchId(long pitchId);
    String findUrlMapByPitchId(long pitchId);
}
