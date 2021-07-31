package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.PitchScheduleServiceDto;

import java.util.List;

public interface PitchScheduleServiceService {
    List<PitchScheduleServiceDto> findAllServiceByPitchScheduleByBooking(long bookingId);
}
