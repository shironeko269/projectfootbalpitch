package com.edu.fud.projectfootbalpitch.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentOrderDto extends AbstractDto<PaymentOrderDto> implements Serializable {

    private String name;

}
