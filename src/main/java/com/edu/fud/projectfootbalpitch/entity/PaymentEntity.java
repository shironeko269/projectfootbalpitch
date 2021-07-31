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
@Table(name = "order_payment")
public class PaymentEntity extends BaseEntity implements Serializable {

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "paymentOrderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntitiesPayment = new ArrayList<>();
}
