package com.example.footballmanager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.exeption.CustomException;
import com.example.footballmanager.model.Team;
import com.example.footballmanager.service.TeamService;
import com.example.footballmanager.service.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("get_all")
    public List<TeamResponseDto> getAll() {
        try {
            return teamService.getAll().stream()
                    .map(teamMapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can't get all teams", e);
        }
    }

    @GetMapping("/get/{id}")
    public TeamResponseDto get(@PathVariable Long id) {
        try {
            Team team = teamService.get(id);
            return teamMapper.mapToDto(team);
        } catch (Exception e) {
            throw new CustomException("Can't get team by id - " + id, e);
        }
    }

    @PostMapping("/add")
    public TeamResponseDto add(@RequestBody @Valid TeamRequestDto dto) {
        try {
            Team team = teamService.add(teamMapper.mapToModel(dto));
            return teamMapper.mapToDto(team);
        } catch (Exception e) {
            throw new CustomException("Can't insert team - " + dto, e);
        }
    }

    @PostMapping("/add_all")
    public List<TeamResponseDto> addAll(@RequestBody @Valid List<TeamRequestDto> dtos) {
        try {
            return dtos.stream()
                    .map(teamMapper::mapToModel)
                    .map(teamService::add)
                    .map(teamMapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can't insert all teams - " + dtos, e);
        }
    }

    @PutMapping("/update/{id}")
    public TeamResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid TeamRequestDto teamRequestDto) {
        try {
            Team team = teamMapper.mapToModel(teamRequestDto);
            team.setId(id);
            return teamMapper.mapToDto(teamService.update(team));
        } catch (Exception e) {
            throw new CustomException("Can't update team by id - "
                    + id + " and team " + teamRequestDto, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        try {
         teamService.delete(id);
        } catch (Exception e) {
            throw new CustomException("Can't delete team by id - " + id, e);
        }
    }

    @GetMapping("/transfer")
    public List<TeamResponseDto> transfer(@RequestParam Long playerTeamId,
                         @RequestParam Long playerId,
                         @RequestParam Long buyingTeamId) {
        try {
            teamService.transferPlayer(playerTeamId, playerId, buyingTeamId);
            return teamService.getAll().stream()
                    .map(teamMapper::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Can't transfer player by id - "
                    + playerId + " from team id " + playerId + " to team id" + buyingTeamId, e);
        }
    }
}
