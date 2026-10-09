package com.harshitha.springsecurity.springsecurity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDTO {
    private String email;
    private String name;
    private String password;
}
