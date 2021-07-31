package com.edu.fud.projectfootbalpitch.entity;

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
@Entity
@Table(name = "booking_payment")
public class PaymentBookingEntity extends BaseEntity implements Serializable {

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "paymentBookingEntityBooking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookFootballPitchEntity> bookFootballPitchEntitiesPayment = new ArrayList<>();
}
