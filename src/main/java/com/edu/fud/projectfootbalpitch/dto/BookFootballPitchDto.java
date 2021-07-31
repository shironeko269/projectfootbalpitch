package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.*;
import lombok.*;
import java.sql.Time;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookFootballPitchDto extends AbstractDto<BookFootballPitchDto> implements Serializable {

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    private double PreOrderPayment;

    private Long userId;

    private Long footballPitchScheduleId;

    private Long paymentBookingId;

    private Time timeBookingMost;
    //statistics
    private String namePitchBookingMost;
    private String streetNumberPitchBookingMost;
    private String wardPitchBookingMost;
    private String districtPitchBookingMost;
}
