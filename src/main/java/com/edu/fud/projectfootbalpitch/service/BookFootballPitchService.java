package com.edu.fud.projectfootbalpitch.service;

import com.edu.fud.projectfootbalpitch.dto.BookFootballPitchDto;
import com.edu.fud.projectfootbalpitch.dto.ServiceDto;
import com.edu.fud.projectfootbalpitch.entity.BookFootballPitchEntity;

import java.util.List;
import java.util.Optional;

public interface BookFootballPitchService {

    List<BookFootballPitchDto> findAll(long userId);
    BookFootballPitchDto save(BookFootballPitchDto bookFootballPitchDto);
    BookFootballPitchDto findByIdViewBookingDetail(long bookingId);
    void deleteBooking(long id);
    void deleteAllByStatusCancel();
}
