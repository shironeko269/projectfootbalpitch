package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrderEntity,Long> {
    @Query(value = " select sum(total_price) from orders",nativeQuery = true)
    double StatisticsTotalPrice();
    @Query(value = "select * from orders order by id desc",nativeQuery = true)
    List<OrderEntity> findAll();
}
