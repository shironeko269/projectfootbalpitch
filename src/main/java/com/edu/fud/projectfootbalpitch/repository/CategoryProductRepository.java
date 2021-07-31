package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.CategoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<CategoryProductEntity,Long> {
    List<CategoryProductEntity> findAll();
}
