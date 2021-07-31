package com.edu.fud.projectfootbalpitch.dto;

import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportPitch extends AbstractDto<ReportPitch> implements Serializable {
    private int month;
    private double totalPrice;
}
