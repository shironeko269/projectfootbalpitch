package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.FootballPitchScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface FootballPitchScheduleRepository extends JpaRepository<FootballPitchScheduleEntity,Long> {
    @Query(value = "select sum(f.price) from football_pitchs_schedule f",nativeQuery = true)
    double StatisticsPrice();
    @Query(value = "select month(f.date_booking) month from football_pitchs_schedule f\n" +
            "group by month(f.date_booking) order by month(f.date_booking) asc",nativeQuery = true)
    int[] findAllMonth();
    @Query(value = "select sum(f.price) as soTienThuDuocTrongThang " +
            "from football_pitchs_schedule f where month(f.date_booking)=:month",nativeQuery = true)
    double findAllTotalPriceByMonth(int month);
    @Query(value = "select sum(f.price) as soTienThuDuocTrongThang " +
            "from football_pitchs_schedule f where month(f.date_booking)=:month",nativeQuery = true)
    Double findAllTotalPriceByMonthCheck(int month);
}
