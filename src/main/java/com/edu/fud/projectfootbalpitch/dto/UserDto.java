package com.edu.fud.projectfootbalpitch.dto;

import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbstractDto<UserDto> implements Serializable {


    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String userName;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String password;

    @NotEmpty(message = "Không được bỏ trống!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String fullName;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String phone;

    @NotBlank(message = "Không được bỏ trống,Không có khoảng cách!")
    @Email(message = "Nhập đúng định dạng email!")
    private String gmail;

    @NotEmpty(message = "Không được bỏ trống!")
    @Size(min = 5,message = "Ít nhất phải 5 kí tự")
    private String address;

    private String image;

    private String role;

    private Long isMemberId;

    private int numberUserReturnMost;
}
