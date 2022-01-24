package com.example.footballmanager.service;

import java.util.List;
import com.example.footballmanager.model.Team;
import org.springframework.stereotype.Service;

public interface TeamService {
    Team add(Team team);

    Team update(Team team);

    Team get(Long id);

    List<Team> getAll();

    void delete(Long id);

    void transferPlayer(Long teamId, Long playerId, Long buyingTeamId);
}
