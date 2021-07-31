package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookFootballPitchDto extends AbstractDto<BookFootballPitchDto> implements Serializable {

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    private double PreOrderPayment;

    private Long userId;

    private String emailUserBooking;

    private Long footballPitchScheduleId;

    private String subFootballPitchName;

    private Long paymentBookingId;

    private String paymentBookingName;

    private Long statusBookFootballPitchId;

    private String statusBookFootballPitchName;
    private String userBook;

    private Date dateCreate;

    private Time timeStart;

    private Time timeEnd;

    private Time timeBookingMost;

    private int numberBookingMost;

    private double pricePitchSchedule;
}
