package com.edu.fud.projectfootbalpitch.dto;

import com.edu.fud.projectfootbalpitch.entity.CategoryProductEntity;
import com.edu.fud.projectfootbalpitch.entity.SupplierEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends AbstractDto<ProductDto> implements Serializable {

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String name;

    @NotNull
    private int quantity;

    @NotNull
    private double price;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 1,message = "Ít nhất phải 1 kí tự")
    private String unit;

    private String image;

    @NotNull
    private double discount;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 20,message = "Ít nhất phải 20 kí tự")
    private String description;

//    private SupplierDto supplierDto;
//
//    private CategoryProductDto categoryProductDto;
    private Long supplierId;

    private String companyName;

    private Long categoryId;

    private String categoryName;
}
