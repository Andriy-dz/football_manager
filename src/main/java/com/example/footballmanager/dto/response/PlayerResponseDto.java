package com.example.footballmanager.dto.response;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer experience;
}
