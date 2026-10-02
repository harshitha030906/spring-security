package com.harshitha.springsecurity.springsecurity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String description;
}
