package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.PitchScheduleServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PitchScheduleServiceRepository extends JpaRepository<PitchScheduleServiceEntity,Long> {
    @Query(value = " select sum(price*quantity) from pitch_schedule_service",nativeQuery = true)
    double StatisticsPrice();
    @Query(value = "select month(p.createddate) month  from services s\n" +
            " inner join pitch_schedule_service p on s.id=p.service_id\n" +
            "group by month(p.createddate) order by  month(p.createddate) asc",nativeQuery = true)
    int[] findAllMonthOfService();
    @Query(value = "select sum(s.price*p.quantity) as soTienThuDuocTrongThang from services s\n" +
            " inner join pitch_schedule_service p on s.id=p.service_id where month(p.createddate)=:month",nativeQuery = true)
    double totalPriceOfServiceByMonth(int month);
    @Query(value = "select sum(s.price*p.quantity) as soTienThuDuocTrongThang from services s\n" +
            " inner join pitch_schedule_service p on s.id=p.service_id where month(p.createddate)=:month",nativeQuery = true)
    Double totalPriceOfServiceByMonthDouble(int month);
}
