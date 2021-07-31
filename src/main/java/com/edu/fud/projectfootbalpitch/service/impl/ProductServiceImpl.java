package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.constant.SystemConstant;
import com.edu.fud.projectfootbalpitch.converter.ProductConverter;
import com.edu.fud.projectfootbalpitch.dto.CategoryProductDto;
import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.dto.ProductDto;
import com.edu.fud.projectfootbalpitch.dto.UserDto;
import com.edu.fud.projectfootbalpitch.entity.*;
import com.edu.fud.projectfootbalpitch.repository.CategoryProductRepository;
import com.edu.fud.projectfootbalpitch.repository.ProductRepository;
import com.edu.fud.projectfootbalpitch.repository.SupplierRepository;
import com.edu.fud.projectfootbalpitch.service.ProductService;
import com.edu.fud.projectfootbalpitch.service.UserService;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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
            productDtoList.add(productDto);
        }
        return productDtoList;
    }
    //sua
    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        CategoryProductEntity categoryProductEntity=
                categoryProductRepository.getById(productDto.getCategoryId());
        SupplierEntity supplierEntity=supplierRepository.getById(productDto.getSupplierId());
        ProductsEntity productsEntity=new ProductsEntity();
        if (productDto.getId() != null){
            ProductsEntity oldProductsEntity=productRepository.findById(productDto.getId()).get();
            oldProductsEntity=beanConfig.modelMapper().map(productDto,ProductsEntity.class);
            oldProductsEntity.setCategoryProductEntity(categoryProductEntity);
            oldProductsEntity.setSupplierEntity(supplierEntity);
            productRepository.save(oldProductsEntity);
        }else {
            productsEntity=beanConfig.modelMapper().map(productDto,ProductsEntity.class);
//            CategoryProductEntity categoryProductEntity=new CategoryProductEntity();
//            SupplierEntity supplierEntity=new SupplierEntity();
            supplierEntity.setId(productDto.getSupplierId());
            categoryProductEntity.setId(productDto.getCategoryId());
            productsEntity.setCategoryProductEntity(categoryProductEntity);
            productsEntity.setSupplierEntity(supplierEntity);
            productsEntity.setStatus(SystemConstant.ACTIVE_STATUS);
            productRepository.save(productsEntity);
        }

        return beanConfig.modelMapper().map(productsEntity,ProductDto.class);
    }
    //tao
    @Override
    public Optional<ProductDto> findById(long id) {
        Optional<ProductsEntity> opt=productRepository.findById(id);
        return opt.map(productsEntity -> beanConfig.modelMapper().map(productsEntity, ProductDto.class));
    }
    //tao
    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
