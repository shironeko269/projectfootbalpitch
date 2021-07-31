package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.UserEntity;
import com.edu.fud.projectfootbalpitch.entity.WardEntity;
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
public class FootBallPitchDto extends AbstractDto<FootBallPitchDto> implements Serializable {

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String name;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String description;

    private String image;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String streetNumber;

    private String urlMap;

    private Long userId;

    private Long wardId;

    private String wardName;

    private String districtName;
}
