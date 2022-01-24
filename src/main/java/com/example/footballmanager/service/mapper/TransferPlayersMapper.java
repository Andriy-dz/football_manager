package com.example.footballmanager.service.mapper;

import com.example.footballmanager.dto.request.TransferPlayerRequestDto;
import com.example.footballmanager.dto.response.TransferPlayerResponseDto;
import com.example.footballmanager.model.TransferPlayer;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class TransferPlayersMapper implements ResponseDtoMapper<TransferPlayerResponseDto, TransferPlayer>,
        RequestDtoMapper<TransferPlayerRequestDto, TransferPlayer> {
    private final TeamService teamService;
    private final PlayerService playerService;

    public TransferPlayersMapper(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public TransferPlayerResponseDto mapToDto(TransferPlayer transferPlayers) {
        TransferPlayerResponseDto dto = new TransferPlayerResponseDto();
        dto.setId(transferPlayers.getId());
        dto.setCost(transferPlayers.getCost());
        dto.setPlayerId(transferPlayers.getPlayer().getId());
        dto.setTeamId(transferPlayers.getTeam().getId());
        return dto;
    }

    @Override
    public TransferPlayer mapToModel(TransferPlayerRequestDto dto) {
        TransferPlayer transferPlayer = new TransferPlayer();
        transferPlayer.setTeam(teamService.get(dto.getTeamId()));
        transferPlayer.setPlayer(playerService.get(dto.getPlayerId()));
        transferPlayer.setCost(dto.getCost());
        return transferPlayer;
    }
}
