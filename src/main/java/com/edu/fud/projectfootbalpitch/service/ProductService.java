package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.ProductDto;
import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto save(ProductDto productDto);
    //tao
    Optional<ProductDto> findById(long id);
    void deleteProduct(long id);
}
