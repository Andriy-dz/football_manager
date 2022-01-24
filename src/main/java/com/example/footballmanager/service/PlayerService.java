package com.example.footballmanager.service;

import java.util.List;
import com.example.footballmanager.model.Player;
import org.springframework.stereotype.Service;

public interface PlayerService {
    Player add(Player player);

    Player update(Player player);

    Player get(Long id);

    List<Player> getAllByTeamID(Long id);

    void delete(Long id);
}
