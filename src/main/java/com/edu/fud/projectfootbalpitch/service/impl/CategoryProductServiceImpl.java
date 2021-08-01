package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.CategoryProductDto;
import com.edu.fud.projectfootbalpitch.entity.CategoryProductEntity;
import com.edu.fud.projectfootbalpitch.service.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Autowired
    private BeanConfig beanConfig;

    @Override
    public List<CategoryProductDto> findAll() {
        List<CategoryProductDto> productDtoList=new ArrayList<>();
        List<CategoryProductEntity> categoryProductEntities= categoryProductRepository.findAll();
        for (CategoryProductEntity categoryProductEntity :
                categoryProductEntities) {
            productDtoList.add(beanConfig.modelMapper().map(categoryProductEntity,CategoryProductDto.class));
        }
        return productDtoList;
    }
}
