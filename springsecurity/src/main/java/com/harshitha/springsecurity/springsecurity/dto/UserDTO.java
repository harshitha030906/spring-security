package com.harshitha.springsecurity.springsecurity.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String name;
}
