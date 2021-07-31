package com.edu.fud.projectfootbalpitch.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductDto extends AbstractDto<CategoryProductDto> implements Serializable {

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String name;


}
