package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.BaseEntity;
import com.edu.fud.projectfootbalpitch.entity.BookFootballPitchEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentBookingDto extends AbstractDto<PaymentBookingDto> implements Serializable {

    private String name;

}
