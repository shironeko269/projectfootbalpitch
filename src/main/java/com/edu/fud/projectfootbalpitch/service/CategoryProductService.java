package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.CategoryProductDto;

import java.util.List;

public interface CategoryProductService {
    List<CategoryProductDto> findAll();
}
