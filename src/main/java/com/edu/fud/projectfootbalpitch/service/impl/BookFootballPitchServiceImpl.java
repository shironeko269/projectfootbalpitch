package com.edu.fud.projectfootbalpitch.service.impl;

import com.edu.fud.projectfootbalpitch.config.BeanConfig;
import com.edu.fud.projectfootbalpitch.dto.BookFootballPitchDto;
import com.edu.fud.projectfootbalpitch.dto.ServiceDto;
import com.edu.fud.projectfootbalpitch.entity.*;
import com.edu.fud.projectfootbalpitch.repository.*;
import com.edu.fud.projectfootbalpitch.service.BookFootballPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookFootballPitchServiceImpl implements BookFootballPitchService {

    @Autowired
    private BookFootballPitchRepository bookFootballPitchRepository;

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private StatusBookFootballPitchRepository statusBookFootballPitchRepository;

    @Autowired
    private PaymentBookFootballPitchRepository paymentBookFootballPitchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FootballPitchScheduleRepository footballPitchScheduleRepository;

    @Override
    public List<BookFootballPitchDto> findAll(long userId) {
        List<BookFootballPitchDto> bookFootballPitchDtos = new ArrayList<>();
        List<BookFootballPitchEntity> bookFootballPitchEntities = bookFootballPitchRepository.findAll(userId);
        for (BookFootballPitchEntity entity: bookFootballPitchEntities){
            BookFootballPitchDto bookFootballPitchDto = beanConfig.modelMapper().map(entity,BookFootballPitchDto.class);
            bookFootballPitchDto.setStatusBookFootballPitchName(entity.getStatusBookFootballPitchEntityBooking().getName());
            bookFootballPitchDto.setPaymentBookingName(entity.getPaymentBookingEntityBooking().getName());
            bookFootballPitchDto.setSubFootballPitchName(entity.getFootballPitchScheduleEntityBooking().getSubPitchEntitySchedule().getName());
            bookFootballPitchDto.setUserBook(entity.getUserEntityPitchBooking().getUserName());
        //    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
           bookFootballPitchDto.setDateCreate(entity.getFootballPitchScheduleEntityBooking().getDateBooking());
           bookFootballPitchDto.setTimeStart(entity.getFootballPitchScheduleEntityBooking().getTimeStart());
            bookFootballPitchDto.setTimeEnd(entity.getFootballPitchScheduleEntityBooking().getTimeEnd());
            bookFootballPitchDto.setEmailUserBooking(entity.getUserEntityPitchBooking().getGmail());
            bookFootballPitchDtos.add(bookFootballPitchDto);
        }
        return bookFootballPitchDtos;
    }

    @Override
    public BookFootballPitchDto save(BookFootballPitchDto bookFootballPitchDto) {
        PaymentBookingEntity paymentBookingEntity = paymentBookFootballPitchRepository.getById(bookFootballPitchDto.getPaymentBookingId());
        StatusBookFootballPitchEntity statusBookFootballPitchEntity = statusBookFootballPitchRepository.getById(bookFootballPitchDto.getStatusBookFootballPitchId());
        FootballPitchScheduleEntity footballPitchScheduleEntity = footballPitchScheduleRepository.getById(bookFootballPitchDto.getFootballPitchScheduleId());
        UserEntity userEntity=userRepository.getById(bookFootballPitchDto.getUserId());
        BookFootballPitchEntity oldBookFootballPitchEntity = bookFootballPitchRepository.getById(bookFootballPitchDto.getId());
        oldBookFootballPitchEntity = beanConfig.modelMapper().map(bookFootballPitchDto, BookFootballPitchEntity.class);
        oldBookFootballPitchEntity.setPaymentBookingEntityBooking(paymentBookingEntity);
        oldBookFootballPitchEntity.setStatusBookFootballPitchEntityBooking(statusBookFootballPitchEntity);
        oldBookFootballPitchEntity.setUserEntityPitchBooking(userEntity);
        oldBookFootballPitchEntity.setFootballPitchScheduleEntityBooking(footballPitchScheduleEntity);
        bookFootballPitchRepository.save(oldBookFootballPitchEntity);
        return beanConfig.modelMapper().map(oldBookFootballPitchEntity,BookFootballPitchDto.class);
    }

    @Override
    public BookFootballPitchDto findByIdViewBookingDetail(long bookingId) {
        BookFootballPitchEntity bookFootballPitchEntity=
                bookFootballPitchRepository.findByIdViewBookingDetail(bookingId);
        BookFootballPitchDto bookFootballPitchDto=beanConfig.modelMapper().map(bookFootballPitchEntity,BookFootballPitchDto.class);
        bookFootballPitchDto.setDateCreate(bookFootballPitchEntity.getFootballPitchScheduleEntityBooking().getDateBooking());
        bookFootballPitchDto.setTimeStart(bookFootballPitchEntity.getFootballPitchScheduleEntityBooking().getTimeStart());
        bookFootballPitchDto.setTimeEnd(bookFootballPitchEntity.getFootballPitchScheduleEntityBooking().getTimeEnd());
        bookFootballPitchDto.setPricePitchSchedule(bookFootballPitchEntity.getFootballPitchScheduleEntityBooking().getPrice());
        return bookFootballPitchDto;
    }

    @Override
    public void deleteBooking(long id) {
        bookFootballPitchRepository.deleteById(id);
    }

    @Override
    public void deleteAllByStatusCancel() {
        long[] bookingEntity= footballPitchScheduleRepository.deleteAllByStatusCancel();
        for(long id:bookingEntity){
            footballPitchScheduleRepository.deleteById(id);
        }
    }

}
