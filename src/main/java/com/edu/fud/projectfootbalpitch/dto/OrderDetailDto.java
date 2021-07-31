package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.OrderEntity;
import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto extends AbstractDto<OrderDetailDto> implements Serializable {

    private Long orderId;
    private String userName;

    private Long productId;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    private int quantity;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    private double price;
}
