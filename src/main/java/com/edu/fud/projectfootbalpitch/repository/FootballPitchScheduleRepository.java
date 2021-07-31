package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.FootballPitchScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FootballPitchScheduleRepository extends JpaRepository<FootballPitchScheduleEntity,Long> {
    @Query(value = "select f.id from football_pitchs_schedule f inner join book_football_pitch b\n" +
            " on b.football_pitch_schedule_id=f.id where b.football_pitch_status_id=3"
            ,nativeQuery = true)
    long[] deleteAllByStatusCancel();

    @Query(value = "select month(sche.date_booking) month from user u inner join football_pitchs f inner join sub_pitch sub inner join\n" +
            "football_pitchs_schedule sche on u.id=f.user_id and f.id=sub.pitch_pitch_id and sche.sub_pitch_id\n" +
            "=sub.id where u.id=:userId group by month(sche.date_booking)  order by month(sche.date_booking) asc;",nativeQuery = true)
    int[] findAllMonthOfPitchByManagerId(long userId);

    @Query(value = "select sum(sche.price) from user u inner join football_pitchs f \n" +
            "inner join sub_pitch sub inner join football_pitchs_schedule sche \n" +
            "on u.id=f.user_id and f.id=sub.pitch_pitch_id and sche.sub_pitch_id\n" +
            "=sub.id where u.id=:userId and month(sche.date_booking)=:month",nativeQuery = true)
    double totalPricePitchOfMonthByManagerId(long userId,int month);
    @Query(value = "select sum(sche.price) from user u inner join football_pitchs f \n" +
            "inner join sub_pitch sub inner join football_pitchs_schedule sche \n" +
            "on u.id=f.user_id and f.id=sub.pitch_pitch_id and sche.sub_pitch_id\n" +
            "=sub.id where u.id=:userId and month(sche.date_booking)=:month",nativeQuery = true)
    Double totalPricePitchOfMonthByManagerIdCheck(long userId,int month);
}
