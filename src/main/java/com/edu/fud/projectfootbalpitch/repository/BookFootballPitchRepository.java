package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.BookFootballPitchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookFootballPitchRepository extends JpaRepository<BookFootballPitchEntity,Long> {
    @Query(value = "select b.*,f.time_start as GioDatNhieuNhat,count(f.time_start) as SoDonDat from \n" +
            "book_football_pitch b inner join football_pitchs_schedule f on b.football_pitch_schedule_id=f.id \n" +
            "group by f.time_start order by count(f.time_start) desc limit 5",nativeQuery = true)
    List<BookFootballPitchEntity> findAllTimeBookingMost();

}
