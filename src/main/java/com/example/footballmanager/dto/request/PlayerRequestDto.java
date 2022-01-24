package com.example.footballmanager.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PlayerRequestDto {
    @NotNull
    @Size(min = 3)
    private String firstName;
    @NotNull
    @Size(min = 3)
    private String lastName;
    @Min(value = 16)
    private Integer age;
    private Integer experience;
}
