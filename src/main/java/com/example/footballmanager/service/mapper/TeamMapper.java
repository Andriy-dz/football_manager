package com.example.footballmanager.service.mapper;

import java.util.stream.Collectors;
import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements ResponseDtoMapper<TeamResponseDto, Team>,
        RequestDtoMapper<TeamRequestDto, Team> {
    private final PlayerService playerService;

    @Autowired
    public TeamMapper(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public Team mapToModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setBudget(dto.getBudget());
        team.setName(dto.getName());
        team.setTransferCosts(dto.getTransferCosts());
        team.setPlayers(dto.getPlayersId()
                .stream()
                .map(playerService::get)
                .collect(Collectors.toList()));
        team.setCountry(dto.getCountry());
        team.setTown(dto.getTown());
        return team;
    }

    @Override
    public TeamResponseDto mapToDto(Team team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setBudget(team.getBudget());
        dto.setName(team.getName());
        dto.setTransferCosts(team.getTransferCosts());
        dto.setPlayersId(team.getPlayers()
                .stream()
                .map(p -> p.getId())
                .collect(Collectors.toList()));
        return dto;
    }
}
