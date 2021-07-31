package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.OrderDto;
import com.edu.fud.projectfootbalpitch.entity.OrderEntity;
import com.edu.fud.projectfootbalpitch.entity.PaymentEntity;
import com.edu.fud.projectfootbalpitch.entity.StatusOrderEntity;
import com.edu.fud.projectfootbalpitch.entity.UserEntity;
import com.edu.fud.projectfootbalpitch.repository.OrdersRepository;
import com.edu.fud.projectfootbalpitch.repository.PaymentOrderRepository;
import com.edu.fud.projectfootbalpitch.repository.StatusOrderRepository;
import com.edu.fud.projectfootbalpitch.repository.UserRepository;
import com.edu.fud.projectfootbalpitch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private BeanConfig beanConfig;
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<OrderDto> findALl() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<OrderEntity> orderEntityList = ordersRepository.findAll();
        for (OrderEntity orderEntity : orderEntityList
        ) {
            OrderDto orderDto = beanConfig.modelMapper().map(orderEntity, OrderDto.class);
            orderDto.setPaymentOrderName(orderEntity.getPaymentOrderEntity().getName());
            orderDto.setUserNameCreate(orderEntity.getUserEntityOrder().getUserName());
            orderDto.setEmailUser(orderEntity.getUserEntityOrder().getGmail());
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public OrderDto findOne(long id) {
        OrderEntity optionalOrderEntity = ordersRepository.getById(id);
        OrderDto orderDto=beanConfig.modelMapper().map(optionalOrderEntity,OrderDto.class);
        orderDto.setUserNameCreate(optionalOrderEntity.getUserEntityOrder().getUserName());
        orderDto.setStatusName(optionalOrderEntity.getStatusOrderEntity().getName());
        return orderDto;
    }

    @Override
    public Optional<OrderDto> findById(long id) {
        return Optional.empty();
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        PaymentEntity paymentEntity = paymentOrderRepository.getById(orderDto.getPaymentOrderId());
        StatusOrderEntity statusOrderEntity = statusOrderRepository.getById(orderDto.getStatusId());
        UserEntity userEntity=userRepository.getById(orderDto.getUserId());
        OrderEntity oldEntityOrderEntity = ordersRepository.getById(orderDto.getId());
        oldEntityOrderEntity = beanConfig.modelMapper().map(orderDto, OrderEntity.class);
        oldEntityOrderEntity.setPaymentOrderEntity(paymentEntity);
        oldEntityOrderEntity.setStatusOrderEntity(statusOrderEntity);
        oldEntityOrderEntity.setUserEntityOrder(userEntity);
        ordersRepository.save(oldEntityOrderEntity);
        return beanConfig.modelMapper().map(oldEntityOrderEntity,OrderDto.class);
    }

    @Override
    public void deleteOrder(long orderId) {
        ordersRepository.deleteById(orderId);
    }
}
