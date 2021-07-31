package com.edu.fud.projectfootbalpitch.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PitchScheduleServiceDto extends AbstractDto<PitchScheduleServiceDto> implements Serializable {

    private Long footballPitchScheduleId;

    private Long servicePitchId;

    private String nameService;

    private int quantity;

    private String unit;

    private double price;
}
