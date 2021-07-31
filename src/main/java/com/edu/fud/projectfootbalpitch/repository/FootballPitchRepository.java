package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FootballPitchRepository extends JpaRepository<FootballPitchEntity,Long> {

    @Query(value = "select * from football_pitchs where user_id=:userId",nativeQuery = true)
    FootballPitchEntity findFootballPitchEntitiesByUserId(long userId);



}
