package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {
    @Query(value = "select s.*,sum(pss.quantity) as totalQuantitySellMost from services s\n" +
            "inner join pitch_schedule_service as pss\n" +
            "   on pss.service_id = s.id  group by s.id order by totalQuantitySellMost desc limit 5",nativeQuery = true)
    List<ServiceEntity> findAllByQuantitySellMost();

    @Query(value = "select sum(pss.quantity) as totalQuantitySellMost from services s\n" +
            "inner join pitch_schedule_service as pss\n" +
            "   on pss.service_id = s.id where s.id=:serviceId group by s.id",nativeQuery = true)
    int sumQuantitySell(long serviceId);

    @Query(value = "select sum(pss.quantity*s.price) as totalQuantitySellMost from services s\n" +
            "inner join pitch_schedule_service as pss\n" +
            "   on pss.service_id = s.id where s.id=:serviceId group by s.id;",nativeQuery = true)
    double sumPriceSell(long serviceId);
}
