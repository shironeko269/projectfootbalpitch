package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.converter.ProductConverter;
import com.edu.fud.projectfootbalpitch.dto.ProductDto;
import com.edu.fud.projectfootbalpitch.entity.CategoryProductEntity;
import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;
import com.edu.fud.projectfootbalpitch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductRepository categoryProductRepository;


    @Override
    @Transactional
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList=new ArrayList<>();
        List<ProductsEntity> productsEntityList= productRepository.findAll();
        for (ProductsEntity entity:
             productsEntityList) {
            ProductDto productDto=beanConfig.modelMapper().map(entity,ProductDto.class);
            productDto.setCategoryName(entity.getCategoryProductEntity().getName());
            productDto.setCompanyName(entity.getSupplierEntity().getCompanyName());
            productDto.setCategoryId(entity.getCategoryProductEntity().getId());
            productDtoList.add(productDto);
        }
//        for (ProductsEntity productsEntity :
//                productsEntityList) {
//            ProductDto productDto=productConverter.toDto(productsEntity);
//            productDtoList.add(productDto);
//        }
        return productDtoList;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        CategoryProductEntity categoryProductEntity=
                categoryProductRepository.getById(productDto.getCategoryId());
        return null;
    }
}
