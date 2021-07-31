package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.SubPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubPitchRepository extends JpaRepository<SubPitchEntity , Long> {
    @Query(value = "select s.* from football_pitchs f inner join sub_pitch s on f.id=s.pitch_pitch_id where f.id=(select max(id)from football_pitchs)",nativeQuery = true)
    List<SubPitchEntity> findAllByFootballPitchEntitySub();

    @Query(value = "select * from sub_pitch where pitch_pitch_id=:idPitchBig",nativeQuery = true)
    List<SubPitchEntity> findAndByIdPitchBig(long idPitchBig);
}
