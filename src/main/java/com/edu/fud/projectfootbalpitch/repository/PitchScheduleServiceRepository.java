package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.PitchScheduleServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PitchScheduleServiceRepository extends JpaRepository<PitchScheduleServiceEntity,Long > {

    @Query(value = "select s.* from book_football_pitch b inner join football_pitchs_schedule f inner join \n" +
            " pitch_schedule_service s on f.id = s.football_pitch_schedule_id  and \n" +
            " f.id=b.football_pitch_schedule_id where b.id=:bookingId",nativeQuery = true)
    List<PitchScheduleServiceEntity> findAllServiceByPitchScheduleByBooking(long bookingId);

    @Query(value = "select month(pser.createddate) month from user u inner join \n" +
            "football_pitchs f inner join sub_pitch sub inner join football_pitchs_schedule sche \n" +
            "inner join pitch_schedule_service pser inner join services s\n" +
            "on u.id=f.user_id and f.id=sub.pitch_pitch_id and sche.sub_pitch_id\n" +
            "=sub.id and pser.football_pitch_schedule_id = sche.id and pser.service_id = s.id\n" +
            "where u.id=:userId group by month(pser.createddate)  order by month(pser.createddate) asc",nativeQuery = true)
    int[] findAllMonthOfServiceByManagerId(long userId);

    @Query(value = "select sum(pser.quantity*s.price) from user u inner join \n" +
            "football_pitchs f inner join sub_pitch sub inner join football_pitchs_schedule sche \n" +
            "inner join pitch_schedule_service pser inner join services s\n" +
            "on u.id=f.user_id and f.id=sub.pitch_pitch_id and sche.sub_pitch_id\n" +
            "=sub.id and pser.football_pitch_schedule_id = sche.id and pser.service_id = s.id\n" +
            "where u.id=:userId and month(pser.createddate)=:month ;",nativeQuery = true)
    double totalPriceServiceOfMonthByManagerId(long userId,int month);
    @Query(value = "select sum(pser.quantity*s.price) from user u inner join \n" +
            "football_pitchs f inner join sub_pitch sub inner join football_pitchs_schedule sche \n" +
            "inner join pitch_schedule_service pser inner join services s\n" +
            "on u.id=f.user_id and f.id=sub.pitch_pitch_id and sche.sub_pitch_id\n" +
            "=sub.id and pser.football_pitch_schedule_id = sche.id and pser.service_id = s.id\n" +
            "where u.id=:userId and month(pser.createddate)=:month ;",nativeQuery = true)
    Double totalPriceServiceOfMonthByManagerIdCheck(long userId,int month);
}
