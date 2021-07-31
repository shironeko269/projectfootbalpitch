package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.BaseEntity;
import com.edu.fud.projectfootbalpitch.entity.CategoryServiceEntity;
import com.edu.fud.projectfootbalpitch.entity.PitchScheduleServiceEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDto extends AbstractDto<ServiceDto> implements Serializable {

    @NotEmpty(message = "Không được bỏ trống")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String name;

    private String image;

    @NotEmpty(message = "Không được bỏ trống")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String unit;

    @NotNull(message = "Không được bỏ trống")
    private int quantity;

    @NotNull(message = "Không được bỏ trống")
    private double price;

    private Long categoryServiceId;

    private String categoryServiceName;

    private int totalQuantityThongKe;

    private double totalPriceSell;
}
