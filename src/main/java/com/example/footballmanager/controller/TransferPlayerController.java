package com.example.footballmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.example.footballmanager.dto.request.TransferPlayerRequestDto;
import com.example.footballmanager.dto.response.TransferPlayerResponseDto;
import com.example.footballmanager.exeption.CustomException;
import com.example.footballmanager.model.TransferPlayer;
import com.example.footballmanager.service.PlayerService;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.service.TransferPlayersService;
import com.example.footballmanager.service.mapper.TransferPlayersMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/transfer_players")
public class TransferPlayerController {
    private final TransferPlayersService transferPlayersService;
    private final TransferPlayersMapper mapper;
    private final TeamService teamService;
    private final PlayerService playerService;

    public TransferPlayerController(TransferPlayersService transferPlayersService,
                                    TransferPlayersMapper mapper,
                                    TeamService teamService,
                                    PlayerService playerService) {
        this.transferPlayersService = transferPlayersService;
        this.mapper = mapper;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/get/{id}")
    public TransferPlayerResponseDto get(@PathVariable Long id) {
        try {
            return mapper.mapToDto(transferPlayersService.get(id));
        } catch (Exception e) {
            throw new CustomException("Can't get transfer player by id - " + id, e);
        }
    }

    @GetMapping("/get_all/{teamId}")
    public List<TransferPlayerResponseDto> getAllByTeamId(@PathVariable Long teamId) {
        try {
            return transferPlayersService.getAllByTeamId(teamId)
                    .stream()
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can't get all transfer players by team id - " + teamId, e);
        }
    }

    @PostMapping("/add")
    public TransferPlayerResponseDto add(@RequestBody @Valid TransferPlayerRequestDto dto) {
        try {
            return mapper.mapToDto(transferPlayersService.add(mapper.mapToModel(dto)));
        } catch (Exception e) {
            throw new CustomException("Can't add transfer player - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<TransferPlayerResponseDto> addAll(@RequestBody @Valid List<TransferPlayerRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(mapper::mapToModel)
                    .map(transferPlayersService::add)
                    .map(mapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can't add all transfer players - " + dtos, e);
        }
    }

    @PutMapping("/update/{id}")
    public TransferPlayerResponseDto update(@PathVariable Long id,
                                            @RequestBody @Valid TransferPlayerRequestDto dto) {
        try {
            TransferPlayer transferPlayer = new TransferPlayer();
            transferPlayer.setId(id);
            transferPlayer.setTeam(teamService.get(dto.getTeamId()));
            transferPlayer.setPlayer(playerService.get(dto.getPlayerId()));
            return mapper.mapToDto(transferPlayersService.update(transferPlayer));
        } catch (Exception e) {
            throw new CustomException("Can't update transfer player by id "
                    + id + " and transfer player " + dto, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        try {
            transferPlayersService.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can't delete transfer players by id - " + id, e);
        }
    }
}
