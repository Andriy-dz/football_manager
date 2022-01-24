package com.example.footballmanager.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.example.footballmanager.model.Player;
import lombok.Data;

@Data
public class TeamResponseDto {
    private Long id;
    private String name;
    private Integer transferCosts;
    private List<Long> playersId = new ArrayList<>();
    private BigDecimal budget;
    private String country;
    private String town;
}
