package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.BaseEntity;
import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;
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
public class SupplierDto extends AbstractDto<SupplierDto> implements Serializable {

    private String companyName;

    private String contactName;

    private String address;

    private String phone;

}
