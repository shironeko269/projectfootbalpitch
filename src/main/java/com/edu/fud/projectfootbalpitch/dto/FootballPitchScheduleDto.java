package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.BaseEntity;
import com.edu.fud.projectfootbalpitch.entity.BookFootballPitchEntity;
import com.edu.fud.projectfootbalpitch.entity.PitchScheduleServiceEntity;
import com.edu.fud.projectfootbalpitch.entity.SubPitchEntity;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FootballPitchScheduleDto extends AbstractDto<FootballPitchScheduleDto> implements Serializable {

    private Date dateBooking;

    private Time timeStart;

    private Time timeEnd;

    private double price;

    private Long subPitchId;

}
