package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.ServiceDto;
import com.edu.fud.projectfootbalpitch.entity.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    List<ServiceDto> findAll();
    ServiceDto save(ServiceDto serviceDto);
    Optional<ServiceDto> findById(long id);
    void deleteService(long id);

}
