package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.FootballPitchEntity;
import com.edu.fud.projectfootbalpitch.entity.PitchTypeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubPitchDto extends AbstractDto<SubPitchDto> implements Serializable {

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String name;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String description;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    private double price;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String image5;

    private Long pitchTypeId;

    private String nameType;

    private Long footballPitchId;

    private String footballPitchName;
}
