package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.*;
import com.edu.fud.projectfootbalpitch.entity.FootballPitchScheduleEntity;
import com.edu.fud.projectfootbalpitch.entity.ServiceEntity;

import java.util.List;

public interface Thongke {
    List<ServiceDto> findAllServiceByQuantitySellMostByManager(long userId);
    List<BookFootballPitchDto> findAllTimeStartByManager(long userId);
    List<UserDto> findAllUserByReturnByManager(long userId);
    double TotalMoneyByManager(long userId);
    int TotalBookingByMonthByManagerId(long userId);
    int TotalSubPitchByManagerId(long userId);
    List<ReportPitch> findAllTotalPricePitchOfMonthByManagerId(long userId);
    List<ReportService> findAllTotalPriceServiceOfMonthByManagerId(long userId);
}
