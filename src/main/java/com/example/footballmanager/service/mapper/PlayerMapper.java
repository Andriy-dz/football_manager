package com.example.footballmanager.service.mapper;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements ResponseDtoMapper<PlayerResponseDto, Player>,
        RequestDtoMapper<PlayerRequestDto, Player> {

    @Override
    public PlayerResponseDto mapToDto(Player player) {
        PlayerResponseDto dto = new PlayerResponseDto();
        dto.setId(player.getId());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        dto.setFirstName(player.getFirstName());
        dto.setLastName(player.getLastName());
        return dto;
    }

    @Override
    public Player mapToModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setAge(dto.getAge());
        player.setExperience(dto.getExperience());
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        return player;
    }
}
