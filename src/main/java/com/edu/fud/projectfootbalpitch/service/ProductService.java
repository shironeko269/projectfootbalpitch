package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.ProductDto;
import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto save(ProductDto productDto);
}
