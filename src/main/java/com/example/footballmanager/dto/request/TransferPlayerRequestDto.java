package com.example.footballmanager.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.model.Team;
import lombok.Data;

@Data
public class TransferPlayerRequestDto {
    private Long playerId;
    private Long teamId;
    @NotNull
    @Min(value = 0)
    private BigDecimal cost;
}
