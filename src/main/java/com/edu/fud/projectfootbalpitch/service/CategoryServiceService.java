package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.CategoryServiceDto;
import com.edu.fud.projectfootbalpitch.dto.ServiceDto;
import com.edu.fud.projectfootbalpitch.entity.CategoryServiceEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceService {
    List<CategoryServiceDto> findAll();
    CategoryServiceDto save(CategoryServiceDto categoryServiceDto);
    Optional<CategoryServiceDto> findById(long id);
    void deleteCategory(long id);
    List<CategoryServiceDto> findAllByCategoryName(String categoryName);
}
