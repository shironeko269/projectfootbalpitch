package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;
import com.edu.fud.projectfootbalpitch.entity.SubPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubPitchRepository extends JpaRepository<SubPitchEntity,Long> {
    @Query(value = "select s.* from football_pitchs f inner join sub_pitch s on f.id=s.pitch_pitch_id where f.user_id=:userId",nativeQuery = true)
    List<SubPitchEntity> findAllSubPitchByUserId(long userId);

    @Query(value = " select count(sp.id) as tongsanbong from sub_pitch sp inner join\n" +
            " football_pitchs fp inner join user u \n" +
            " on sp.pitch_pitch_id = fp.id and fp.user_id = u.id\n" +
            " where u.id=:userId",nativeQuery = true)
    int TotalSubPitchByManager(long userId);
}
