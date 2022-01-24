package com.example.footballmanager.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransferPlayerResponseDto {
    private Long id;
    private Long playerId;
    private Long teamId;
    private BigDecimal cost;
}
