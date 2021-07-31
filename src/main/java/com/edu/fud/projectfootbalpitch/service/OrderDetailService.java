package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> findAllByOrderId(long orderId);
}
