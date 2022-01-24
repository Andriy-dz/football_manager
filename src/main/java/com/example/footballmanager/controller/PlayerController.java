package com.example.footballmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.exeption.CustomException;
import com.example.footballmanager.model.Player;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.mapper.PlayerMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    public PlayerController(PlayerMapper playerMapper, PlayerService playerService) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public PlayerResponseDto add(@RequestBody @Valid PlayerRequestDto playerRequestDto) {
        try {
            Player player = playerService.add(playerMapper.mapToModel(playerRequestDto));
            return playerMapper.mapToDto(player);
        } catch (Exception e) {
            throw new CustomException("Can`t inset player - " + playerRequestDto, e);
        }
    }

    @PostMapping("/add_all")
    public List<PlayerResponseDto> addAll(@RequestBody @Valid List<PlayerRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(playerMapper::mapToModel)
                    .map(playerService::add)
                    .map(playerMapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t inset players - " + dtos, e);
        }
    }

    @GetMapping("/get/{id}")
    public PlayerResponseDto get(@PathVariable Long id) {
        try {
            return playerMapper.mapToDto(playerService.get(id));
        } catch (Exception e) {
            throw new CustomException("Can`t get player by id - " + id, e);
        }
    }

    @GetMapping("/get_all/{teamId}")
    public List<PlayerResponseDto> getAllByTeamId(@PathVariable Long teamId) {
        try {
            return playerService.getAllByTeamID(teamId)
                    .stream()
                    .map(playerMapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can`t get all players by team id - " + teamId, e);
        }
    }

    @PutMapping("/update/{id}")
    public PlayerResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid PlayerRequestDto playerRequestDto) {
        try {
            Player player = playerMapper.mapToModel(playerRequestDto);
            player.setId(id);
            return playerMapper.mapToDto(playerService.update(player));
        } catch (Exception e) {
            throw new CustomException("Can`t update player by id - "
                    + id + " and player " + playerRequestDto, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        try {
            playerService.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can`t delete player by id - " + id, e);
        }
    }
}
