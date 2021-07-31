package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.OrderDetailDto;
import com.edu.fud.projectfootbalpitch.entity.OrderDetailEntity;
import com.edu.fud.projectfootbalpitch.repository.OrderDetailRepository;
import com.edu.fud.projectfootbalpitch.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailImpl implements OrderDetailService {

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailDto> findAllByOrderId(long orderId) {
        List<OrderDetailDto> orderDetailDtoList=new ArrayList<>();
        List<OrderDetailEntity> orderDetailEntityList= orderDetailRepository.findAllByOrderId(orderId);
        for (OrderDetailEntity orderDetailEntity : orderDetailEntityList
        ){
            OrderDetailDto orderDetailDto=beanConfig.modelMapper().map(orderDetailEntity,OrderDetailDto.class);
            orderDetailDto.setProductName(orderDetailEntity.getProductsEntityOrderDetail().getName());
            orderDetailDtoList.add(orderDetailDto);
        }
        return orderDetailDtoList;
    }
}
