package com.example.footballmanager.dto.request;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TeamRequestDto {
    @NotNull
    @Size(min = 3)
    private String name;
    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private Integer transferCosts;
    private List<Long> playersId;
    @Min(value = 0)
    private BigDecimal budget;
    private String country;
    private String town;
}
